package co.com.mippes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.services.ConsultaMipres;

@RestController
@RequestMapping("/impuestos")
public class ConsultaController {

	@Autowired
	private ConsultaMipres consulta;

	@GetMapping(path = "/saludar")
	public List<DireccionamientoDTO> obtenerTiposDocumento() {
		return consulta.consultaDireccionamiento("555555196", "2019-08-06");
	}
	

	@GetMapping(path = "/token")
	public String obtenerrToken() {
		return consulta.validaToken("555555196");
	}
	
	@GetMapping(path = "/programacion")
	public void guardarProgramacion() {
		consulta.registarProgramacion("555555196");
	}

}
