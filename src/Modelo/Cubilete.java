package Modelo;

import java.util.ArrayList;

public class Cubilete {
    private ArrayList<Dado> dados;

    public Cubilete() {
        dados = new ArrayList<>();
        dados.add(new Dado());
        dados.add(new Dado());
        dados.add(new Dado());
        dados.add(new Dado());
        dados.add(new Dado());
    }

    public ArrayList<Dado> getDados() {
        return dados;
    }
}
