package Modelo;

import java.util.ArrayList;

public class Reglas {
    public int[] cantidadDados(ArrayList<Dado> dados){ //cuenta la cantidad de dados que hay de cada numero
        int[] conteo = new int[7];
        for(int i=0; i<dados.size(); i++){
            int valorDado = dados.get(i).getValorCaraSuperior();
            conteo[valorDado]++;
        }
        return conteo;
    }

    public boolean tieneTrio(int[] conteo){ //chequeo si tiene trio de alguno de los numeros
        for(int i=1; i <= 6; i++ ){
            if(conteo[i]>=3){
                return true;
            }
        }
        return false;
    }

    public boolean tieneEscalera(int[] conteo){ //chequeo si tiene escalera
        boolean escalera1a5 = true; //chequeo escalera: 1,2,3,4,5
        for(int i=1; i <= 5; i++){
            if(conteo[i] != 1){
                escalera1a5=false;
            }
        }
        if(escalera1a5){
            return true;
        }

        boolean escalera2a6 = true; //chequeo escalera: 2,3,4,5,6
        for(int i=2; i <= 6; i++){
            if(conteo[i] != 1){
                escalera2a6=false;
            }
        }
        if(escalera2a6){
            return true;
        }

        if(conteo[1]==1 && conteo[3]==1 && conteo[4]==1 && conteo[5]==1 && conteo[6]==1){ //chequeo 3,4,5,6,1
            return true;
        }

        return false;

    }

    public boolean tieneDadosConPuntos(ArrayList<Dado> dadosParciales){ //con esto puedo chequear que si el usuario me pide seguir, pueda.
        int[] conteo = cantidadDados(dadosParciales);
        if(tieneEscalera(conteo) || tieneTrio(conteo) || conteo[1]>0 || conteo[5]>0){
            return true;
        }
        return false;
    }


    public boolean verificar_si_puede_apartar(ArrayList<Dado> dadosApartados){
        int[] cantidad= cantidadDados(dadosApartados);

        for(int i=0; i<dadosApartados.size(); i++){
            int n = dadosApartados.get(i).getValorCaraSuperior();
            if((n!=5 && n!=1 && cantidad[n] != 3)){
                return false;
            }
        }
        return true;
    }


}
