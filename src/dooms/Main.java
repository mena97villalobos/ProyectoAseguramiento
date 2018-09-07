package dooms;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String commandArgumentos;
        String command;
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        Utilitarias u = new Utilitarias();

        while(!salir){
            System.out.println("Inserte un comando, para ayuda digite \"-h\", digite \"salir\" para salir del programa");
            commandArgumentos = scan.nextLine().toLowerCase();
            command = limpiarEntrada(commandArgumentos);
            commandArgumentos = commandArgumentos.replace(command, ""); //Obtener la lista de argumentos de un comando
            switch (command){
                case "-h":
                    System.out.println("Asignación 2, Aseguramiento de la Calidad del Software" + "\n" +
                        "Autores: Franco Quiros Carnet: 2013029890, Bryan Mena Carnet: 2016112933" + "\n" +
                        "Usos del programa: \n" +
                        "fecha_es_tupla aaaa mm dd              --> Verificar si una fecha se puede representar en el programa\n" +
                        "bisiesto aaaa                          --> Retorna verdadero o falso dependiendo si el año dado es bisiesto o no\n" +
                        "fecha_es_valida aaaa mm dd             --> Verifica que la fecha sea valida\n" +
                        "dia_siguiente aaaa mm dd               --> Retorna la fecha siguiente a la fecha dada\n" +
                        "dias_desde_primero_enero aaaa mm dd    --> Retorna la cantidad de dias transcurridos entre el" +
                        "primero de enero del año dado a la fecha dada\n" +
                        "dia_primero_enero aaaa                 --> Retorna el número del día en que cae el 1 de Enero del año dado\n" +
                        "imprimir_4x3 aaaa                      --> Imprime el calendario en una matriz 4x3 del año dado\n");
                    break;
                case "fecha_es_tupla ":
                    System.out.println(u.fecha_es_tupla(commandArgumentos));
                    break;
                case "bisiesto ":
                    System.out.println(u.bisiesto(commandArgumentos));
                    break;
                case "fecha_es_valida ":
                    System.out.println(u.fecha_es_valida(commandArgumentos));
                    break;
                case "dia_siguiente ":
                    System.out.println(u.dia_siguiente(commandArgumentos).toString());
                    break;
                case "dias_desde_primero_enero ":
                    System.out.println(u.dias_desde_primero_enero(commandArgumentos));
                    break;
                case "dia_primero_enero ":
                    System.out.println(u.dia_primero_enero(commandArgumentos));
                case "imprimir 4x3 ":
                    break;
                case "salir":
                    salir = true;
                    break;
                default:
                    System.out.println("Comando Invalido, intente de nuevo");
            }
        }
    }

    private static String limpiarEntrada(String in){
        String patron = "^([a-z_]*\\s)"; //Utilizar Regex para obtener la función que se desea utilizar
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(in);
        try {
            m.find();
            return m.group(); //El comando es correcto
        }
        catch (IllegalStateException ignored){
            return in; //La opción puede ser -h || salir || comando invalido
        }
    }
}
