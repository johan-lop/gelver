package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

public @Data class ConsultaEntregaDTO extends ConsultaCommonDTO {

	@JsonProperty("IDEntrega")
	@SerializedName("IDEntrega")
	private Integer iDEntrega;
	
	@JsonProperty("CodSerTecEntregado")
	@SerializedName("CodSerTecEntregado")
    private String codSerTecEntregado;
	
	@JsonProperty("CantTotEntregada")
	@SerializedName("CantTotEntregada")
    private String cantTotEntregada;
	
	@JsonProperty("EntTotal")
	@SerializedName("EntTotal")
    private String entTotal;
    
	@JsonProperty("CausaNoEntrega")
	@SerializedName("CausaNoEntrega")
	private String causaNoEntrega;
    
	@JsonProperty("FecEntrega")
	@SerializedName("FecEntrega")
	private String fecEntrega;
    
	@JsonProperty("NoLote")
	@SerializedName("NoLote")
	private String noLote;
    
	@JsonProperty("TipoIDRecibe")
	@SerializedName("TipoIDRecibe")
	private String tipoIDRecibe;
    
	@JsonProperty("NoIDRecibe")
	@SerializedName("NoIDRecibe")
	private String noIDRecibe;
    
	@JsonProperty("EstEntrega")
	@SerializedName("EstEntrega")
	private String estEntrega;
    
	
}
