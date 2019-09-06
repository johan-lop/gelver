package co.com.mippes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.mippes.entity.LogInvocacionWs;
import co.com.mippes.enumerator.CodigoServicio;

public interface LogInvocacionWsDAO extends JpaRepository<LogInvocacionWs, Integer>{

	@Query("SELECT e FROM LogInvocacionWs e WHERE e.direccionamiento.id = :direccionamiento and e.exitoso = true")
	public List<LogInvocacionWs> buscarExitosoPorDireccionamiento(@Param("direccionamiento") Integer idDireccionamiento);
	
	public LogInvocacionWs findTopByCodigoServicioAndExitosoOrderByFechaConsultaDesc(CodigoServicio codigoServicio,Boolean exitoso);
	
}
