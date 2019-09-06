package co.com.mippes.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.mippes.enumerator.CodigoServicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "log_invocacion_ws")
@Entity
public class LogInvocacionWs {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "fecha_consulta")
	private LocalDateTime fechaConsulta;
	
	@Column(name = "entrada")
	private String entrada;
	
	@Column(name = "salida", columnDefinition = "TEXT")	
	private String salida;
	
	@Column(name = "exitoso")
	private Boolean exitoso;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "codigo_respuesta")
	private Integer codigoRespuesta;
	
	@ManyToOne
	@JoinColumn(name = "direccionamiento_id")
	private Direccionamiento direccionamiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="codigo_servicio")
	private CodigoServicio codigoServicio;
	
	
	
}