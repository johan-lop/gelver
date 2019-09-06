package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

public @Data class RespuestaReporteEntregaDTO {

	@JsonProperty("Id")
	@SerializedName("Id")
	private Integer id;

	@JsonProperty("IdReporteEntrega")
	@SerializedName("IdReporteEntrega")
	private Integer idReporteEntrega;
	
}
