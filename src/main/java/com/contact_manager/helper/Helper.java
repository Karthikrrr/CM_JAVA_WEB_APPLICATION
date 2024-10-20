package com.contact_manager.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken oauth2AuthenticationToken) {
            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();

            String username = "";

            if (clientId.equalsIgnoreCase("google")) {
                username = oauth2User.getAttribute("email");
            } else if (clientId.equalsIgnoreCase("github")) {
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email")
                        : oauth2User.getAttribute("login") + "@gmail.com";

            }
            return username;
        } else {
            return authentication.getName();
        }

    }

}