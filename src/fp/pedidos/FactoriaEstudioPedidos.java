package fp.pedidos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class FactoriaEstudioPedidos {

	public static EstudioPedidos leerEstudioPedidos(String rutaFichero) {
		EstudioPedidos res = null;
		try {
			Stream<Pedido> stPedidos = Files.lines(Paths.get(rutaFichero)).skip(1)
					.map(FactoriaEstudioPedidos::parsearPedido);
			res = new EstudioPedidos(stPedidos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	private static Pedido parsearPedido(String cad) {
		Checkers.checkNoNull(cad);
		String[] trozos = cad.split(";");
		Checkers.check("Formato no válido", trozos.length == 9);
		LocalDate fecha = parsearFecha(trozos[0].trim());
		String usuario = trozos[1].trim();
		String pais = trozos[2].trim();
		String ciudad = trozos[3].trim();
		Envio envio = Envio.valueOf(trozos[4].trim());
		Set<String> categorias = parsearCategorias(trozos[5].trim());
		String producto = trozos[6].trim();
		Double precioUnitario = parsearPrecio(trozos[7].trim());
		Integer unidades = Integer.parseInt(trozos[8].trim());
		return new Pedido(fecha, usuario, pais, ciudad, envio, categorias, producto, precioUnitario, unidades);
	}

	private static Double parsearPrecio(String cad) {
		String cleaned = cad.replace("$", "").replace(".", "");
		return Double.parseDouble(cleaned);
	}

	private static Set<String> parsearCategorias(String cad) {
		String[] trozos = cad.split("and");
		return Arrays.stream(trozos).map(String::trim).collect(Collectors.toSet());
	}

	private static LocalDate parsearFecha(String cad) {
		return LocalDate.parse(cad, DateTimeFormatter.ofPattern("d/M/yy"));
	}

}