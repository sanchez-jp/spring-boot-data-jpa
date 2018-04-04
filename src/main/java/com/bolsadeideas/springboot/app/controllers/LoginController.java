package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    /**
     * Método controlador para login
     *
     * @param model     contenedor para pasar datos a la vista
     * @param principal el usuario actualmente logeado
     * @param flash     contenedor para pasar mensajes flash a la vista
     * @return el nombre de la vista a renderizar (login)
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            Model model, Principal principal, RedirectAttributes flash) {

        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha iniciado sesión");
            return "redirect:/";
        }

        if(error != null){
            model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta. " +
                    "Por favor, vuelva a intentarlo");
        }

        return "login";
    }
}
