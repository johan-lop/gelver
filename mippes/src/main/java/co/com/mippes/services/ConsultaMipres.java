package co.com.mippes.services;

import java.util.List;

import co.com.mippes.dto.DireccionamientoDTO;

public interface ConsultaMipres {

	public String validaToken(String nit);
	
	public List<DireccionamientoDTO> consultaDireccionamiento(String nit, String fecha);
	
	public void registarProgramacion(String nit);
	
}
