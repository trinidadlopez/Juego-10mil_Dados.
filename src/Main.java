import Controlador.ControladorJuego;
import Controlador.ControladorLanzamientos;
import Modelo.Juego;
import Vista.VistaJuego;

public class Main {
    public static void main(String[] args) {
        Juego juego1=new Juego();
        ControladorJuego controladorJuego= new ControladorJuego(juego1, new VistaJuego());
        juego1.iniciar_juego();
    }
}
