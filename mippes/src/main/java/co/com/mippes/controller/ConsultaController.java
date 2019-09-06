package co.com.mippes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mippes.dao.LogInvocacionWsDAO;
import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.entity.LogInvocacionWs;
import co.com.mippes.enumerator.CodigoServicio;
import co.com.mippes.services.ConsultaMipres;
import co.com.mippes.services.DireccionamientoLogica;

@RestController
@RequestMapping("/mipres")
public class ConsultaController {

	@Autowired
	private ConsultaMipres consulta;
	@Autowired
	private DireccionamientoLogica direccionamientoLogica;
	@Autowired
	private LogInvocacionWsDAO logDAO;
	
	@Value("${mipres.nit.ips}")
	private String nit;

	@GetMapping(path = "/direccionamiento/{fecha}")
	public List<DireccionamientoDTO> obtenerDireccionamientos(@PathVariable("fecha") String fecha) {
		return direccionamientoLogica.procesaDireccionamientos(fecha);
	}
	
	@GetMapping(path = "/direccionamientoAgosto")
	public List<DireccionamientoDTO> obtenerDireccionamientosAgosto() {
		LocalDate fechaInicial = LocalDate.of(2019, 8, 1);
		LocalDate fechaFinal = LocalDate.of(2019, 8, 31);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<DireccionamientoDTO> direc = new ArrayList<>();
		while (fechaInicial.isBefore(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			direc.addAll(direccionamientoLogica.procesaDireccionamientos(fechaInicial.format(formatter)));
			fechaInicial = fechaInicial.plusDays(1);
		}
		return direc;
	}

	@GetMapping(path = "/token")
	public String obtenerrToken() {
		LogInvocacionWs logInvocacionWs =  logDAO.findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio.CONSULTA_REPORTE_ENTREGAS, Boolean.TRUE);
		return logInvocacionWs.getId().toString();
	}
	
	@GetMapping(path = "/programacion")
	public void guardarProgramacion() {
		direccionamientoLogica.registrarProgramacion();
	}

	@GetMapping(path = "/anulaciones")
	public void guardarAnulaciones() {
		direccionamientoLogica.registrarAnulaciones();
	}
	
	@GetMapping(path = "/entrega")
	public void guardarEntregas() {
		direccionamientoLogica.registrarEntrega();
	}

	@GetMapping(path = "/consultaEntrega")
	public void consultaEntrega() {
		LocalDate fechaInicial = LocalDate.of(2019, 9, 1);
		LocalDate fechaFinal = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		while (fechaInicial.isBefore(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			direccionamientoLogica.consultaEntregas(fechaInicial.format(formatter));
			fechaInicial = fechaInicial.plusDays(1);
		}
	}
	
	
	@GetMapping(path = "/consultaProgramaciones")
	public void consultaProgramaciones() {
		LocalDate fechaInicial = LocalDate.of(2019, 8, 26);
		LocalDate fechaFinal = LocalDate.of(2019, 8, 30);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		while (fechaInicial.isBefore(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			direccionamientoLogica.consultaProgramaciones(fechaInicial.format(formatter));
			fechaInicial = fechaInicial.plusDays(1);
		}
	}
	

}
