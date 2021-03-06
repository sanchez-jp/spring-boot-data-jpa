package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Clase de configuración para Spring Security para registrar usuarios
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// Habilita la captación de notaciones de seguridad de los controllers
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JpaUserDetailService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Asignación de rutas permitidas roles de usuario
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar", "/locale").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successHandler)
                .loginPage("/login")
                .permitAll() // Acceso a login para todos los usuarios
                .and()
                .logout().permitAll()  // Acceso a logout para todos los usuarios
                .and()
                .exceptionHandling().accessDeniedPage("/error_403"); // redirecionamiento a página de error
    }

    /**
     * Configuración global de permisos para Spring Security
     *
     * @param builder Asistente de configuración de autenticaciones
     */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
