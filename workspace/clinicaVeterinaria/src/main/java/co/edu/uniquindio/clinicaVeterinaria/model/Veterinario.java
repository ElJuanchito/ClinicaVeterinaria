package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Veterinario extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private byte[] imagenData;

	

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
		this.imagenData = getByteArrayImg(foto);
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
		ByteArrayInputStream bais = new ByteArrayInputStream(imagenData);
		return new Image(bais);
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
		return "Veterinario [codigo=" + codigo + ", Nombre=" + getNombre() + ", Correo="
				+ getCorreo() + ", Telefono=" + getTelefono() + "]";
	}
	
	private byte[] getByteArrayImg(Image imagen) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
}
