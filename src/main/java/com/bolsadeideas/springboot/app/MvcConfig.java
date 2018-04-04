package com.bolsadeideas.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración
 * Extiende de WebMvcConfigurerAdapter, clase que provee Spring Boot para complementar o sobreescribir la configuración
 * del proyecto
 * En Spring Boot 2: implements WebMvcConfigurer
 */
@Configuration
public class MvcConfig  implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass()); // Logger para depuración por consola
    /**
     * Manejador de recursos. Establece el directorio de recursos de donde obtener las imágenes de los clientes
     * @param registry registra una configuración determinada
     */
    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString(); // toUri() incluye el esquema "file:/"
        log.info("resourcePath: " + resourcePath);
        registry.addResourceHandler("/uploads/**") // "**" indican cualquier archivo o subcarpeta que se ecuentre en ese directorio
        .addResourceLocations(resourcePath); // Agrega la ubicación del recurso al que se va a acceder. (ruta absoluta)
    }
    */


}
