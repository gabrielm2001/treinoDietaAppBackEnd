package treinoDieta.api.infra.security;

import jdk.javadoc.doclet.Doclet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.w3c.dom.DocumentType;

import javax.swing.text.Document;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessim->sessim.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers(HttpMethod.POST ,"/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST ,"/auth/login").permitAll()

                                .requestMatchers(HttpMethod.GET ,"/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                                .requestMatchers(HttpMethod.POST ,"/treino", "/aluno", "/exercicio", "/ficha", "/dieta").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROFESSOR")
                                .requestMatchers(HttpMethod.POST ,"/professor").hasAnyAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
