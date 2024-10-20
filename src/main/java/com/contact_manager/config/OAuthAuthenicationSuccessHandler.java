package com.contact_manager.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contact_manager.entities.Providers;
import com.contact_manager.entities.User;
import com.contact_manager.helper.WebAppConstants;
import com.contact_manager.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedRegisteredId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedRegisteredId);

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        oAuth2User.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(WebAppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);

        //google
        if (authorizedRegisteredId.equalsIgnoreCase("google")) {
            // getting google attrubutes
            user.setEmail(oAuth2User.getAttribute("email"));
            user.setProfilePic(oAuth2User.getAttribute("picture"));
            user.setName(oAuth2User.getAttribute("name"));
            user.setProviderUserId(oAuth2User.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using Google");

        } //github
        else if (authorizedRegisteredId.equalsIgnoreCase("github")) {
            // getting github attrubites
            String email = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email") : oAuth2User.getAttribute("login") + "@gmail.com";
            String picture = oAuth2User.getAttribute("avatar_url");
            String name = oAuth2User.getAttribute("login");
            String providerUserId = oAuth2User.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account is created using GitHub");

        } else {
            logger.info("Oauth : Unknown provider");
        }

        User checkUser = userRepository.findByEmail(user.getEmail()).orElse(null);

        if (checkUser == null) {
            userRepository.save(user);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
