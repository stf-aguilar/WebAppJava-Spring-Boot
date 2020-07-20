package com.bolsadeideas.springboot.app.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.web.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping({"/index", "/", "","/home"})
	public String index(Model model){
		model.addAttribute("titulo", "Hola Spring desde Model");
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Estefanía");
		usuario.setApellido("A");
		usuario.setEmail("silestagui@gmail.com");
		
		model.addAttribute("titulo", "Perfil del usuario ".concat(usuario.getNombre()));
		model.addAttribute("usuario", usuario);
		
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Estefanía","Aguilar","silestagui@gmail.com"));
		usuarios.add(new Usuario("Pedro","Picapiedras","pp@gmail.com"));
		usuarios.add(new Usuario("Mujer","Maravilla","mujerm@hotmail.com"));
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		
		return "listar";
		
	}
}
