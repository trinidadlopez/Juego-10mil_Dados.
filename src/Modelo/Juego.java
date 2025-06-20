package Modelo;

import Controlador.Observador;

import java.util.ArrayList;


public class Juego implements Observable{
    private ArrayList<Jugador> jugadores;
    private Jugador ganador_parcial;
    private ArrayList<Lanzamientos> lanzamientos;
    private ReglaPuntos puntaje;
    private Jugador jugadorActual;
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Juego() {
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public ArrayList<Lanzamientos> getLanzamientos() {
        return lanzamientos;
    }

    public void iniciar_juego() {
        lanzamientos = new ArrayList<>();
        jugadores = new ArrayList<>();
        int i = 0;
        notifyMessage("PEDIR CANTIDAD JUGADORES"); //pedir_cantidad_jugadores();
        ganador_parcial=jugadores.get(i);
        while (ganador_parcial.getPuntajeTotal() < 10000) {
            jugadorActual = jugadores.get(i);
            notifyMessage("NOTIFICAR TURNO"); //System.out.println("Turno de " + jugadorActual.getNombreJugador());
            Cubilete cubilete = new Cubilete();
            Lanzamientos lanzamiento1 = new Lanzamientos(cubilete, jugadores.get(i));
            puntaje=new ReglaPuntos();
            lanzamientos.add(lanzamiento1);
            notifyMessage("INICIAR CONTROLADOR LANZAMIENTO");
            lanzamiento1.iniciar_lanzamiento();
            int puntosRonda = jugadores.get(i).getPuntajeParcial();
            if (puntosRonda > 0) {
                puntaje.sumarPuntaje(puntosRonda, jugadores.get(i));
            }
            jugadores.get(i).setPuntajeParcial(0);  //me aseguro de que cada turno empiece con puntaje parcial desde cero

            puntaje.sumarPuntaje(jugadores.get(i).getPuntajeParcial(), jugadores.get(i));
            notifyMessage("NOTIFICAR PUNTAJE TOTAL"); //System.out.println("Tu puntaje total, incluida esta partida, es de: " + jugadores.get(i).getPuntajeTotal() + " puntos\n" );
            i++; //para avanzar al siguiente turno
            i = i % jugadores.size();
            ganador_parcial=puntaje.determinar_quien_gano(jugadores);
        }
        System.out.println("El ganador del juego es: " + ganador_parcial.getNombreJugador());//notifyMessage("NOTIFICAR GANADOR")
    }


    public void ingresar_jugadores(int cantidad){
        for(int i=0; i<cantidad;i++){
            notifyMessage("PEDIR NOMBRE AL JUGADOR");
        }
    }
    public void inicializar_jugador(String nombre){
        Jugador nuevoJugador=new Jugador(nombre,new ArrayList<>());
        jugadores.add(nuevoJugador);

    }
    /*public void pedir_cantidad_jugadores(){
            jugadores = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            int cantJugadores=0;

            while(cantJugadores<2){
                System.out.println("Ingrese la cantidad de jugadores que van a jugar: ");
                cantJugadores = scanner.nextInt();
            }

            for(int i=0; i<cantJugadores; i++){
                System.out.println("Ingrese el nombre del jugador " + (i+1) + ": ");
                String nombre = scanner.next();
                Jugador nuevoJugador = new Jugador(nombre, new ArrayList<>());
                jugadores.add(nuevoJugador);
            }
        }*/
    @Override
    public void attach(Observador anObserver) {
        observadores.add(anObserver);
    }

    @Override
    public void detach(Observador anObserver) {
    }

    @Override
    public void notifyMessage(Object object) {
        for(Observador observador : observadores){
            observador.update(object);
        }
    }
}
