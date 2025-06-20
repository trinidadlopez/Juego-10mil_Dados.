package Modelo;

import Controlador.Observador;


public interface Observable {
    public void attach (Observador anObserver);
    public void detach (Observador anObserver);
    public void notifyMessage (Object object);
}
