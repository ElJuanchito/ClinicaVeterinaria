package co.edu.uniquindio.clinicaVeterinaria.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AtencionVeterinaria {
	private LocalDateTime fecha;
	private Estado estado;
	private Mascota mascota;
	private Veterinario veterinario;

	/**
	 * Constructor base de la clase <b>AtencionVeterinaria</b>
	 */
	public AtencionVeterinaria() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parametros de la clase <b>AtencionVeterinaria</b>
	 * 
	 * @param fecha
	 * @param estado
	 * @param mascota
	 * @param veterinario
	 */
	public AtencionVeterinaria(LocalDateTime fecha, Estado estado, Mascota mascota, Veterinario veterinario) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtencionVeterinaria other = (AtencionVeterinaria) obj;
		return Objects.equals(fecha, other.fecha);
	}

	@Override
	public String toString() {
		return "AtencionVeterinaria [fecha=" + fecha + ", estado=" + estado + ", mascota=" + mascota + ", veterinario="
				+ veterinario + "]";
	}

}
