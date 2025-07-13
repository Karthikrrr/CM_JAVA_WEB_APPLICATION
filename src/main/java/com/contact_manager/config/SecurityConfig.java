package com.contact_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.contact_manager.services.servicesImplementation.CustomSecurityUserDetailService;

@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user =
    // User.withUsername("admin123").password("admin123").roles("ADMIN",
    // "USER").build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    // return inMemoryUserDetailsManager;
    // }
    
    private final CustomSecurityUserDetailService customSecurityUserDetailService;
    private final OAuthAuthenicationSuccessHandler oAuthAuthenicationSuccessHandler;

    public SecurityConfig(CustomSecurityUserDetailService customSecurityUserDetailService,
            OAuthAuthenicationSuccessHandler oAuthAuthenicationSuccessHandler) {
        this.customSecurityUserDetailService = customSecurityUserDetailService;
        this.oAuthAuthenicationSuccessHandler = oAuthAuthenicationSuccessHandler;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customSecurityUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/css/**", "/images/**",
            // "/js/**").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")
                    .loginProcessingUrl("/authentication")
                    // .successForwardUrl("/user/dashboard")
                    .defaultSuccessUrl("/user/profile")
                    .usernameParameter("email")
                    .passwordParameter("password");
        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true");
        });

        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(oAuthAuthenicationSuccessHandler);
        });
        return httpSecurity.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
