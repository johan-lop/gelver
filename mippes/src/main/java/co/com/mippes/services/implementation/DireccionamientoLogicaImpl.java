package co.com.mippes.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.mippes.dao.DireccionamientoDAO;
import co.com.mippes.dao.EntregaDAO;
import co.com.mippes.dao.ProgramacionDAO;
import co.com.mippes.dto.ConsultaEntregaDTO;
import co.com.mippes.dto.ConsultaProgramacionDTO;
import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.dto.EntregaDTO;
import co.com.mippes.dto.ProgramacionDTO;
import co.com.mippes.dto.ReporteEntregaDTO;
import co.com.mippes.dto.RespuestaEntregaDTO;
import co.com.mippes.dto.RespuestaProgramacionDTO;
import co.com.mippes.dto.RespuestaReporteEntregaDTO;
import co.com.mippes.entity.Direccionamiento;
import co.com.mippes.entity.Entrega;
import co.com.mippes.entity.Programacion;
import co.com.mippes.services.ConsultaMipres;
import co.com.mippes.services.DireccionamientoLogica;

@Service
public class DireccionamientoLogicaImpl implements DireccionamientoLogica {

	@Autowired
	private ConsultaMipres consultaMipres;

	@Autowired
	private DireccionamientoDAO direccionamientoDAO;

	@Autowired
	private EntregaDAO entregaDAO;

	@Autowired
	private ProgramacionDAO programacionDAO;

	@Value("${mipres.nit.ips}")
	private String nit;

	@Value("${mipres.sede}")
	private String sedeProveedor;

	@Override
	public List<DireccionamientoDTO> procesaDireccionamientos(String fecha) {
		List<DireccionamientoDTO> direccionamientos = consultaMipres.consultaDireccionamiento(nit, fecha);
		if (!direccionamientos.isEmpty()) {
			direccionamientos.forEach(dir -> {
				ModelMapper modelMapper = new ModelMapper();
				Direccionamiento direccionamientoEntity = modelMapper.map(dir, Direccionamiento.class);
				if (!direccionamientoDAO.existsById(direccionamientoEntity.getId())) {
					direccionamientoDAO.save(direccionamientoEntity);
				}
			});
		}
		return direccionamientos;

	}

	@Override
	public void registrarProgramacion() {
		List<Direccionamiento> direccionamientos = direccionamientoDAO.obtenerProgramadosNoReportados();
		if (!direccionamientos.isEmpty()) {
			direccionamientos.forEach(dir -> {
				ProgramacionDTO programacion = new ProgramacionDTO();
				programacion.setId(dir.getId());
				programacion.setFechaMaximaEntrega(dir.getFecMaxEnt());
				programacion.setTipoIDSedeProv(dir.getTipoIdProv());
				programacion.setCodSedeProv(sedeProveedor);
				programacion.setCodSerTecAEntregar(dir.getCodSerTecAEntregar());
				programacion.setCantTotAEntregar(dir.getCantTotAEntregar());
				programacion.setNoIDSedeProv(dir.getNoIdProv());
				RespuestaProgramacionDTO respuesta = consultaMipres.registarProgramacion(nit, programacion,
						dir.getId());
				if (respuesta != null) {
					dir.setReportadoProgramado(Boolean.TRUE);
					dir.setIdProgramacion(respuesta.getIdProgramacion());
					dir.setFechaProgramacion(LocalDateTime.now());
					direccionamientoDAO.save(dir);
				}
			});
		}

	}

	@Override
	public void registrarAnulaciones() {
		List<Direccionamiento> direccionamientos = direccionamientoDAO.obtenerProgramadosNoReportados();
		if (!direccionamientos.isEmpty()) {
			direccionamientos.forEach(dir -> {
				consultaMipres.registaAnulacion(nit, dir.getIdProgramacion(), dir.getId());
			});
		}

	}

	@Override
	public void registrarEntrega() {
		List<Direccionamiento> direccionamientos = direccionamientoDAO.obtenerEntregadosNoReportados();
		EntregaDTO entrega = new EntregaDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (!direccionamientos.isEmpty()) {
			direccionamientos.forEach(direccionamiento -> {
				entrega.setId(direccionamiento.getId());
				entrega.setCodigoServicioTecnicoEntregado(direccionamiento.getCodSerTecAEntregar());
				entrega.setCantidadTotalEntregada(direccionamiento.getCantTotAEntregar());
				entrega.setEntTotal(direccionamiento.getEntregaTotal());
				entrega.setCausaNoEntrega(direccionamiento.getCausaNoEntrega());
				entrega.setFecEntrega(formatter.format(LocalDate.now()));
				entrega.setNoLote("");
				if (direccionamiento.getTipoIdPaciente().equals("RC")
						|| direccionamiento.getTipoIdPaciente().equals("TI")) {
					entrega.setTipoIdRecibe(direccionamiento.getTipoDocAcompanante());
					entrega.setNoIdRecibe(direccionamiento.getNumDocAcompanante());
				} else {
					entrega.setTipoIdRecibe(direccionamiento.getTipoIdPaciente());
					entrega.setNoIdRecibe(direccionamiento.getNumeroIdPaciente());
				}
				RespuestaEntregaDTO respuesta = consultaMipres.registrarEntrega(nit, entrega, direccionamiento.getId());
				if (respuesta != null) {
					direccionamiento.setReportadoEntregado(Boolean.TRUE);
					direccionamiento.setIdEntrega(respuesta.getIdEntrega());
					direccionamiento.setFechaEntrega(LocalDateTime.now());
					direccionamientoDAO.save(direccionamiento);
				}
			});
		}
	}

	@Override
	public void registrarReporteEntrega() {
		List<Direccionamiento> direccionamientos = direccionamientoDAO.obtenerReporteEntregadosNoReportados();
		if (!direccionamientos.isEmpty()) {
			direccionamientos.forEach(direccionamiento -> {
				ReporteEntregaDTO reporteEntrega = new ReporteEntregaDTO();
				reporteEntrega.setId(direccionamiento.getId());
				reporteEntrega.setCausaNoEntrega(direccionamiento.getCausaNoEntrega());
				reporteEntrega.setEstadoEntrega(1);
				reporteEntrega.setValorEntregado(direccionamiento.getValorEntregado());
				RespuestaReporteEntregaDTO respuesta = consultaMipres.registrarReporteEntrega(nit, reporteEntrega,
						direccionamiento.getId());
				if (respuesta != null) {
					direccionamiento.setReportadoReporteEntregado(Boolean.TRUE);
					direccionamiento.setIdReporteEntrega(respuesta.getIdReporteEntrega());
					direccionamiento.setFechaReporte(LocalDateTime.now());
					direccionamientoDAO.save(direccionamiento);
				}
			});

		}
	}

	@Override
	public void consultaProgramaciones(String fecha) {
		List<ConsultaProgramacionDTO> consulta = consultaMipres.consultaProgramaciones(nit, fecha);
		consulta.forEach(con -> {
			ModelMapper mapper = new ModelMapper();
			Programacion programacion = mapper.map(con, Programacion.class);
			programacionDAO.save(programacion);
		});
	}

	@Override
	public void consultaEntregas(String fecha) {
		List<ConsultaEntregaDTO> consulta = consultaMipres.consultaEntregas(nit, fecha);
		consulta.forEach(con -> {
			ModelMapper mapper = new ModelMapper();
			Entrega entrega = mapper.map(con, Entrega.class);
			entregaDAO.save(entrega);
		});
	}

	@Override
	public void consultaReporteEntrega(String fecha) {
		// TODO Auto-generated method stub

	}

}
