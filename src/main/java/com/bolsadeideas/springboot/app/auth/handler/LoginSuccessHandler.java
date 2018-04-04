package com.bolsadeideas.springboot.app.auth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controla el inicio de sesión de los usuarios
 */
@Component // Indica que se trata de un componente de Spring
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * @param request        la solicitud de autenticación de usuario
     * @param response       la respuesta a la solicitud de autenticación
     * @param authentication la autenticación del usuario
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        /* Administrador de map para los mensajes flash. Se usa esta metodología porque aquí no se puede hacer uso de la
           notación @RedirectAttributes */
        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();

        FlashMap flashMap = new FlashMap();

        flashMap.put("success", "¡Hola " + authentication.getName() + "! Has iniciado sesión con éxito");

        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        if (authentication != null) {
            logger.info("El usuario \"" + authentication.getName() + "\" ha iniciado sesión con éxito");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
