package dooms;

import java.util.ArrayList;

public class Utilitarias {
    private ArrayList<Integer> meses31 = new ArrayList<>(); //Almacenar una lista de los meses con 31 días

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
        //Revisa que se haya podido parsear el año además valida que este en el periodo valido
        if (anno != 0 && anno >= 1582)
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
                //Cantidad de dias que tiene el mes dado en la fecha
                int numDias = meses31.indexOf(f.mes) != -1 ? 31 : 30;
                if (f.anno == 1582 && f.mes == 10) {
                    //Validación de la excepción dada en Octubre de 1582
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
            else { //En esta sección se debe incrementar el mes
                int mes = f.mes + 1;
                if (mes <= 12)
                    return new Fechas(f.anno, mes, 1);
                else //Incrementar el año en caso que el memes sobre pase el 12
                    return new Fechas(f.anno + 1, 1, 1);
            }
        }
        return new Fechas(0, 0, 0); //Fecha no es valida o no es una tupla
    }

    int dias_desde_primero_enero(String argumentos){
        Fechas f = Fechas.parseFecha(argumentos);
        if(f != null && fecha_es_valida(argumentos)) {
            int cantDias = 0; //Contador de la cantidad de dias desde el primero de Enero
            for (int i = 1; i < f.mes; i++) { //Iterar sobres los meses que han pasado desde enero a la fecha - 1
                if (f.anno == 1582 && i == 10) {
                    cantDias += 21; //Validación de la excepción del Ocutubre de 1582
                } else {
                    if (i == 2) {
                        cantDias += bisiesto(f.anno) ? 29 : 28; //Sumar dias de Febrero sea bisiesto o no
                    } else {
                        //Sumar los dias de un mes normal de 31 o 30 dias
                        cantDias += meses31.indexOf(i) != -1 ? 31 : 30;
                    }
                }
            }
            //Sumar los dias que han pasado de mes dado se resta 1 para ignorar el primero de enero del año
            cantDias += f.dia - 1;
            return cantDias;
        }
        return -1; //Fecha no es valida o no es una tupla
    }

    int dia_primero_enero(String argumentos){
        int a = Fechas.parseAnno(argumentos);
        int ultimos2Digitos = a%100;
        int siglo = a/100+1;
        int anchorDay = ((5*siglo + ((siglo-1) / 4))+4)%7;
        int diaReferencia = (((ultimos2Digitos/12) + ultimos2Digitos%12 + (ultimos2Digitos%12 / 4)) + anchorDay)%7;
        int resultado;
        int offset = bisiesto(a) ? -3 : -2;
        resultado = (diaReferencia + offset)%7;
        if(resultado < 0){
            resultado = 7 + resultado;
        }
        return resultado;
    }

    protected void imprimir_4x3(String argumentos){
        int anno = Integer.parseInt(argumentos);
        int dia = dia_primero_enero(argumentos);
        ArrayList<String> auxiliar = new ArrayList<>();
        ArrayList<Mes> meses = new ArrayList<>();
        auxiliar.add("D");
        auxiliar.add("L");
        auxiliar.add("K");
        auxiliar.add("M");
        auxiliar.add("J");
        auxiliar.add("V");
        auxiliar.add("S");
        for (int k = 1; k <= 12; k++) {
            int numDias = (k == 2 ? (bisiesto(anno) ? 29 : 28) : (meses31.indexOf(k) != -1 ? 31 : 30));
            String nombreMes = Meses.getById(k).toString();
            ArrayList<String> fila = new ArrayList<>();
            Mes mes = new Mes(nombreMes,numDias);
            mes.calendario.add(auxiliar);
            for (int i = 1; i <= dia; i++)
                fila.add("     ");
            for (int j = 1; j <= numDias; j++) {
                if (dia % 7 == 0 && dia != 0){
                    mes.calendario.add(fila);
                    fila.clear();
                }
                fila.add (( j < 10) ? "    "+j : "   "+j);
                dia += 1;
            }
            dia %= 7;
            meses.add(mes);
        }
        for(int j = 0; j < 12; j++){
            for (int i1 = 0; i1 < meses.size(); i1++) {
                for (int i = -1; i<7; i++){

                }
            }
        }

    }


}
