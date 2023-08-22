package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double costo;
	private LocalDateTime fecha;
	private String diagnostico;
	private String tratamiento;
	private Cliente cliente;
	private AtencionVeterinaria atencionVeterinaria;

	/**
	 * Constructor con parametros de la clase <b>Factura</b>
	 * 
	 * @param costo
	 * @param fecha
	 * @param observaciones
	 * @param cliente
	 * @param atencionVeterinaria
	 */
	public Factura(Double costo, LocalDateTime fecha, String diagnostico, String tratamiento, Cliente cliente,
			AtencionVeterinaria atencionVeterinaria) {
		super();
		this.costo = costo;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.cliente = cliente;
		this.atencionVeterinaria = atencionVeterinaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCosto() {
		return costo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
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

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
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
		return "Factura [id=" + id + ", costo=" + costo + ", fecha=" + fecha + ", diagnostico=" + diagnostico
				+ ", tratamiento=" + tratamiento + ", cliente=" + cliente + ", atencionVeterinaria="
				+ atencionVeterinaria + "]";
	}

}
