package Vista;

import java.util.Scanner;

public class VistaLanzamientos extends Vista{
    public int pedir_opcion_deseada(){
        System.out.println("Seleccione la opcion que desee: 1)Plantarse. 2)Apartar dados y seguir tirando.");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        return opcion;
    }
    public int mensaje_de_error_opcion_deseada(){
        System.out.println("El numero que ingreso no es valido, ingrese 1 o 2");
        Scanner scanner = new Scanner(System.in);
        int new_opcion = scanner.nextInt();
        return new_opcion;
    }
    public int pedir_cantidad(){
        System.out.println("Ingrese la cantidad de dados que quiere apartar: ");
        Scanner scanner = new Scanner(System.in);
        int cant = scanner.nextInt();
        return cant;
    }
    public int mensaje_de_error_cantidad(){
        System.out.println("El numero de dados a apartar no es valido, ingrese un numero valido para apartar");
        Scanner scanner = new Scanner(System.in);
        int new_cant = scanner.nextInt();
        return new_cant;
    }
    public int pedir_indices(){
        System.out.println("Ingrese el indice del dado que desee apartar: ");
        Scanner scanner = new Scanner(System.in);
        int indice = scanner.nextInt();
        return indice;
    }

}
