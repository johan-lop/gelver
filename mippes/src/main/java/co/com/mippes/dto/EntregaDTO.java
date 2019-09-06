package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTO {

	@JsonProperty("ID")
	@SerializedName("ID")
	private Integer id;

	@JsonProperty("CodSerTecEntregado")
	@SerializedName("CodSerTecEntregado")
	private String codigoServicioTecnicoEntregado;

	@JsonProperty("CantTotEntregada")
	@SerializedName("CantTotEntregada")
	private String cantidadTotalEntregada;

	@JsonProperty("EntTotal")
	@SerializedName("EntTotal")
	private Integer entTotal;

	@JsonProperty("CausaNoEntrega")
	@SerializedName("CausaNoEntrega")
	private Integer causaNoEntrega;

	@JsonProperty("FecEntrega")
	@SerializedName("FecEntrega")
	private String fecEntrega;

	@JsonProperty("NoLote")
	@SerializedName("NoLote")
	private String noLote;
	
	@JsonProperty("TipoIDRecibe")
	@SerializedName("TipoIDRecibe")
	private String tipoIdRecibe;
	
	@JsonProperty("NoIDRecibe")
	@SerializedName("NoIDRecibe")
	private String noIdRecibe;

}

