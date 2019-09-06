package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

public @Data class RespuestaProgramacionDTO {

	@JsonProperty("Id")
	@SerializedName("Id")
	private Integer id;

	@JsonProperty("IdProgramacion")
	@SerializedName("IdProgramacion")
	private Integer idProgramacion;
	
}
