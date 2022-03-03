package com.ibm.academia.restapi.commons.modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fraudes")
public class Fraude implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "ip", nullable = false, unique = true)
	private String ip;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "nombre_pais", nullable = false)
	private String nombrePais;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "codigo_iso", nullable = false)
	private String codigoIso;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "moneda_local", nullable = false)
	private String monedaLocal;

	@NotNull(message = "No puede ser nulo")
	@Column(name = "cotizacion_euro", nullable = false)
	private BigDecimal cotizacionEuro;

	@NotEmpty(message = "No puede ser vacío")
	@Size(min = 3, max = 100)
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;

	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@Transient
	private Integer puerto;

	public Fraude(Long id, String ip, String nombrePais, String codigoIso, String monedaLocal,
			BigDecimal cotizacionEuro, String usuarioCreacion) {
		this.id = id;
		this.ip = ip;
		this.nombrePais = nombrePais;
		this.codigoIso = codigoIso;
		this.monedaLocal = monedaLocal;
		this.cotizacionEuro = cotizacionEuro;
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fraude [id=");
		builder.append(id);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", nombrePais=");
		builder.append(nombrePais);
		builder.append(", codigoIso=");
		builder.append(codigoIso);
		builder.append(", monedaLocal=");
		builder.append(monedaLocal);
		builder.append(", cotizacionEuro=");
		builder.append(cotizacionEuro);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoIso, cotizacionEuro, ip, monedaLocal, nombrePais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraude other = (Fraude) obj;
		return Objects.equals(codigoIso, other.codigoIso) && Objects.equals(cotizacionEuro, other.cotizacionEuro)
				&& Objects.equals(ip, other.ip) && Objects.equals(monedaLocal, other.monedaLocal)
				&& Objects.equals(nombrePais, other.nombrePais);
	}

	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}

	@PreUpdate
	private void antesActualizar() {
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 1230181249498139351L;

}
