package dooms;

import java.util.ArrayList;

class Utilitarias {
    private final ArrayList<Integer> meses31 = new ArrayList<>(); //Almacenar una lista de los meses con 31 días

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
        return f != null && fecha_es_valida(f);
    }

    boolean bisiesto(int anno){
        if (anno != 0 && anno >= 1582)
            return ((anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0)));
        else
            return false;
    }

    boolean fecha_es_valida(Fechas f){
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

    Fechas dia_siguiente(Fechas f){
        if(f != null && fecha_es_valida(f)) {
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

    int dias_desde_primero_enero(Fechas f){
        if(f != null && fecha_es_valida(f)) {
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

    int dia_primero_enero(int anno){
        //Modificado Asignación 3 para redirigir retorno al metodo dia_Semana agregado en asignación 3
        return dia_semana(new Fechas(anno, 1, 1));
    }

    //Agregado asignación 3
    Fechas fecha_futura(Fechas f){
        int n = f.aux;
        if(f.anno == 1582 && f.mes == 10)
            n += 10; //Validar excepción 10 de octubre de 1582
        //Obtener el día tope del mes actual
        int topeMes = f.mes == 2 ? (bisiesto(f.anno) ? 29 : 28) : (meses31.indexOf(f.mes) != -1 ? 31 : 30);
        int diferenciaMes = topeMes - f.dia; //Diferencia de días para llegar al tope del mes
        while(n > 0){
            if(diferenciaMes > n){ //Si n es menor que la diferencia de mes solo se suma n y se retorna
                f.dia += n;
                break;
            }
            else{ //Sumar hasta el proximo mes
                f.dia += diferenciaMes + 1;
                n -= diferenciaMes + 1;
            }
            if (f.mes == 12){
                f.anno++;
                f.mes = 1;
                f.dia = 1;
                //Actualizar valores del tope del mes y de la diferencia de mes
                diferenciaMes = 30;
                n --;
            }
            else{
                f.mes++;
                f.dia = 1;
                //Actualizar valores del tope del mes y de la diferencia de mes
                topeMes = f.mes == 2 ? (bisiesto(f.anno) ? 29 : 28) : (meses31.indexOf(f.mes) != -1 ? 31 : 30);
                diferenciaMes = topeMes - f.dia;
                n --;
            }
        }
        return f;
    }

    private void getDiasNoHabiles(Fechas f){
        //Obtiene los dias no habiles que hay entre una cantidad de dias naturales
        //Basado en el día en el que se esta
        int diasNoHabiles = 0;
        int diaSemana = dia_semana(f);
        int n = f.aux;
        while(n > 0){
            if(diaSemana == 0){
                diasNoHabiles += 1;
                diaSemana = 1;
            }
            else if(diaSemana == 6){
                diasNoHabiles += 1;
                diaSemana = 0;
            }
            else {
                diasNoHabiles += 1;
                diaSemana += 1;
                n--;
            }
        }
        f.aux = diasNoHabiles;
    }

    Fechas fecha_futura_habil(Fechas f){
        if (f == null)
            System.exit(-1); //todo SÁFESE ALV PERRO
        getDiasNoHabiles(f);
        f = fecha_futura(f);
        if(dia_semana(f) == 0)
            f = dia_siguiente(f);
        else if(dia_semana(f) == 6){
            f.aux = 2;
            f = fecha_futura(f);
        }
        return f;
    }

    int dia_semana(Fechas f){
        int anno = f.anno;
        int mes = f.mes;
        int dia = f.dia;
        int a = (14- mes) / 12;
        anno = anno - a;
        mes = mes + (12 * a) - 2;
        return (dia + anno + anno/4 - anno/100 + anno/400 + (31* mes)/12)%7;
    }

    int dias_entre(Fechas f1, Fechas f2){
        Fechas fechaInicio;
        Fechas fechaFin;
        int contador = 0;

        if (f1.esMayorQue(f2)){
            fechaFin = f1;
            fechaInicio = f2;
        }else{
            fechaFin = f2;
            fechaInicio = f1;
        }
        while (!fechaInicio.equals(fechaFin)){
            contador += 1;
            fechaInicio = dia_siguiente(fechaInicio);
        }
        return contador;
    }

    int dias_habiles_entre(Fechas f1, Fechas f2){
        Fechas fechaInicio;
        Fechas fechaFin;
        int contador = 0;
        if (f1.esMayorQue(f2)){
            fechaFin = f1;
            fechaInicio = f2;
        }else{
            fechaFin = f2;
            fechaInicio = f1;
        }
        int diaActual = dia_semana(fechaInicio);
        while (!fechaInicio.equals(fechaFin)){
            if(diaActual == 0){
                fechaInicio = dia_siguiente(fechaInicio);
                diaActual = 1;
            }
            else if(diaActual == 6){
                fechaInicio = dia_siguiente(fechaInicio);
                diaActual = 0;
            }
            else {
                contador += 1;
                fechaInicio = dia_siguiente(fechaInicio);
                diaActual += 1;
            }
        }
        return contador;
    }

    private ArrayList<Mes> creaCalendario(int anno, int dia){
        ArrayList<String> auxiliar = new ArrayList<>();
        ArrayList<Mes> meses = new ArrayList<>();
        //Arreglo auxiliar que contiene la fila 1 de los calendarios de cada mes (los dias de la semana)
        auxiliar.add("   D ");
        auxiliar.add("  L ");
        auxiliar.add("  K ");
        auxiliar.add("  M ");
        auxiliar.add("  J ");
        auxiliar.add("  V ");
        auxiliar.add("  S");
        // Crea un calendario mensual y lo almacena en un array
        for (int k = 1; k <= 12; k++) { //Iterar sobre los meses de cada año
            //Obtener el numero de dias del mes
            int numDias = (k == 2 ? (bisiesto(anno) ? 29 : 28) : (meses31.indexOf(k) != -1 ? 31 : 30));
            String nombreMes = Meses.getById(k).toString();
            ArrayList<String> fila = new ArrayList<>();
            Mes mes = new Mes(nombreMes,numDias);
            mes.calendario.add(auxiliar); //Añadir fila con los dias de la semana
            for (int i = 1; i <= dia; i++)
                fila.add("    "); //Rellenar días hasta el primero del mes
            int j = 1;
            while(j <= mes.cantDias){
                if(j == 5 && anno == 1582 && k == 10){
                    j = 15; //Validación de la excepción de Octubre de 1582
                }
                if (dia % 7 == 0 && dia != 0){
                    mes.calendario.add((ArrayList<String>) fila.clone());
                    fila.clear();
                }
                fila.add (( j < 10) ? "   "+j : "  "+j);
                dia += 1;
                j++;
            }
            mes.calendario.add((ArrayList<String>) fila.clone());
            dia %= 7;
            meses.add(mes);
        }
        return meses;
    }

    void imprimir_4x3(int anno){
        int dia = dia_primero_enero(anno);
        ArrayList<Mes> meses = creaCalendario(anno,dia);
        //Recorre array de calendarios y los imprime en una matriz 4 x 3
        System.out.println("\n\t\t\t\t\t\t\t\t\t\tCalendario " + Integer.toString(anno) + "\n");
        for(int j = 0; j < 12; j+=3){
            String m1;
            String m2;
            String m3;
            for (int i = -1; i<8; i++){
                m1 = meses.get(j).toString(i);
                m2 = meses.get(j+1).toString(i);
                m3 = meses.get(j+2).toString(i);
                System.out.println(m1+"    "+m2+"    "+m3);

            }
        }
    }
}
