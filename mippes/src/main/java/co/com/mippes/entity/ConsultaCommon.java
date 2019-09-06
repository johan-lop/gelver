package co.com.mippes.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
public @Data class ConsultaCommon {

	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NoPrescripcion")
	private String NoPrescripcion;

	@Column(name="TipoTec")
	private String tipoTec;
	
	
	@Column(name="ConTec")
	private Integer conTec;
	
	@Column(name="TipoIDPaciente")
	private String tipoIDPaciente;
	
	@Column(name="NoIDPaciente")
	private String noIDPaciente;
	
	@Column(name="NoEntrega")
	private Integer noEntrega;
	
	@Column(name="FecAnulacion")
    private String fecAnulacion;
	
}
