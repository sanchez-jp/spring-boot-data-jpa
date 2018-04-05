package com.bolsadeideas.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Clase de configuración
 * Extiende de WebMvcConfigurerAdapter, clase que provee Spring Boot para complementar o sobreescribir la configuración
 * del proyecto
 * En Spring Boot 2: implements WebMvcConfigurer
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass()); // Logger para depuración por consola

    /**
     * Controlador parametrizable para redirigir la página de error 403 de inicio de sesión a una vista personalizada
     *
     * @param registry asistente para registro de controladores automáticos simples preconfigurados con código de estado
     *                 y/o una vista.
     */
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/error_403").setViewName("error_403");
    }

    /**
     * Obtiene un codificador BCrypt pata contraseñas
     *
     * @return el codificador para contraseñas
     */
    @Bean // Indica que es un componente Bean de Spring
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Obtiene el Locale con la internacionalización de la sesión
     *
     * @return el LocalResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("es", "ES"));
        return localeResolver;
    }

    /**
     * Interceptor para cambair el lenguaje cada vez que se reciba el Locale
     *
     * @return el interceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

    /**
     * Agrega los interceptores
     *
     * @param registry Registro donde se almacenará el interceptor de Locale
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
