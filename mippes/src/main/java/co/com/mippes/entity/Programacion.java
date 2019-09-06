package co.com.mippes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "programacion")
@NoArgsConstructor
@Entity
public @Data class Programacion extends ConsultaCommon {

	@Column(name = "IDProgramacion")
	private Integer iDProgramacion;

	@Column(name = "FecMaxEnt")
	private String fecMaxEnt;

	@Column(name = "TipoIDSedeProv")
	private String tipoIDSedeProv;

	@Column(name = "NoIDSedeProv")
	private String noIDSedeProv;

	@Column(name = "CodSedeProv")
	private String codSedeProv;

	@Column(name = "CodSerTecAEntregar")
	private String codSerTecAEntregar;

	@Column(name = "CantTotAEntregar")
	private String cantTotAEntregar;

	@Column(name = "FecProgramacion")
	private String fecProgramacion;

	@Column(name = "EstProgramacion")
	private String estProgramacion;

}