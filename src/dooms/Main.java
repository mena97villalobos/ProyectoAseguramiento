package dooms;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Utilitarias u = new Utilitarias();
        menu(u);

    }

    /* TODO arreglar el parseo de los comandos
    private static void testFile(Utilitarias u, String nombre) {
        try {
            File archivo = new File(nombre);
            Scanner scan = new Scanner(archivo);
            String commandArgumentos;
            String command;
            String old;

            while(scan.hasNextLine()) {
                commandArgumentos = scan.nextLine();

                if (commandArgumentos.startsWith("#") || commandArgumentos.isEmpty())
                    continue;

                command = limpiarEntrada(commandArgumentos);
                old = commandArgumentos + " -> ";
                commandArgumentos = commandArgumentos.replace(command, ""); //Obtener la lista de argumentos de un comando

                switch (command){
                    case "fecha_es_tupla ":
                        System.out.println(old + u.fecha_es_tupla(commandArgumentos));
                        break;
                    case "bisiesto ":
                        System.out.println(old + u.bisiesto(commandArgumentos));
                        break;
                    case "fecha_es_valida ":
                        System.out.println(old + u.fecha_es_valida(commandArgumentos));
                        break;
                    case "dia_siguiente ":
                        System.out.println(old + u.dia_siguiente(commandArgumentos).toString());
                        break;
                    case "dias_desde_primero_enero ":
                        System.out.println(old + u.dias_desde_primero_enero(commandArgumentos));
                        break;
                    case "dia_primero_enero ":
                        System.out.println(old + u.dia_primero_enero(commandArgumentos));
                        break;
                    //TODO pruebas para nuevos requerimientos
                    default:
                        System.out.println("Error encontrado");
                        return;
                }

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo de prueba: \"" + nombre + "\" no pudo ser abierto.");
        }
    } */

    private static void menu(Utilitarias u){

        String commandArgumentos;
        String command;
        Scanner scan = new Scanner(System.in);
        boolean salir = false;

        while(!salir){
            System.out.println("Inserte un comando, para ayuda digite \"-h\", digite \"salir\" para salir del programa");
            commandArgumentos = scan.nextLine();
            command = limpiarEntrada(commandArgumentos);
            commandArgumentos = commandArgumentos.replace(command, ""); //Obtener la lista de argumentos de un comando
            Fechas f;
            int anno;
            switch (command){
                case "-h":
                    System.out.println("Asignación 2, Aseguramiento de la Calidad del Software" + "\n" +
                            "Autores: Franco Quiros Carnet: 2013029890, Bryan Mena Carnet: 2016112933, Pablo Brenes Carnet: 2016250460" + "\n" +
                            "Usos del programa: \n" +
                            "fecha_es_tupla aaaa mm dd              --> Verificar si una fecha se puede representar en el programa\n" +
                            "bisiesto aaaa                          --> Retorna verdadero o falso dependiendo si el año dado es bisiesto o no\n" +
                            "fecha_es_valida aaaa mm dd             --> Verifica que la fecha sea valida\n" +
                            "dia_siguiente aaaa mm dd               --> Retorna la fecha siguiente a la fecha dada\n" +
                            "dias_desde_primero_enero aaaa mm dd    --> Retorna la cantidad de dias transcurridos entre el" +
                            "primero de enero del año dado a la fecha dada\n" +
                            "dia_primero_enero aaaa                 --> Retorna el número del día en que cae el 1 de Enero del año dado\n" +
                            "imprimir_4x3 aaaa                      --> Imprime el calendario en una matriz 4x3 del año dado\n" +
                            "fecha futura aaaa mm dd n              --> Retorna la fecha que está a n días naturales en el futuro\n" +
                            "probar_archivo nombre                  --> Ejecuta cada comando por linea en el archivo de prueba (bajo el mismo formato de los comandos)\n + " +
                            "dia_semana aaaa mm dd                  --> Calcula que en que día de la semana se da una determinada fecha");
                    break;
                case "fecha_es_tupla ":
                    System.out.println(u.fecha_es_tupla(commandArgumentos));
                    break;
                case "bisiesto ":
                    anno = Fechas.parseAnno(commandArgumentos);
                    System.out.println(u.bisiesto(anno));
                    break;
                case "fecha_es_valida ":
                    f = Fechas.parseFecha(commandArgumentos);
                    System.out.println(u.fecha_es_valida(f));
                    break;
                case "dia_siguiente ":
                    f = Fechas.parseFecha(commandArgumentos);
                    System.out.println(u.dia_siguiente(f).toString());
                    break;
                case "dias_desde_primero_enero ":
                    f = Fechas.parseFecha(commandArgumentos);
                    System.out.println(u.dias_desde_primero_enero(f));
                    break;
                case "dia_primero_enero ":
                    anno = Integer.parseInt(commandArgumentos);
                    System.out.println(u.dia_primero_enero(anno));
                    break;
                case "imprimir_4x3 ":
                    anno = Integer.parseInt(commandArgumentos);
                    u.imprimir_4x3(anno);
                    break;
                //Modificado Asignación 3 Bryan Mena
                //Se añaden los nuevos requerimientos funcionales al menu de línea de comando
                case "fecha_futura ":
                    f = Fechas.parseFecha(commandArgumentos);
                    System.out.println(u.fecha_futura(f).toString());
                    break;
                case "fecha_futura_habil ":
                    f = Fechas.parseFecha(commandArgumentos);
                    System.out.println(u.fecha_futura_habil(f).toString());
                    break;
                    /* TODO
                case "probar_archivo ":
                    testFile(u, commandArgumentos);
                    break;
                    */
                case "dia_semana ":
                    f = Fechas.parseFecha(commandArgumentos);
                    int dia = u.dia_semana(f);
                    System.out.println(dia);
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
        String patron = "^([a-z_]*(4x3)*\\s)"; //Utilizar Regex para obtener la función que se desea utilizar
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
