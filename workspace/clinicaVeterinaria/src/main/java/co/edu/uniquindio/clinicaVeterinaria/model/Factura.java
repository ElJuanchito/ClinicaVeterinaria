package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final AtomicLong cuentaF = new AtomicLong(0);
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
	 * @param diagnostico
	 * @param tratamiento
	 * @param atencionVeterinaria
	 */
	public Factura(Double costo, String diagnostico, String tratamiento, AtencionVeterinaria atencionVeterinaria) {
		super();
		this.id = cuentaF.get();
		this.costo = costo;
		this.fecha = LocalDateTime.now();
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.cliente = atencionVeterinaria.getMascota().getDueno();
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

	public static void incrementLong() {
		cuentaF.incrementAndGet();
	}

	public static long getLong() {
		return cuentaF.get();
	}

	public boolean tieneCodigo(Long codigo) {
		String codigoString = codigo + "";
		return (this.id + "").startsWith(codigoString);
	}

	public String getNombreMascota() {
		return atencionVeterinaria.getMascota().getNombre();
	}

}
