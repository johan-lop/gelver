package co.com.mippes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "direccionamiento")
@Entity
public class Direccionamiento {

	@Column(name = "id")
    private Integer id;
	
	@Column(name = "id_direccionamiento")
	private Integer idDireccionamiento;
	
	@Column(name = "no_rescripcion")
	private String noPrescripcion;
	
	@Column(name = "tipo_tec")
	private String tipoTec;
	
	@Column(name = "con_tec")
	private Integer conTec;
	
	@Column(name = "tipo_id_paciente")
	private String tipoIdPaciente;
	
	@Column(name = "numero_id_paciente")
	private String numeroIdPaciente;
	
	@Column(name = "no_entrega")
	private Integer noEntrega;
	
	@Column(name = "no_sub_entrega")
	private Integer noSubEntrega;
	
	@Column(name = "tipo_id_proveedor")
	private String tipoIdProv;
	
	@Column(name = "no_id_proveedor")
	private String noIdProv;
	
	@Column(name = "cod_mun_entrega")
	private String codMunEnt;
	
	@Column(name = "fecha_maxima_entrega")
	private String fecMaxEnt;
	
	@Column(name = "cant_total_entregar")
	private String cantTotAEntregar;
	
	@Column(name = "direccion_paciente")
	private String dirPaciente;
	
	@Column(name = "cod_servicio_tecnico")
	private String codSerTecAEntregar;
	
	@Column(name = "no_id_eps")
	private String noIDEPS;
	
	@Column(name = "codigo_eps")
	private String codEPS;
	
	@Column(name = "fecha_direccionamiento")
	private String fecDireccionamiento;
	
	@Column(name = "estado_direccionamiento")
	private Integer estDireccionamiento;
	
	@Column(name = "fecha_anulacion")
    private String fecAnulacion;
	
	@Column(name = "id_programacion")
    private Integer idProgramacion;
	
	@Column(name = "es_programado")
    private Boolean esProgramado;
	
	@Column(name = "reportado_programado")
    private Boolean reportadoProgramado;
	
	@Column(name = "es_anulado")
    private Boolean esAnulado;
	
	@Column(name = "reportado_programado")
    private Boolean reportadoAnulado;
	
	@Column(name = "es_entregado")
    private Boolean esEntregado;
	
	@Column(name = "reportado_entregado")
    private Boolean reportadoEntregado;
}