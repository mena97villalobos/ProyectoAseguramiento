package dooms;

import java.util.ArrayList;

public class Utilitarias {
    private ArrayList<Integer> meses31 = new ArrayList<>();

    Utilitarias(){
        meses31.add(1);
        meses31.add(3);
        meses31.add(5);
        meses31.add(7);
        meses31.add(8);
        meses31.add(10);
        meses31.add(12);
    }

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
            if(f.mes == 2) {
                return f.anno >= 1582 && f.dia >= 1 && (bisiesto(f.anno) ? f.dia <= 29 : f.dia <= 28);
            }
            else {
                int numDias = meses31.indexOf(f.mes) != -1 ? 31 : 30;
                if (f.anno == 1582 && f.mes == 10) {
                    return (f.dia >= 1 && f.dia <= 4) || (f.dia >= 15 && f.dia <= 31);
                }
                else
                    return f.anno >= 1582 && f.mes >= 1 && f.mes <= 12 && f.dia >= 1 && f.dia <= numDias;
            }
        }
        else
            return false;
    }

    Fechas dia_siguiente(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        if(f != null && fecha_es_valida(argumentos)) {
            //Obtener la cantidad de dias de un mes
            int numDias = (f.mes == 2 ? (bisiesto(f.anno) ? 29 : 28) : (meses31.indexOf(f.mes) != -1 ? 31 : 30));
            int dia = f.dia + 1;
            //Validar la excepcion del 4 de Octubre de 1582 al 15 de Octubre de 1582
            if(f.anno == 1582 && f.mes == 10 && f.dia == 4)
                return new Fechas(1582, 10, 15);
            else if (dia <= numDias)
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

    int dias_desde_primero_enero(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        if(f != null && fecha_es_valida(argumentos)) {
            int cantDias = 0;
            for (int i = 1; i < f.mes; i++) {
                if (f.anno == 1582 && i == 10) {
                    cantDias += 21;
                } else {
                    if (i == 2) {
                        cantDias += bisiesto(f.anno) ? 29 : 28;
                    } else {
                        if (meses31.indexOf(i) != -1)
                            cantDias += 31;
                        else
                            cantDias += 30;
                    }
                }
            }
            cantDias += f.dia;
            return cantDias;
        }
        return -1;
    }

    protected int dia_primero_enero(String argumentos){
        return 0;
    }

    protected void imprimir_4x3(String argumentos){

    }
}
