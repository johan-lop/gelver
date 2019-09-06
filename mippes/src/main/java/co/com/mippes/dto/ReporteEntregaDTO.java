package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteEntregaDTO {

	@JsonProperty("ID")
	@SerializedName("ID")
	private Integer id;

	@JsonProperty("EstadoEntrega")
	@SerializedName("EstadoEntrega")
	private Integer estadoEntrega;

	@JsonProperty("CausaNoEntrega")
	@SerializedName("CausaNoEntrega")
	private Integer causaNoEntrega;

	@JsonProperty("ValorEntregado")
	@SerializedName("ValorEntregado")
	private String valorEntregado;

	
}

