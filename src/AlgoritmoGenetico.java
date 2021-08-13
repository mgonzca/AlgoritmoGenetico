import java.util.Date;
import java.util.Random;

public class AlgoritmoGenetico {

    public static final String genes = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!.,";
    public static final String objetivo = "Politecnico Colombiano Jaime Isaza Cadavid";
    private static final Random random = new Random();

    public static void main(String[] args) {
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico();
        random.setSeed(12345);
        Date tiempoInicio = new Date();
        String mejorPadre = algoritmoGenetico.generarPadre(objetivo.length());
        int mejorFitness = algoritmoGenetico.fitness(mejorPadre);
        algoritmoGenetico.imprimir(mejorPadre, tiempoInicio);
        while (true) {
            String hijo = algoritmoGenetico.mutar(mejorPadre);
            int hijoFitness = algoritmoGenetico.fitness(hijo);
            if (mejorFitness >= hijoFitness) {
                imprimir(hijo, tiempoInicio);
            }
            if (hijoFitness >= mejorPadre.length()){
                imprimir(hijo, tiempoInicio);
                break;
            }
            mejorFitness = hijoFitness;
            mejorPadre = hijo;
        }
    }

    public static String generarPadre(int longitud) {
        StringBuilder constructor = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            constructor.append(genes.charAt(random.nextInt(genes.length())));
        }
        return constructor.toString();
    }

    public static int fitness(String solucionProbable){
        char[] arregloObtenido = solucionProbable.toCharArray();
        char[] arregloObjetivo = objetivo.toCharArray();
        int contador = 0;
        for(int i = 0; i < arregloObjetivo.length; i++) {
            if (arregloObtenido[i] == arregloObjetivo[i]) {
                contador++;
            }
        }
        return contador;
    }

    public static String mutar(String padre){
        char[] arregloObtenido = padre.toCharArray();
        char[] arregloObjetivo = objetivo.toCharArray();
        for(int i = 0; i < arregloObjetivo.length; i++) {
            if (arregloObtenido[i] != arregloObjetivo[i]) {
                char gen = genes.charAt(random.nextInt(genes.length()));
                while (gen != arregloObtenido[i]){
                    arregloObtenido[i] = gen;
                }
            }
        }
        return String.valueOf(arregloObtenido);
    }

    public static void imprimir(String obtenido, Date tiempoInicio) {
        Date time = new Date();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(String.format("Cadena obtenida: " + obtenido));
        System.out.println(String.format("Fitness: " + fitness(obtenido)));
        System.out.println(String.format("Tiempo: " + (time.getTime() - tiempoInicio.getTime()) + " milisegundos" ));
        System.out.println("---------------------------------------------------------------------------------------");
    }
}
