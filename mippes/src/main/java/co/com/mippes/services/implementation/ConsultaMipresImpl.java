package co.com.mippes.services.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.com.mippes.dao.LogInvocacionWsDAO;
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
import co.com.mippes.entity.LogInvocacionWs;
import co.com.mippes.enumerator.CodigoServicio;
import co.com.mippes.services.ConsultaMipres;

@Service
public class ConsultaMipresImpl implements ConsultaMipres {

	@Value("${mipres.token}")
	private String token;

	@Value("${mipres.base.url}")
	private String baseUrl;

	private String generarToken = "GenerarToken/";

	private String direccionamiento = "DireccionamientoXFecha/";

	private String urlProgramacion = "Programacion/";

	private String urlAnulacion = "AnularProgramacion/";
	
	private String urlEntrega = "Entrega/";
	
	private String urlReporteEntrega = "ReporteEntrega/";
	
	// Consulta 
	
	private String urlConsultaProgramaciones = "ProgramacionXFecha/";
	
	private String urlConsultaEntrega = "EntregaXFecha/";
	
	private String urlConsultaReporteEntrega = "ReporteEntregaXFecha/";
	

	@Autowired
	private LogInvocacionWsDAO logInvocacionWsDAO;

	@Override
	public String validaToken(String nit) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl + generarToken + nit + "/" + token,
				String.class);
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			return responseEntity.getBody().replaceAll("\"", "");
		}
		return null;
	}

	@Override
	public List<DireccionamientoDTO> consultaDireccionamiento(String nit, String fecha) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.CONSULTA_DIRECCIONAMIENTO);
		List<DireccionamientoDTO> direccionamientos = new ArrayList<>();
		try {
			String tokenMipres = this.validaToken(nit);
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + direccionamiento + nit + "/" + tokenMipres + "/" + fecha;
			logInvocacionWs.setUrl(url);
			ResponseEntity<List<DireccionamientoDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<DireccionamientoDTO>>() {
					});
			logInvocacionWs.setCodigoRespuesta(responseEntity.getStatusCode().value());
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				logInvocacionWs.setExitoso(Boolean.TRUE);
				Gson gson = new Gson();
				logInvocacionWs.setSalida(gson.toJson(responseEntity.getBody()));
				direccionamientos = responseEntity.getBody();
			}
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return direccionamientos;
	}

	@Override
	public RespuestaProgramacionDTO registarProgramacion(String nit, ProgramacionDTO programacion,
			Integer idDireccionamiento) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.REPORTAR_PROGRAMACION);
		logInvocacionWs.setDireccionamiento(new Direccionamiento(idDireccionamiento));
		try {
			String tokenMipres = this.validaToken(nit);
			String url = baseUrl + urlProgramacion + nit + "/" + tokenMipres;
			RestTemplate restTemplate = new RestTemplate();
			logInvocacionWs.setUrl(url);
			Gson gson = new Gson();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(programacion), headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			logInvocacionWs.setCodigoRespuesta(respEntity.getStatusCode().value());
			String respuesta = respEntity.getBody();
			logInvocacionWs.setSalida(respuesta);
			logInvocacionWs.setExitoso(Boolean.TRUE);
			respuesta = respuesta.replace("[", "").replace("]", "");
			return gson.fromJson(respuesta, RespuestaProgramacionDTO.class);
		} catch (HttpClientErrorException httpException) {
			logInvocacionWs.setCodigoRespuesta(httpException.getStatusCode().value());
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(httpException.getResponseBodyAsString());
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return null;
	}

	@Override
	public String registaAnulacion(String nit, Integer idProgramacion, Integer idDireccionamiento) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.REPORTAR_ANULACION);
		logInvocacionWs.setDireccionamiento(new Direccionamiento(idDireccionamiento));
		try {
			String tokenMipres = this.validaToken(nit);
			String url = baseUrl + urlAnulacion + nit + "/" + tokenMipres + "/" + idProgramacion;
			RestTemplate restTemplate = new RestTemplate();
			logInvocacionWs.setUrl(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			logInvocacionWs.setCodigoRespuesta(respEntity.getStatusCode().value());
			logInvocacionWs.setSalida(respEntity.getBody());
			logInvocacionWs.setExitoso(Boolean.TRUE);
			return respEntity.getBody();
		} catch (HttpClientErrorException httpException) {
			logInvocacionWs.setCodigoRespuesta(httpException.getStatusCode().value());
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(httpException.getResponseBodyAsString());
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return null;
	}

	@Override
	public RespuestaEntregaDTO registrarEntrega(String nit, EntregaDTO entrega, Integer idDireccionamiento) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.REPORTAR_ENTREGADO);
		logInvocacionWs.setDireccionamiento(new Direccionamiento(idDireccionamiento));
		try {
			Gson gson = new Gson();
			String tokenMipres = this.validaToken(nit);
			String url = baseUrl + urlEntrega + nit + "/" + tokenMipres;
			RestTemplate restTemplate = new RestTemplate();
			logInvocacionWs.setUrl(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(entrega), headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			logInvocacionWs.setCodigoRespuesta(respEntity.getStatusCode().value());
			String respuesta = respEntity.getBody();
			logInvocacionWs.setSalida(respuesta);
			logInvocacionWs.setExitoso(Boolean.TRUE);
			respuesta = respuesta.replace("[", "").replace("]", "");
			return gson.fromJson(respuesta, RespuestaEntregaDTO.class);
		} catch (HttpClientErrorException httpException) {
			logInvocacionWs.setCodigoRespuesta(httpException.getStatusCode().value());
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(httpException.getResponseBodyAsString());
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return null;		
	}

	@Override
	public RespuestaReporteEntregaDTO registrarReporteEntrega(String nit, ReporteEntregaDTO reporteEntrega, Integer idDireccionamiento) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.REPORTAR_REPORTE_ENTREGADO);
		logInvocacionWs.setDireccionamiento(new Direccionamiento(idDireccionamiento));
		try {
			Gson gson = new Gson();
			String tokenMipres = this.validaToken(nit);
			String url = baseUrl + urlReporteEntrega + nit + "/" + tokenMipres;
			RestTemplate restTemplate = new RestTemplate();
			logInvocacionWs.setUrl(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(reporteEntrega), headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			logInvocacionWs.setCodigoRespuesta(respEntity.getStatusCode().value());
			String respuesta = respEntity.getBody();
			logInvocacionWs.setSalida(respuesta);
			logInvocacionWs.setExitoso(Boolean.TRUE);
			respuesta = respuesta.replace("[", "").replace("]", "");
			return gson.fromJson(respuesta, RespuestaReporteEntregaDTO.class);
		} catch (HttpClientErrorException httpException) {
			logInvocacionWs.setCodigoRespuesta(httpException.getStatusCode().value());
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(httpException.getResponseBodyAsString());
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return null;
	}
	
	@Override
	public List<ConsultaProgramacionDTO> consultaProgramaciones(String nit, String fecha) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.CONSULTA_PROGRAMACIONES);		
		List<ConsultaProgramacionDTO> consulta = new ArrayList<>();
		try {
			String tokenMipres = this.validaToken(nit);
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + urlConsultaProgramaciones + nit + "/" + tokenMipres + "/" + fecha;
			logInvocacionWs.setUrl(url);
			ResponseEntity<List<ConsultaProgramacionDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ConsultaProgramacionDTO>>() {
					});
			logInvocacionWs.setCodigoRespuesta(responseEntity.getStatusCode().value());
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				logInvocacionWs.setExitoso(Boolean.TRUE);
				Gson gson = new Gson();
				logInvocacionWs.setSalida(gson.toJson(responseEntity.getBody()));	
				consulta = responseEntity.getBody();
			}
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}	
		return consulta;
	}
	
	@Override
	public List<ConsultaEntregaDTO> consultaEntregas(String nit, String fecha) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.CONSULTA_ENTREGAS);		
		List<ConsultaEntregaDTO> consulta = new ArrayList<>();
		try {
			String tokenMipres = this.validaToken(nit);
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + urlConsultaEntrega + nit + "/" + tokenMipres + "/" + fecha;
			logInvocacionWs.setUrl(url);
			ResponseEntity<List<ConsultaEntregaDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ConsultaEntregaDTO>>() {
					});
			logInvocacionWs.setCodigoRespuesta(responseEntity.getStatusCode().value());
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				logInvocacionWs.setExitoso(Boolean.TRUE);
				Gson gson = new Gson();
				logInvocacionWs.setSalida(gson.toJson(responseEntity.getBody()));	
				consulta = responseEntity.getBody();				
			}
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		return consulta;
	}
	
	@Override
	public void consultaReporteEntregas(String nit, String fecha) {
		LogInvocacionWs logInvocacionWs = new LogInvocacionWs();
		logInvocacionWs.setFechaConsulta(LocalDateTime.now());
		logInvocacionWs.setCodigoServicio(CodigoServicio.CONSULTA_REPORTE_ENTREGAS);		
		List<ConsultaProgramacionDTO> consulta = new ArrayList<>();
		try {
			String tokenMipres = this.validaToken(nit);
			RestTemplate restTemplate = new RestTemplate();
			String url = baseUrl + urlConsultaReporteEntrega + nit + "/" + tokenMipres + "/" + fecha;
			logInvocacionWs.setUrl(url);
			ResponseEntity<List<ConsultaProgramacionDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ConsultaProgramacionDTO>>() {
					});
			logInvocacionWs.setCodigoRespuesta(responseEntity.getStatusCode().value());
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				logInvocacionWs.setExitoso(Boolean.TRUE);
				Gson gson = new Gson();
				logInvocacionWs.setSalida(gson.toJson(responseEntity.getBody()));	
				consulta = responseEntity.getBody();
			}
		} catch (Exception e) {
			logInvocacionWs.setExitoso(Boolean.FALSE);
			logInvocacionWs.setSalida(e.getMessage());
		} finally {
			logInvocacionWsDAO.save(logInvocacionWs);
		}
		
	}
	
	

}
