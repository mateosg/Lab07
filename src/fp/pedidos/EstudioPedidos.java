package fp.pedidos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstudioPedidos {
	private Set<Pedido> pedidos;

	public EstudioPedidos(Stream<Pedido> sPedidos) {
		this.pedidos = sPedidos.collect(Collectors.toSet());
	}

	public Set<Pedido> getPedidos() {
		return new HashSet<Pedido>(pedidos);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstudioPedidos other = (EstudioPedidos) obj;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		return true;
	}

	public String toString() {
		return "EstudioPedidos [pedidos=" + pedidos + "]";
	}
	/****************************** Tratamientos secuenciales *************************************/
	
	
}
