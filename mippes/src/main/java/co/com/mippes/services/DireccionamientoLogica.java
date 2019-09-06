package co.com.mippes.services;

import java.util.List;

import co.com.mippes.dto.DireccionamientoDTO;

public interface DireccionamientoLogica {

	public List<DireccionamientoDTO> procesaDireccionamientos(String fecha);
	
	public void registrarProgramacion();
	
	public void registrarAnulaciones();
	
	public void registrarEntrega();
	
	public void registrarReporteEntrega();
	
	public void consultaProgramaciones(String fecha);
	
	public void consultaEntregas(String fecha);
	
	public void consultaReporteEntrega(String fecha);
	
}
