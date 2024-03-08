package treinoDieta.api.securit;

import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf->csrf.disable())
                .sessionManagement(sessim->sessim.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers(HttpMethod.POST ,"/register").permitAll()
                                .requestMatchers(HttpMethod.POST ,"/login").permitAll()

                                .requestMatchers(HttpMethod.POST ,"/dieta").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROFESSOR")
                                .requestMatchers(HttpMethod.POST ,"/treino").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROFESSOR")
                )

                .build();
    }

}
