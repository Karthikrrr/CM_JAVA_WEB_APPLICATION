package com.contact_manager.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

                DefaultOAuth2User oAuth2User = (DefaultOAuth2User)authentication.getPrincipal();

                // oAuth2User.getAttributes().forEach((key, value) -> {
                //     logger.info(key + " : " + value);
                // });

                String email = oAuth2User.getAttribute("email").toString();
                String name = oAuth2User.getAttribute("name").toString();
                String picture = oAuth2User.getAttribute("picture").toString();
                

                User google_user = new User();

                google_user.setEmail(email);
                google_user.setName(name);
                google_user.setProfilePic(picture);
                google_user.setPassword("password");
                google_user.setUserId(UUID.randomUUID().toString());
                google_user.setProvider(Providers.GOOGLE);
                google_user.setEnabled(true);
                google_user.setEmailVerified(true);
                google_user.setProviderUserId(oAuth2User.getName());
                google_user.setRoleList(List.of(WebAppConstants.ROLE_USER));
                google_user.setAbout("This Account Is Created By " + name);


                User checkUser = userRepository.findByEmail(email).orElse(null);

                if(checkUser == null){
                    userRepository.save(google_user);
                    logger.info("user Saved : " + email);
                }


                
                
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }



} 
