package co.com.mippes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.mippes.entity.Direccionamiento;

public interface DireccionamientoDAO extends JpaRepository<Direccionamiento, Integer>{

	@Query("SELECT d FROM Direccionamiento d WHERE d.esProgramado = true AND (d.reportadoProgramado is null or d.reportadoProgramado =false)")	
	public List<Direccionamiento> obtenerProgramadosNoReportados();
	
	@Query("SELECT d FROM Direccionamiento d WHERE d.esEntregado = true AND (d.reportadoEntregado is null or d.reportadoEntregado =false)")	
	public List<Direccionamiento> obtenerEntregadosNoReportados();
	
	@Query("SELECT d FROM Direccionamiento d WHERE d.esReporteEntregado = true AND (d.reportadoReporteEntregado is null or d.reportadoReporteEntregado =false)")	
	public List<Direccionamiento> obtenerReporteEntregadosNoReportados();
	
}
