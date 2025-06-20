package Modelo;

import java.util.ArrayList;

public class Jugador {
    private String nombreJugador;
    private int puntajeTotal;
    private int puntajeParcial; //ese me va a ser util si necesito limpiar los dados
    private ArrayList<Dado> dadosApartados;

    public Jugador(String nombreJugador, ArrayList<Dado> dadosApartados) {
        this.nombreJugador = nombreJugador;
        this.puntajeTotal = 0;
        this.puntajeParcial = 0;
        this.dadosApartados = dadosApartados;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public int getPuntajeParcial() {
        return puntajeParcial;
    }

    public ArrayList<Dado> getDadosApartados() {
        return dadosApartados;
    }

    public void setDadosApartados(Dado dado) {
        this.dadosApartados.add(dado);
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public void setPuntajeParcial(int puntajeParcial) {
        this.puntajeParcial = puntajeParcial;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
}
