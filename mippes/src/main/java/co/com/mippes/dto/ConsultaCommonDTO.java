package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

public @Data class ConsultaCommonDTO {

	@JsonProperty("ID")
	@SerializedName("ID")
	private Integer id;
	
	@JsonProperty("NoPrescripcion")
	@SerializedName("NoPrescripcion")
	private String NoPrescripcion;

	@JsonProperty("TipoTec")
	@SerializedName("TipoTec")
	private String tipoTec;
	
	
	@JsonProperty("ConTec")
	@SerializedName("ConTec")
	private Integer conTec;
	
	@JsonProperty("TipoIDPaciente")
	@SerializedName("TipoIDPaciente")
	private String tipoIDPaciente;
	
	@JsonProperty("NoIDPaciente")
	@SerializedName("NoIDPaciente")
	private String noIDPaciente;
	
	@JsonProperty("NoEntrega")
	@SerializedName("NoEntrega")
	private Integer noEntrega;
	
	@JsonProperty("FecAnulacion")
	@SerializedName("FecAnulacion")
    private String fecAnulacion;
	
}
