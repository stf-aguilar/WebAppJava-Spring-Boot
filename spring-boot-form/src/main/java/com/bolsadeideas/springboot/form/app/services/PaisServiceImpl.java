package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais(1, "MX", "Mexico"), 
				new Pais(2, "PE", "Peru"), 
				new Pais(3,"CL","Chile"),
				new Pais(4, "VE", "Venezuela"), 
				new Pais(5, "UY", "Uruguay"));	
	}
	
	@Override
	public List<Pais> listar() {
		return null;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		
		for(Pais pais:this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
