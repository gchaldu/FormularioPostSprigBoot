package com.chaldu.formularioweb.formularioweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chaldu.formularioweb.formularioweb.models.domain.Usuario;

import jakarta.validation.Valid;

@Controller
public class FormController {
    
    @GetMapping("/form")
    public String form(Model model) {
        return "form";
    }
    @PostMapping("/form")
    public String procesarForm(@Valid Usuario usuario,BindingResult result ,Model model) {

        model.addAttribute("titulo", "Resultado del POST");
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach((error) -> {
                errores.put(error.getField(), "El campo".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()) );
            });
            model.addAttribute("errores", errores);
            return "form";
        }
        model.addAttribute("usuario", usuario);
        return "resultado";
    }

    /* @PostMapping("/form")
    public String procesarForm(Model model,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email) {

        Usuario usuario = new Usuario(username, password, email);
        model.addAttribute("titulo", "Resultado del POST");
        model.addAttribute("usuario", usuario);
        return "resultado";
    } */
}
