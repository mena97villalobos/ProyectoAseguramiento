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
        StringBuilder str = new StringBuilder();
        if(fila == -1)
            str = new StringBuilder("            " + this.nombre + "        ");
        else{
            try{
                ArrayList<String> aux = this.calendario.get(fila);
                for (String s : aux) {
                    str.append(s);
                }
                for (int i = aux.size(); i <7 ; i++) {
                    str.append("    ");
                }
                str.append("|");
            }catch(IndexOutOfBoundsException ignored){
                str = new StringBuilder("                             ");
            }
        }
        return str.toString();

    }
}
