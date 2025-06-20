package Modelo;

import Controlador.Observador;

import java.util.ArrayList;
import java.util.Scanner;

public class Lanzamientos implements Observable{
    private ArrayList<Dado> dadosParciales; //fisicamente son los dados en mesa
    private final Cubilete cubilete;
    private Jugador jugadorActual;
    private Reglas regla;
    private ReglaPuntos regla_puntos;
    private ArrayList<Observador> observadores = new ArrayList<>();
    private int opcion_seleccionada;
    private ArrayList<Integer> indices = new ArrayList<>();


    public Lanzamientos(Cubilete cubilete, Jugador jugadorActual) {
        this.cubilete = cubilete;
        this.jugadorActual = jugadorActual;
    }

    public void lanzar(){
        dadosParciales = new ArrayList<>(); //reinicio la lista de dadosParciales
        for(int i=0;i<cubilete.getDados().size();i++){
            if(jugadorActual.getDadosApartados().contains(cubilete.getDados().get(i)) == false){ //si el dado no esta apartado
                cubilete.getDados().get(i).tirarse(); //le vuelvo a generar un numero sobre ese dado
                dadosParciales.add(cubilete.getDados().get(i)); //y lo agrego a lista de dados parciales
            }
        }
    }

    public void iniciar_lanzamiento(){
        dadosParciales=new ArrayList<>();
        lanzar();
        regla = new Reglas();
        regla_puntos= new ReglaPuntos();
        regla.tieneDadosConPuntos(dadosParciales);
        if(regla.tieneDadosConPuntos(dadosParciales) == false){
            notifyMessage("MOSTRAR DADOS PARCIALES"); //System.out.println(i + " . " + dadosParciales.get(i).getValorCaraSuperior());
            notifyMessage("NO TIENE DADOS CON PUNTOS");//System.out.println("Los dados lanzados no suman puntos. Su puntaje en esta ronda es 0");
            return;
        }
        notifyMessage("MOSTRAR DADOS PARCIALES");

        int[] conteoEscalera= regla.cantidadDados(dadosParciales);
        if (regla.tieneEscalera(conteoEscalera)){
            notifyMessage("INFORMAR ESCALERA"); //System.out.println("Usted obtuvo una escalera! \n Tu puntaje en esta ronda fue de: 500 puntos! ");
            jugadorActual.setPuntajeParcial(500);
            return;
        }

        notifyMessage("SELECCIONE OPCION DESEADA");

        int cantidad=0;
        while(opcion_seleccionada==2){
            apartarDados();
            notifyMessage("MOSTRAR DADOS APARTADOS");

            boolean eleccionValida = regla.verificar_si_puede_apartar(jugadorActual.getDadosApartados());
            while(eleccionValida == false) {
                notifyMessage("NOTIFICAR DADOS A APARTAR INCORRECTOS");//System.out.println("No es posible apartar el/los dado/s, vuelva a ingresar la cantidad: ");
                for (int i = jugadorActual.getDadosApartados().size() - 1; i >= cantidad; i--) {
                    jugadorActual.getDadosApartados().remove(i);
                }
                apartarDados();

                eleccionValida = regla.verificar_si_puede_apartar(jugadorActual.getDadosApartados());
            }
            if(jugadorActual.getDadosApartados().size()==5){
                int calculoPuntosParciales= regla_puntos.calcularPuntaje(jugadorActual.getDadosApartados()); //este calcula los puntos de los dados apartados una vez que relance.
                jugadorActual.setPuntajeParcial(calculoPuntosParciales + jugadorActual.getPuntajeParcial());
                notifyMessage("NOTIFICAR USO DE LIMPIAR DADOS");//System.out.println("Ya apartaste todo los dados con puntos! Puedes volver a lanzar los 5 dados nuevamente y seguir sumando en esta ronda.");
                jugadorActual.getDadosApartados().clear();

                notifyMessage("SELECCIONE OPCION DESEADA");

                if(opcion_seleccionada==1){
                    int puntosParciales= regla_puntos.calcularPuntaje(dadosParciales);
                    int puntosApartados = regla_puntos.calcularPuntaje(jugadorActual.getDadosApartados());
                    jugadorActual.setPuntajeParcial(puntosParciales +puntosApartados);
                    notifyMessage("NOTIFICAR PUNTAJE RONDA");//System.out.println("Tu puntaje en esta ronda fue de: " + totalRonda + " puntos!");
                    jugadorActual.getDadosApartados().clear();
                }

            }
            lanzar();
            regla.tieneDadosConPuntos(dadosParciales);
            notifyMessage("NOTIFICAR NUEVO LANZAMIENTO");//System.out.println("Nuevo lanzamiento: ");
            notifyMessage("MOSTRAR DADOS PARCIALES");
            if(regla.tieneDadosConPuntos(dadosParciales) == false){
                notifyMessage("NO TIENE DADOS CON PUNTOS");//System.out.println("Los dados lanzados no suman puntos. Su puntaje en esta ronda es 0");
                jugadorActual.setPuntajeParcial(0);
                jugadorActual.getDadosApartados().clear();
                return;
            }
            notifyMessage("SELECCIONE OPCION DESEADA");
        }
        //si la decision es 1(plantarse)
        int puntosParciales= regla_puntos.calcularPuntaje(dadosParciales);
        int puntosApartados = regla_puntos.calcularPuntaje(jugadorActual.getDadosApartados());
        jugadorActual.setPuntajeParcial(puntosParciales +puntosApartados);
        notifyMessage("NOTIFICAR PUNTAJE RONDA");//System.out.println("Tu puntaje en esta ronda fue de: " + totalRonda + " puntos!");
        jugadorActual.getDadosApartados().clear();


    }

    public ArrayList<Integer> guardar_indices(int indice){
        indices.add(indice);
        return indices;
    }

    public ArrayList<Integer> ingresar_indices(int cantidad){
        indices.clear();
        //System.out.println("Ingrese los indice/s del/los dado/s que quiere apartar(separados por enter): ");
        for (int i = 0; i < cantidad; i++) {
            notifyMessage("INGRESAR INDICE");
        }
        return indices;
    }

    public boolean validar_indices(){
        ArrayList<Dado> dadosIndices = new ArrayList<>();
        for (int i = 0; i < indices.size(); i++) {
            int indice_valor = indices.get(i);
            dadosIndices.add(dadosParciales.get(indice_valor));
        }
        boolean tienePuntos = regla.tieneDadosConPuntos(dadosIndices);
        boolean puedeApartar = regla.verificar_si_puede_apartar(dadosIndices);
        if (tienePuntos && puedeApartar) {
            for (int i = 0; i < indices.size(); i++) {
                jugadorActual.getDadosApartados().add(dadosParciales.get(indices.get(i)));
            }
            return true;
        }
        notifyMessage("INDICES INVALIDOS");
        //System.out.println("Los indices y/o cantidad seleccionados no son validos. Vuelva a intentarlo");
        return false;
    }


    public void apartarDados(){
        boolean flag = false;
        while(flag == false){
            notifyMessage("INGRESAR CANTIDAD INDICES");
            flag = validar_indices();

        }
    }

    public ArrayList<Dado> getDadosParciales() {
        return dadosParciales;
    }


    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void establecer_decision(int numero){
        opcion_seleccionada = numero;
    }

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




