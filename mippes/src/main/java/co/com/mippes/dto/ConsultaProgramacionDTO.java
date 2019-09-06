package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

public @Data class ConsultaProgramacionDTO extends ConsultaCommonDTO {

	@JsonProperty("IDProgramacion")
	@SerializedName("IDProgramacion")
	private Integer iDProgramacion;
	
	@JsonProperty("FecMaxEnt")
	@SerializedName("FecMaxEnt")
    private String fecMaxEnt;
	
	@JsonProperty("TipoIDSedeProv")
	@SerializedName("TipoIDSedeProv")
    private String tipoIDSedeProv;
	
	@JsonProperty("NoIDSedeProv")
	@SerializedName("NoIDSedeProv")
    private String noIDSedeProv;
	
	@JsonProperty("CodSedeProv")
	@SerializedName("CodSedeProv")
    private String codSedeProv;
	
	@JsonProperty("CodSerTecAEntregar")
	@SerializedName("CodSerTecAEntregar")
    private String codSerTecAEntregar;
	
	@JsonProperty("CantTotAEntregar")
	@SerializedName("CantTotAEntregar")
    private String cantTotAEntregar;
	
	@JsonProperty("FecProgramacion")
	@SerializedName("FecProgramacion")
    private String fecProgramacion;
	
	@JsonProperty("EstProgramacion")
	@SerializedName("EstProgramacion")
    private String estProgramacion;
	
	

}

