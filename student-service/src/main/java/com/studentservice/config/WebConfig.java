package com.studentservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
Paquete config

Esta capa contiene las clases de configuración global de la aplicación.
Aquí se definen aspectos como seguridad, filtros, interceptores o configuraciones de CORS.
*/

/*
Clase WebConfig

Configura el mapeo de CORS para permitir solicitudes desde orígenes cruzados.
Esto es sumamente útil durante las pruebas de la hackathon.
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
