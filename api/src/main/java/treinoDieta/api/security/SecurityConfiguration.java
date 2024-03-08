package treinoDieta.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf->csrf.disable())
                .sessionManagement(sessim->sessim.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers(HttpMethod.POST ,"/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST ,"/auth/login").permitAll()

                                .requestMatchers(HttpMethod.POST ,"/dieta").hasAnyAuthority("ROLE_ADMIM", "ROLE_PROFESSOR")
                                .requestMatchers(HttpMethod.POST ,"/treino").hasAnyAuthority("ROLE_ADMIM", "ROLE_PROFESSOR")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
