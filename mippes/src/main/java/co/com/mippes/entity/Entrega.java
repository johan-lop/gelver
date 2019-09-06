package co.com.mippes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "entrega")
@NoArgsConstructor
@Entity
public @Data class Entrega extends ConsultaCommon{
	
	@Column(name="IDEntrega")
	private Integer iDEntrega;
	
	@Column(name="CodSerTecEntregado")
    private String codSerTecEntregado;
	
	@Column(name="CantTotEntregada")
    private String cantTotEntregada;
	
	@Column(name="EntTotal")
    private String entTotal;
    
	@Column(name="CausaNoEntrega")
	private String causaNoEntrega;
    
	@Column(name="FecEntrega")
	private String fecEntrega;
    
	@Column(name="NoLote")
	private String noLote;
    
	@Column(name="TipoIDRecibe")
	private String tipoIDRecibe;
    
	@Column(name="NoIDRecibe")
	private String noIDRecibe;
    
	@Column(name="EstEntrega")
	private String estEntrega;
}