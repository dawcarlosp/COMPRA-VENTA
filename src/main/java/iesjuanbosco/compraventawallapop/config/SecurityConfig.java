package iesjuanbosco.compraventawallapop.config;

import iesjuanbosco.compraventawallapop.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration      //Especifica que es una clase de configuración
@EnableWebSecurity  //Especificamos con esta clase vamos a configurar la seguridad de la App
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final CustomUserDetailsService customUserDetailsService;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/anuncios/view/**").permitAll()
                        .requestMatchers("/busqueda/*").permitAll()
                        .requestMatchers("/anuncios/**").hasRole("USER")
                        .anyRequest().permitAll()   /* Por ejemplo para la URL "/productos/new" habría que estar autenticado con cualquier ROL */
                )
                .formLogin(
                        form -> form
                                .loginPage("/")
                                .defaultSuccessUrl("/")
                                .failureUrl("/?errorLogin")
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para el logout
                        .logoutSuccessUrl("/") // URL de redirección después de logout
                        .invalidateHttpSession(true) // Invalida la sesión
                        .deleteCookies("JSESSIONID") // Borra las cookies de sesión
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                .key("uniqueAndSecret") // Clave para cifrar las cookies
                        .rememberMeParameter("remember-me")
                        .useSecureCookie(true) // Usar cookies seguras si es necesario
                        .alwaysRemember(true)
                .tokenValiditySeconds(5 * 24 * 60 * 60) // Duración de la cookie en segundos (5 días)
                .userDetailsService(customUserDetailsService) // Servicio para cargar detalles del usuario
        ); ;


        return http.build();
    }
}