package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramacionDTO {

	@JsonProperty("ID")
	@SerializedName("ID")
	private Integer id;

	@JsonProperty("FecMaxEnt")
	@SerializedName("FecMaxEnt")
	private String fechaMaximaEntrega;

	@JsonProperty("TipoIDSedeProv")
	@SerializedName("TipoIDSedeProv")
	private String tipoIDSedeProv;

	@JsonProperty("NoIDSedeProv")
	@SerializedName("NoIDSedeProv")
	private String noIDSedeProv;

	@JsonProperty("CodSedeProv")
	@SerializedName("CodSedeProv")
	private String CodSedeProv;

	@JsonProperty("CodSerTecAEntregar")
	@SerializedName("CodSerTecAEntregar")
	private String codSerTecAEntregar;

	@JsonProperty("CantTotAEntregar")
	@SerializedName("CantTotAEntregar")
	private String cantTotAEntregar;

}
