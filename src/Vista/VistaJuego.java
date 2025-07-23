package Vista;

import java.util.Scanner;

public class VistaJuego extends Vista{
    public int solicitar_cantidad(){
        System.out.println("Bienvenido al juego de dados 10mil!\n");
        System.out.println("Ingrese la cantidad de jugadores que van a jugar: ");
        Scanner scanner = new Scanner(System.in);
        int cantJugadores = scanner.nextInt();
        return cantJugadores;
    }
    public String solicitar_nombreJugador(){
        System.out.println("Ingrese el nombre del/la jugador/a: ");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.next();
        return nombre;
    }

}
