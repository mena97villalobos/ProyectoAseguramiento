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


    public String toString(int fila) {
        String str = "";
        if(fila == -1)
            str= "            "+this.nombre+"        ";
        else{
            try{
                ArrayList<String> aux = this.calendario.get(fila);
                for (String s : aux) {
                    str+=s;
                }
                str+="|";
            }catch(IndexOutOfBoundsException ignored){
                str = "                             ";
            }
        }
        return str;

    }
}
