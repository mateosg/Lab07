package fp.pedidos.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fp.pedidos.Envio;
import fp.pedidos.EstudioPedidos;
import fp.pedidos.FactoriaEstudioPedidos;

public class TestEstudioPedidos {
	public static void main(String[] args) {
		EstudioPedidos estPed = FactoriaEstudioPedidos.leerEstudioPedidos("data/sales.csv");
//		testLeerEstudioPedidos(estPed);
		testGetTotalPedidosPorEnvio(estPed, Set.of(Envio.PLUS, Envio.PRIORITY), 1);
		testGetMediaPrecioPorPedidoUsuarioAlemania(estPed, "Fredrick Beveridge", "office supplies");
		testGetUsuarioMasDerrochadorPosicion(estPed, LocalDate.of(2011, 1, 7), 3);
		testGetProductoMayorPrecioPorPais(estPed, LocalDate.of(2011, 1, 4));
		testGetResumenPedidosUsuario(estPed);
	}

	private static void testLeerEstudioPedidos(EstudioPedidos estPed) {
		String msg = String.format("Número de pedidos: %d\n", estPed.getPedidos().size());
		System.out.println(msg);
		estPed.getPedidos().stream().forEach(System.out::println);
	}

	private static void testGetTotalPedidosPorEnvio(EstudioPedidos ep, Set<Envio> envios, Integer mes) {
		System.out.println("\nTEST de getTotalPedidosPorEnvio");
		try {
			String msg = String.format("El total de pedidos para los envíos %s y el mes %d es", envios.toString(), mes);
			System.out.println(msg);
			imprimeMap(ep.getTotalPedidosPorEnvio(envios, mes));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n " + e);
		}
	}

	private static void testGetMediaPrecioPorPedidoUsuarioAlemania(EstudioPedidos ep, String usuario,
			String categoria) {
		System.out.println("\nTEST de getMediaPrecioPorPedidoUsuarioAlemania");
		try {
			Double res = ep.getMediaPrecioPorPedidoUsuarioAlemania(usuario, categoria);
			String msg = String.format(
					"La media de precio por producto en Alemania para el usuario %s y la categoría %s hubiese sido de : %.2f euros",
					usuario, categoria, res);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n " + e);
		}
	}

	private static void testGetUsuarioMasDerrochadorPosicion(EstudioPedidos ep, LocalDate fecha, Integer n) {
		System.out.println("\nTEST de getUsuarioMasDerrochador");
		try {
			String res = ep.getUsuarioMasDerrochadorPosicion(fecha, n);
			System.out.println(
					String.format("El usuario más derrochador en la posicion %d de compras posteriores a %s es %s", n,
							fecha.toString(), res));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n " + e);
		}
	}

	private static void testGetProductoMayorPrecioPorPais(EstudioPedidos ep, LocalDate fecha) {
		System.out.println("\nTEST de getProductoMayorPrecioPorPais");
		try {
			String msg = String.format("Producto mayor precio por pais en %s", fecha);
			System.out.println(msg);
			Map<String, String> res = ep.getProductoMayorPrecioPorPais(fecha);
			imprimeMap(res);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n " + e);
		}
	}

	private static void testGetResumenPedidosUsuario(EstudioPedidos ep) {
		System.out.println("\nTEST de getResumenPedidosUsuario");
		try {
			Map<String, List<Double>> res = ep.getResumenPedidosUsuario();
			imprimeMap(res);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n " + e);
		}
	}

	private static <K, V> void imprimeMap(Map<K, V> map) {
		map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " --> " + entry.getValue()));
	}
}
