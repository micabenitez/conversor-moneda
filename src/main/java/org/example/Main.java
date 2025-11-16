package org.example;

import org.example.record.Conversion;
import org.example.service.ConversorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConversorService service = new ConversorService();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean salir = false;

        while (!salir) {
            showMenu();
            String monedaBase = "", monedaTarget = "";

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> { monedaBase = "USD"; monedaTarget = "ARS"; }
                case 2 -> { monedaBase = "ARS"; monedaTarget = "USD"; }
                case 3 -> { monedaBase = "USD"; monedaTarget = "BRL"; }
                case 4 -> { monedaBase = "ARS"; monedaTarget = "BRL"; }
                case 5 -> { monedaBase = "USD"; monedaTarget = "EUR"; }
                case 6 -> { monedaBase = "EUR"; monedaTarget = "USD"; }
                case 7 -> salir = true;
                default -> System.out.println("Opción inválida");
            }
            if (salir) {
                System.out.println("Finalizando programa...");
                break;
            }

            System.out.print("Ingrese el valor a convertir: ");
            int valorAConvertir = sc.nextInt();
            Conversion conv = service.getConvertedValue(monedaBase, monedaTarget, valorAConvertir);

            System.out.println("-------------------------------------------------");
            response(valorAConvertir, conv.base_code(), conv.conversion_result(), conv.target_code());
            System.out.println("-------------------------------------------------\n");
        }
    }

    private static void showMenu() {
        System.out.println("===========================================");
        System.out.println("\tBienvenido/a al conversor de moneda\t\t");
        System.out.println("===========================================");
        System.out.println("\t1. Dólar a Peso Argentino");
        System.out.println("\t2. Peso Argentino a Dólar");
        System.out.println("\t3. Dólar a Real Brasileño");
        System.out.println("\t4. Peso Argentino a Real Brasileño");
        System.out.println("\t5. Dólar a Euro");
        System.out.println("\t6. Euro a Dólar");
        System.out.println("\t7. Salir");
        System.out.println("-------------------------------------------");
    }

    private static void response(int valor, String moneda1, double conversion, String moneda2) {
        System.out.println(String.format("%d %s equivale a %2f %s", valor, moneda1, conversion, moneda2));
    }
}