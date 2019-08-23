package co.com.mippes.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.dto.ProgramacionDTO;
import co.com.mippes.services.ConsultaMipres;

@Service
public class ConsultaMipresImpl implements ConsultaMipres {

	private String token = "TOKEN_PROV";
	private String baseUrl = "https://tablas.sispro.gov.co/WSSUMMIPRESNOPBS/api/";
	private String generarToken = "GenerarToken/";
	private String direccionamiento = "DireccionamientoXFecha/";
	private String programacion = "Programacion/";

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
		String token = this.validaToken(nit);
		RestTemplate restTemplate = new RestTemplate();
		String url = baseUrl + direccionamiento + nit + "/" + token + "/" + fecha;
		ResponseEntity<List<DireccionamientoDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DireccionamientoDTO>>() {
				});
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			return responseEntity.getBody();
		}
		return new ArrayList<>();
	}

	@Override
	public void registarProgramacion(String nit) {
		String token = this.validaToken(nit);
		String url = baseUrl + programacion + nit + "/" + token;
		RestTemplate restTemplate = new RestTemplate();

		ProgramacionDTO programacion = new ProgramacionDTO();
		programacion.setId(1);
		programacion.setFechaMaximaEntrega("2019-02-21");
		programacion.setTipoIDSedeProv("NI");
		programacion.setCodSedeProv("EPS05");
		programacion.setCodSerTecAEntregar("1");
		programacion.setCantTotAEntregar("1");
		programacion.setNoIDSedeProv("2");

		Gson gson = new Gson();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(programacion), headers);
		
		try {
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			String resp = respEntity.getBody();
			System.out.println(resp);
		} catch (HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
			System.out.print(e.getMessage());
		}
	

	}
	
	
	
	public void registarProgramacion1(String nit) {
		String token = this.validaToken(nit);
		String url = baseUrl + programacion + nit + "/" + token;
		RestTemplate restTemplate = new RestTemplate();

		ProgramacionDTO programacion = new ProgramacionDTO();
		programacion.setId(1);
		programacion.setFechaMaximaEntrega("2019-02-21");
		programacion.setTipoIDSedeProv("NI");
		programacion.setCodSedeProv("EPS05");
		programacion.setCodSerTecAEntregar("1");
		programacion.setCantTotAEntregar("1");
		programacion.setNoIDSedeProv("2");

		try {
			restTemplate.put(url, programacion);
		} catch (HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
			System.out.print(e.getMessage());
		}

		
	}

}
