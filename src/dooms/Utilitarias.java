package dooms;

import java.util.ArrayList;
import java.util.Arrays;

public class Utilitarias {
    private static int[] auxMeses31 = {1, 3, 5, 7, 8, 10, 12};
    private static ArrayList meses31 = new ArrayList(Arrays.asList(auxMeses31));

    boolean fecha_es_tupla(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        return f != null;
    }

    boolean bisiesto(String argumentos){
        int anno = Fechas.parseAnno(argumentos);
        if (anno != 0)
            return ((anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0)));
        else
            return false;
    }

    private boolean bisiesto(int anno){
        return ((anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0)));
    }

    boolean fecha_es_valida(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        if(f != null){
            if(f.mes == 2)
                return f.anno >= 1582 && f.dia >= 1 && (bisiesto(f.anno) ? f.dia <= 29 : f.dia <= 28);
            else
                return f.anno >= 1582 && f.mes >= 1 && f.mes <= 12 && f.dia >= 1 && f.dia <= 31;
        }
        else
            return false;
    }

    Fechas dia_siguiente(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        if(f != null && fecha_es_valida(argumentos)) {
            int numDias = (f.mes == 2 ? (bisiesto(f.anno) ? 29 : 28) : (meses31.indexOf(f.mes) != -1 ? 31 : 30));
            int dia = f.dia + 1;
            if (dia <= numDias)
                return new Fechas(f.anno, f.mes, dia);
            else {
                int mes = f.mes + 1;
                if (mes <= 12)
                    return new Fechas(f.anno, mes, 1);
                else
                    return new Fechas(f.anno + 1, 1, 1);
            }
        }
        return new Fechas(0, 0, 0);
    }

    protected int dias_desde_primero_enero(String argumentos){
        return 0;
    }

    protected int dia_primero_enero(String argumentos){
        return 0;
    }

    protected void imprimir_4x3(String argumentos){

    }
}
