package Modelo;

import java.util.ArrayList;

public class ReglaPuntos {
    private Reglas reglas = new Reglas(); //no se si hace falta

    public int calcularPuntaje(ArrayList<Dado> dadosApartados){
        int[] conteo = reglas.cantidadDados(dadosApartados);
        int puntos=0;

        if(conteo[1]==5){
            return 10000;
        }

        if(reglas.tieneEscalera(conteo)){
            return 500;
        }

        for(int i=1; i<=6; i++){
            if(conteo[i]>=3){
                if(i==1){
                    puntos += 1000;
                }else{
                    puntos += i*100;
                }
                conteo[i]-=3;
            }
            if(i==5){
                puntos += conteo[i]*50;
            }else if(i==1){
                puntos += conteo[i]*100;
            }
        }
        return puntos;
    }

    public void sumarPuntaje(int puntajeParcial, Jugador jugadorActual){
        int puntajeActual=jugadorActual.getPuntajeTotal();
        jugadorActual.setPuntajeTotal(puntajeActual+puntajeParcial);
    }

    public Jugador determinar_quien_gano(ArrayList<Jugador> jugadores){
        Jugador ganador=jugadores.get(0);
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getPuntajeTotal()>ganador.getPuntajeTotal()){
                ganador=jugadores.get(i);
            }
        }
        return ganador;
    }

}
