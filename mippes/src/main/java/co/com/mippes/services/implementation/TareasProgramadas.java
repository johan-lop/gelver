package co.com.mippes.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import co.com.mippes.dao.LogInvocacionWsDAO;
import co.com.mippes.entity.LogInvocacionWs;
import co.com.mippes.enumerator.CodigoServicio;
import co.com.mippes.services.DireccionamientoLogica;

@Component
public class TareasProgramadas {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static final Logger log = LoggerFactory.getLogger(TareasProgramadas.class);
	
	@Autowired
	private LogInvocacionWsDAO logDAO;
	
	@Autowired
	private DireccionamientoLogica direccionamientoLogica;
		
	@Scheduled(cron = "0 0 20 * * *")
	 public void consultaProgramaciones() {
		 LogInvocacionWs logInvocacionWs =  logDAO.findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio.CONSULTA_PROGRAMACIONES, Boolean.TRUE);
		 LocalDate fecha = LocalDate.now();
		 LocalDate fechaActual = LocalDate.now();
		 if (logInvocacionWs != null) {
			 fecha = logInvocacionWs.getFechaConsulta().toLocalDate().plusDays(1);
		 } else {
			 fecha = fecha.minusDays(8);
		 }
		 while (fecha.isBefore(fechaActual) || fecha.equals(fechaActual)) {
			 direccionamientoLogica.consultaProgramaciones(formatter.format(fecha));
			 fecha = fecha.plusDays(1);
		 }
	 }
	
	@Scheduled(cron = "0 0/30 20 * * *")
	 public void consultaEntregas() {
		 LogInvocacionWs logInvocacionWs =  logDAO.findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio.CONSULTA_ENTREGAS, Boolean.TRUE);
		 LocalDate fecha = LocalDate.now();
		 LocalDate fechaActual = LocalDate.now();
		 if (logInvocacionWs != null) {
			 fecha = logInvocacionWs.getFechaConsulta().toLocalDate().plusDays(1);
		 } else {
			 fecha = fecha.minusDays(8);
		 }
		 while (fecha.isBefore(fechaActual) || fecha.equals(fechaActual)) {
			 direccionamientoLogica.consultaEntregas(formatter.format(fecha));
			 fecha = fecha.plusDays(1);
		 }
	 }
	
	@Scheduled(cron = "0 0 21 * * *")
	 public void consultaReporteEntregas() {
		 LogInvocacionWs logInvocacionWs =  logDAO.findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio.CONSULTA_REPORTE_ENTREGAS, Boolean.TRUE);
		 LocalDate fecha = LocalDate.now();
		 LocalDate fechaActual = LocalDate.now();
		 if (logInvocacionWs != null) {
			 fecha = logInvocacionWs.getFechaConsulta().toLocalDate().plusDays(1);
		 } else {
			 fecha = fecha.minusDays(8);
		 }
		 while (fecha.isBefore(fechaActual) || fecha.equals(fechaActual)) {
			 direccionamientoLogica.consultaReporteEntrega(formatter.format(fecha));
			 fecha = fecha.plusDays(1);
		 }
	 }
	
	 @Scheduled(cron = "0 0 22 * * *")
	 public void consultaDireccionamientosDiarios() {
		 LogInvocacionWs logInvocacionWs =  logDAO.findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio.CONSULTA_DIRECCIONAMIENTO, Boolean.TRUE);
		 LocalDate fecha = LocalDate.now();
		 LocalDate fechaActual = LocalDate.now();
		 if (logInvocacionWs != null) {
			 fecha = logInvocacionWs.getFechaConsulta().toLocalDate().plusDays(1);
		 } else {
			 fecha = fecha.minusDays(8);
		 }
		 while (fecha.isBefore(fechaActual) || fecha.equals(fechaActual)) {
			 direccionamientoLogica.procesaDireccionamientos(formatter.format(fecha));
			 fecha = fecha.plusDays(1);
		 }
	 }
	 
	 	 
	 @Scheduled(cron = "0 0/30 0 * * *")
	 public void registraProgramacion() {
		 direccionamientoLogica.registrarProgramacion();
	 }
	 
	 @Scheduled(cron = "0 0 1 * * *")
	 public void registraEntregas() {
		 direccionamientoLogica.registrarEntrega();
	 }
	 
	 @Scheduled(cron = "0 0/30 1 * * *")
	 public void reportaEntrega() {
		 direccionamientoLogica.registrarReporteEntrega();
	 }
	
	 @Scheduled(cron = "0 0 2 * * *")
	 public void registraAnulaciones() {
		 direccionamientoLogica.registrarAnulaciones();
	 }
	 
}
