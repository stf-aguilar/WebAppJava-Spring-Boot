package com.bolsadeideas.springboot.form.app.controllers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boldadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.models.Pais;
import com.bolsadeideas.springboot.form.app.models.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormControlller {
	
	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private PaisService paisService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@org.springframework.web.bind.annotation.InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais",paisEditor);
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("Mexico", "Peru", "Chile","Venezuela", "Uruguay");	
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USUARIO");
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("MX","México");
		paises.put("PE", "Peru");
		paises.put("CL", "Chile");
		paises.put("VE", "Venezuela");
		paises.put("UY", "Uruguay");
		return paises;
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Homero");
		usuario.setApellido("Simpson");
		usuario.setIdentificador("123.456.789:k");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario,BindingResult result, Model model) {
		//validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado form");
		
		if(result.hasErrors()) {	
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		return "resultado";
	}
}
