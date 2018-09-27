package dooms;

import java.util.ArrayList;

class Mes {

    final ArrayList<ArrayList<String>> calendario = new ArrayList<>();
    private final String nombre;
    final int cantDias;

    Mes(String nombre, int cantDias) {
        this.nombre = nombre;
        this.cantDias = cantDias;
    }


    String toString(int fila) {
        String str = "";
        if(fila == -1)
            str= "            "+this.nombre+"        ";
        else{
            try{
                ArrayList<String> aux = this.calendario.get(fila);
                for (String s : aux) {
                    str += s;
                }
                for (int i = aux.size(); i <7 ; i++) {
                    str += "    ";
                }
                str+="|";
            }catch(IndexOutOfBoundsException ignored){
                str = "                             ";
            }
        }
        return str;

    }
}
