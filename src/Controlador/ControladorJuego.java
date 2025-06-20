package Controlador;

import Modelo.Juego;
import Modelo.Observable;
import Vista.VistaJuego;
import Vista.VistaLanzamientos;

public class ControladorJuego implements Observador{
    private Observable observable;
    private VistaJuego vistaJuego;

    public ControladorJuego(Observable observable, VistaJuego vistaJuego) {
        this.observable = observable;
        this.vistaJuego = vistaJuego;
        observable.attach(this);
    }

    @Override
    public void update(Object object){

        if(object.equals("PEDIR CANTIDAD JUGADORES")){ //pide la cantidad de jugadores que van a ser
            int cantidad= vistaJuego.solicitar_cantidad();
            ((Juego)observable).ingresar_jugadores(cantidad);
        }
        if(object.equals("PEDIR NOMBRE AL JUGADOR")){ //le pongo el nombre a cada jugador
            String nombre = vistaJuego.solicitar_nombreJugador();
            ((Juego)observable).inicializar_jugador(nombre);
        }
        if(object.equals("NOTIFICAR TURNO")){  //muestro de quien es el turno actual
            vistaJuego.mostrarMensaje(" Turno de: " + ((Juego)observable).getJugadorActual().getNombreJugador());
        }
        if(object.equals("NOTIFICAR PUNTAJE TOTAL")){ //muestro el puntaje total del jugador actual, una vez que finaliza su mano
            vistaJuego.mostrarMensaje("Tu puntaje total, incluida esta partida, es de: " + ((Juego)observable).getJugadorActual().getPuntajeTotal() + " puntos\n");
        }
        if(object.equals("INICIAR CONTROLADOR LANZAMIENTO")){ //inicializo el controlador del lanzamiento
            int index=((Juego)observable).getLanzamientos().size()-1;
            ControladorLanzamientos controladorLanzamientos=new ControladorLanzamientos(((Juego)observable).getLanzamientos().get(index),new VistaLanzamientos());
        }
    }

}
