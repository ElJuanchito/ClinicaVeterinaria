package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Clinica {
	private Veterinario[] veterinarios;
	private Set<AtencionVeterinaria> citas;
	private Map<Long, Factura> facturas;
	
	/**
	 * Constructor de la clase <b>Clinica</b>
	 */
	public Clinica() {
		veterinarios = new Veterinario[4];
		citas = new HashSet<AtencionVeterinaria>();
		facturas = new HashMap<Long, Factura>();
	}

	public Veterinario[] getVeterinarios() {
		return veterinarios;
	}

	public void setVeterinarios(Veterinario[] veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Set<AtencionVeterinaria> getCitas() {
		return citas;
	}

	public void setCitas(Set<AtencionVeterinaria> citas) {
		this.citas = citas;
	}

	public Map<Long, Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Map<Long, Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(veterinarios);
		result = prime * result + Objects.hash(citas, facturas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clinica other = (Clinica) obj;
		return Objects.equals(citas, other.citas) && Objects.equals(facturas, other.facturas)
				&& Arrays.equals(veterinarios, other.veterinarios);
	}

	@Override
	public String toString() {
		return "Clinica [veterinarios=" + Arrays.toString(veterinarios) + ", citas=" + citas + ", facturas=" + facturas
				+ "]";
	}
	
	
}
