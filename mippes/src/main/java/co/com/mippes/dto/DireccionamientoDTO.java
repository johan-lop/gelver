package co.com.mippes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionamientoDTO {

	@JsonProperty("ID")
	private Integer id;

	@JsonProperty("IDDireccionamiento")
	private Integer idDireccionamiento;

	@JsonProperty("NoPrescripcion")
	private String noPrescripcion;

	@JsonProperty("TipoTec")
	private String tipoTec;

	@JsonProperty("ConTec")
	private Integer conTec;

	@JsonProperty("TipoIDPaciente")
	private String tipoIdPaciente;

	@JsonProperty("NoIDPaciente")
	private String numeroIdPaciente;

	@JsonProperty("NoEntrega")
	private Integer noEntrega;

	@JsonProperty("NoSubEntrega")
	private Integer noSubEntrega;

	@JsonProperty("TipoIDProv")
	private String tipoIdProv;

	@JsonProperty("NoIDProv")
	private String noIdProv;

	@JsonProperty("CodMunEnt")
	private String codMunEnt;

	@JsonProperty("FecMaxEnt")
	private String fecMaxEnt;

	@JsonProperty("CantTotAEntregar")
	private String cantTotAEntregar;

	@JsonProperty("DirPaciente")
	private String dirPaciente;

	@JsonProperty("CodSerTecAEntregar")
	private String codSerTecAEntregar;

	@JsonProperty("NoIDEPS")
	private String noIDEPS;

	@JsonProperty("CodEPS")
	private String codEPS;

	@JsonProperty("FecDireccionamiento")
	private String fecDireccionamiento;

	@JsonProperty("EstDireccionamiento")
	private Integer estDireccionamiento;

	@JsonProperty("FecAnulacion")
	private String fecAnulacion;

}
