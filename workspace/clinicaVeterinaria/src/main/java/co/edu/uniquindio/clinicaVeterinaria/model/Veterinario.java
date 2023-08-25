package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Objects;

import javafx.scene.image.Image;

public class Veterinario extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Image foto;

	

	/**
	 * Constructor con parametros de la clase <b>Veterinario</b>
	 * @param nombre
	 * @param correo
	 * @param telefono
	 * @param codigo
	 * @param foto
	 */
	public Veterinario(String nombre, String correo, String telefono, String codigo, Image foto) {
		super(nombre, correo, telefono);
		this.codigo = codigo;
		this.foto = foto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the foto
	 */
	public Image getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(Image foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(codigo);
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
		Veterinario other = (Veterinario) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Veterinario [codigo=" + codigo + ", foto=" + foto + ", Nombre=" + getNombre() + ", Correo="
				+ getCorreo() + ", Telefono=" + getTelefono() + "]";
	}

	

	/*
	private byte[] getByteArrayImg() {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(SwingFXUtils.fromFXImage(foto, null), "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    */
	
	
}
