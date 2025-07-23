package Controlador;

import Modelo.Jugador;
import Modelo.Lanzamientos;
import Modelo.Observable;
import Vista.VistaLanzamientos;

import java.util.ArrayList;

public class ControladorLanzamientos implements Observador{
    private Observable observable;
    private VistaLanzamientos vistaLanzamientos;

    public ControladorLanzamientos(Observable observable, VistaLanzamientos vistaLanzamientos) {
        this.observable = observable;
        this.vistaLanzamientos = vistaLanzamientos;
        observable.attach(this);
    }

    @Override
    public void update(Object object){
        if(object.equals("MOSTRAR DADOS PARCIALES")){ //muestro los dados lanzados en cada lanzamiento
            for(int i=0; i<((Lanzamientos)observable).getDadosParciales().size(); i++){
                vistaLanzamientos.mostrarMensaje(i +  " . " + ((Lanzamientos)observable).getDadosParciales().get(i).getValorCaraSuperior());
            }
        }
        if(object.equals("NO TIENE DADOS CON PUNTOS")){
            vistaLanzamientos.mostrarMensaje("Los dados lanzados no suman puntos(1 o 5) ni forman con combinaciones o escalera. Su puntaje en esta ronda es de 0 puntos.");
        }
        if(object.equals("INFORMAR ESCALERA")){
            vistaLanzamientos.mostrarMensaje("Usted obtuvo una escalera! \n Tu puntaje en esta ronda fue de: 500 puntos! ");
        }
        if(object.equals("INDICES INVALIDOS")){
            vistaLanzamientos.mostrarMensaje("Los indices y/o cantidad seleccionados no son validos. Vuelva a intentarlo");
        }
        if(object.equals("NOTIFICAR NUEVO LANZAMIENTO")){
            vistaLanzamientos.mostrarMensaje("Nuevo lanzamiento: ");
        }
        if(object.equals("NOTIFICAR PUNTAJE RONDA")){
            int puntajeRonda = ((Lanzamientos)observable).getJugadorActual().getPuntajeParcial();
            vistaLanzamientos.mostrarMensaje("Tu puntaje en esta ronda fue de: " + puntajeRonda + " puntos!");
        }
        if(object.equals("SELECCIONE OPCION DESEADA")){
            int numero = vistaLanzamientos.pedir_opcion_deseada();//System.out.println("Seleccione la opcion que desee: 1)Plantarse. 2)Apartar dados y seguir tirando.");
            while(numero!=1 && numero!=2){
                numero=vistaLanzamientos.mensaje_de_error_opcion_deseada(); //System.out.println("El numero que ingreso no es valido, ingrese 1 o 2");
            }
            ((Lanzamientos)observable).establecer_decision(numero);
        }
        if(object.equals("INGRESAR CANTIDAD INDICES")){
            int cantidad = vistaLanzamientos.pedir_cantidad(); //System.out.println("Ingrese la cantidad de dados que quiere apartar: ")
            while(cantidad<=0 || cantidad>((Lanzamientos)observable).getDadosParciales().size()){
                cantidad=vistaLanzamientos.mensaje_de_error_cantidad(); //System.out.println("El numero de dados a apartar no es valido, ingrese un numero valido para apartar") y scan;
            }
            ((Lanzamientos)observable).ingresar_indices(cantidad);
        }
        if(object.equals("INGRESAR INDICE")){
            int indice=vistaLanzamientos.pedir_indices(); //System.out.println("Ingrese los indice/s del/los dado/s que quiere apartar(separados por enter): ");
            ((Lanzamientos)observable).guardar_indices(indice);
        }
        if(object.equals("MOSTRAR DADOS APARTADOS")){
            vistaLanzamientos.mostrarMensaje("Dados apartados hasta ahora: ");
            for(int i=0; i< ((Lanzamientos)observable).getJugadorActual().getDadosApartados().size();i++){
                vistaLanzamientos.mostrarMensaje(i + ") " + ((Lanzamientos)observable).getJugadorActual().getDadosApartados().get(i).getValorCaraSuperior());
            }
        }
        if(object.equals("NOTIFICAR DADOS A APARTAR INCORRECTOS")){
            vistaLanzamientos.mostrarMensaje("No es posible apartar el/los dado/s, vuelva a ingresar la cantidad: ");
        }
        if(object.equals("NOTIFICAR USO DE LIMPIAR DADOS")){
            vistaLanzamientos.mostrarMensaje("Ya apartaste todo los dados con puntos! Puedes volver a lanzar los 5 dados nuevamente y seguir sumando en esta ronda.");
        }
    }
}
