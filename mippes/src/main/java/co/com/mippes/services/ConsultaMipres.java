package co.com.mippes.services;

import java.util.List;

import co.com.mippes.dto.ConsultaEntregaDTO;
import co.com.mippes.dto.ConsultaProgramacionDTO;
import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.dto.EntregaDTO;
import co.com.mippes.dto.ProgramacionDTO;
import co.com.mippes.dto.ReporteEntregaDTO;
import co.com.mippes.dto.RespuestaEntregaDTO;
import co.com.mippes.dto.RespuestaProgramacionDTO;
import co.com.mippes.dto.RespuestaReporteEntregaDTO;

public interface ConsultaMipres {

	public String validaToken(String nit);

	public List<DireccionamientoDTO> consultaDireccionamiento(String nit, String fecha);

	public RespuestaProgramacionDTO registarProgramacion(String nit, ProgramacionDTO programacion, Integer idDireccionamiento);

	public String registaAnulacion(String nit, Integer idAnulacion, Integer idDireccionamiento);
	
	public RespuestaEntregaDTO registrarEntrega(String nit, EntregaDTO entrega, Integer idDireccionamiento);
	
	public RespuestaReporteEntregaDTO registrarReporteEntrega(String nit, ReporteEntregaDTO reporteEntrega, Integer idDireccionamiento);
	
	public List<ConsultaProgramacionDTO> consultaProgramaciones(String nit, String fecha);
	
	public List<ConsultaEntregaDTO> consultaEntregas(String nit, String fecha);
	
	public void consultaReporteEntregas(String nit, String fecha);

}
