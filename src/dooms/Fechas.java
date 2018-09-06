package dooms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fechas {
    int anno;
    int mes;
    int dia;

    Fechas(int anno, int mes, int dia) {
        this.anno = anno;
        this.mes = mes;
        this.dia = dia;
    }

    static int parseAnno(String argumentos){
        return Integer.parseInt(argumentos);
    }

    static Fechas parseFecha(String argumentos){
        String patron = "(\\d{4})\\s(\\d{2})\\s(\\d{2})";
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(argumentos);
        m.find();
        try {
            return new Fechas(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)));
        }
        catch(Exception ignored){
            return null;
        }
    }

    @Override
    public String toString() {
        return "(Año: " + this.anno + ", Mes: " + this.mes + ", Día: " + this.dia + ")\n";
    }
}
