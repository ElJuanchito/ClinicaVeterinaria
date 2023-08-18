package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Factura implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double costo;
	private LocalDate fecha;
	private String observaciones;
	private Cliente cliente;
	private AtencionVeterinaria atencionVeterinaria;
	
	/**
	 * Constructor con parametros de la clase <b>Factura</b>
	 * @param costo
	 * @param fecha
	 * @param observaciones
	 * @param cliente
	 * @param atencionVeterinaria
	 */
	public Factura(Double costo, LocalDate fecha, String observaciones, Cliente cliente,
			AtencionVeterinaria atencionVeterinaria) {
		super();
		this.costo = costo;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.cliente = cliente;
		this.atencionVeterinaria = atencionVeterinaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public AtencionVeterinaria getAtencionVeterinaria() {
		return atencionVeterinaria;
	}

	public void setAtencionVeterinaria(AtencionVeterinaria atencionVeterinaria) {
		this.atencionVeterinaria = atencionVeterinaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", costo=" + costo + ", fecha=" + fecha + ", observaciones=" + observaciones
				+ ", cliente=" + cliente + ", atencionVeterinaria=" + atencionVeterinaria + "]";
	}
	
	
	
}
