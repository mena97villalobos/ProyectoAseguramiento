package dooms;

import java.util.ArrayList;

public class Mes {

    ArrayList<ArrayList<String>> calendario = new ArrayList();
    String nombre;
    int cantDias;

    public Mes(String nombre, int cantDias) {
        this.nombre = nombre;
        this.cantDias = cantDias;
    }


    public void toString(int fila) {
        if(fila == -1)
            System.out.print(nombre);
        else{
            try{
                ArrayList<String> aux = this.calendario.get(fila);
                for (String s : aux) {
                    System.out.print(s);
                }
            }catch(IndexOutOfBoundsException ignored){
                System.out.print('\n');
            }
        }

    }
}
