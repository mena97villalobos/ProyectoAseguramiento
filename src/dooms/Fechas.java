package dooms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fechas {
    int anno;
    int mes;
    int dia;
    int aux;

    Fechas(int anno, int mes, int dia) {
        this.anno = anno;
        this.mes = mes;
        this.dia = dia;
    }

    static int parseAnno(String argumentos){
        int anno = Integer.parseInt(argumentos);
        return anno >= 1582 ? anno : -1; //Validar que el año esté en el rango valido
    }

    static Fechas parseFecha(String argumentos){
        //Uso de Regex para parsear una fecha en el formato aaaa mm dd no se aceptan numeros negativos
        String patron = "(\\d{4})\\s(\\d{2})\\s(\\d{2})\\s?(\\d*)?";
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(argumentos);
        m.find();
        try {
            //Modificado asignación 3 Bryan Mena
            // Se parsea un numero auxiliar utilizado en los nuevos requerimientos funcionales
            Fechas f = new Fechas(
                    Integer.parseInt(m.group(1)), //año
                    Integer.parseInt(m.group(2)), //mes
                    Integer.parseInt(m.group(3))); // dia
            String group4 = m.group(4);
            f.aux = !group4.equals("") ? Integer.parseInt(group4) : -1;
            return f;
        }
        catch(Exception ignored){
            return null;
        }
    }

    @Override
    public String toString() {
        return "(Año: " + this.anno + ", Mes: " + this.mes + ", Día: " + this.dia + ")\n";
    }


    //Agregado para asignacion 3
    public boolean equals(Fechas fechas){
        boolean e = false;
        if (this.anno == fechas.anno){
            if (this.mes == fechas.mes){
                if (this.dia == fechas.dia){
                    e = true;
                }
            }
        }
        return e;
    }

    public boolean esMayorQue (Fechas fechas){
        boolean e = false;
        if (this.anno > fechas.anno) {
            e = true;
        }else{
            if (this.mes > fechas.mes) {
                e = true;
            }else{
                if (this.dia > fechas.dia) {
                    e = true;
                }
            }
        }
        return e;
    }
}
