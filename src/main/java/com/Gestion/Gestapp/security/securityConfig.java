package com.Gestion.Gestapp.security;

import com.Gestion.Gestapp.model.MyUserDetailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;


@Configuration
@AllArgsConstructor
public class securityConfig {

    private MyUserDetailService myUserDetailService;

    //pour securiser nos pages
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //csrf -> csrf.disable()
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/private").authenticated()

                        .requestMatchers("/","/register/**").permitAll()
                                .requestMatchers("/user").hasRole("USER")
                                .requestMatchers("/admin").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                //.formLogin(login -> login
                //      .permitAll()
                .logout(LogoutConfigurer::permitAll)
                //logout(logout -> logout
                //      .permitAll()
                .build();
    }

    //SI ON A DEFINI UNE PAGE DE CONNEXION !
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth->{
//                    auth.requestMatchers("/").permitAll();
//                    auth.requestMatchers("/privatePage").authenticated();
//                })
//                .logout(logout ->{
//                    logout.logoutSuccessUrl("/");
//                })
//                .formLogin(login -> {
//                    login.loginPage("/login").permitAll();
//                })
//                .build();
//
//    }

    //pour creer des utilisateurs en memoire
    @Bean
    public UserDetailsService userDetailsService() {
        //creer en bd
        return myUserDetailService;


    }

    @Bean
    public DaoAuthenticationProvider AuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //pour encoder le password (OBLIGATOIRE)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
