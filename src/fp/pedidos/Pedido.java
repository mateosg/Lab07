package fp.pedidos;

import java.time.LocalDate;
import java.util.Set;

import fp.utiles.Checkers;

public record Pedido(LocalDate fecha, String usuario, String pais, String ciudad, Envio envio, Set<String> categorias,
		String producto, Double precioUnitario, Integer unidades) implements Comparable<Pedido> {

	private static final Double IVA = 0.21;
	public Pedido {
		Checkers.checkNoNull(producto, precioUnitario, unidades);
		Checkers.check("Fecha de pedido no válida", LocalDate.now().isAfter(fecha));
		Checkers.check("Precio unitario no válido", precioUnitario >= 0);
		Checkers.check("Unidades no válidas", unidades >= 0);
	}

	public Double precioTotal() {
		Double precio = precioUnitario() * unidades();
		return precio + precio * IVA;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pedido))
			return false;
		Pedido other = (Pedido) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public int compareTo(Pedido p) {
		int res = fecha().compareTo(p.fecha());
		if (res == 0) {
			res = usuario().compareTo(p.usuario());
			if (res == 0) {
				res = producto().compareTo(p.producto());
			}
		}
		return res;
	}

	public String toString() {
		return "Pedido [fecha=" + fecha + ", nombre=" + usuario + ", pais=" + pais + ", ciudad=" + ciudad
				+ ", tipoEnvio=" + envio + ", categorias=" + categorias + ", producto=" + producto + ", precioUnitario="
				+ precioUnitario + ", unidades=" + unidades + ", precioTotal=" + precioTotal() + "]";
	}

}
