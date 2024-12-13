package com.example.rest;

import java.io.IOException;
import java.net.URI;
import java.util.Comparator;
import java.util.Random;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.controls.tda.list.LinkedList;

/**
 * Main class.
 *
 */
public class Main {
    public static final String BASE_URI = "http://localhost:8082/myapp/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.rest");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
    // Metodo para generar un arreglo de numeros aleatorios
    private static Integer[] generateRandomArray(int size) {
        int[] primitiveArray = new Random().ints(size, 0, 100000).toArray();
        Integer[] integerArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            integerArray[i] = primitiveArray[i];
        }
        return integerArray;
    }

    // Metodo para copiar un arreglo
    private static Integer[] copyArray(Integer[] original) {
        return java.util.Arrays.copyOf(original, original.length);
    }

    // Testeo de algoritmos de ordenamiento
    private static void testSorting(LinkedList<Integer> list, String nombreAlgoritmo,
            Comparator<Integer> comparator) {
        long startTime = System.nanoTime();
        switch (nombreAlgoritmo) {
            case "Metodo QuickSort":
                list.quickSort(comparator);
                break;
            case "Metodo MergeSort":
                list.mergeSort(comparator);
                break;
            case "Metodo ShellSort":
                list.shellSort(comparator);
                break;
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("%-13s with %6d elements took %10.2f ms.%n",
                nombreAlgoritmo, list.getSize(), timeInMs);
    }

    // Testeo de algoritmos de busqueda
    private static void testSearching(LinkedList<Integer> list, String nombreAlgoritmo,
        Integer target, Comparator<Integer> comparator) {
        long startTime = System.nanoTime();
        Integer result;

        if (nombreAlgoritmo.equals("Busqueda Lineal")) {
            result = list.linearSearch(target, comparator);
        } else {
            result = list.binarySearch(target, comparator);
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("%-13s with %6d elements took %10.2f ms. Found at index: %d%n",
                nombreAlgoritmo, list.getSize(), timeInMs, result);
    }

    public static void main(String[] args) throws IOException {

        // Inicia el servidor
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();

        int[] sizes = { 10000, 20000, 25000 }; //Tamaños de los arreglos
        // Comparador de enteros
        Comparator<Integer> comparator = Integer::compareTo;
        System.out.println("\n=== Testeo de algoritmos ===\n");

        for (int size : sizes) {
            System.out.println("Testeando con " + size + " elements:");
            System.out.println("----------------------------------------");

            Integer[] originalArray = generateRandomArray(size);

            // Test Sorting Algorithms
            System.out.println("\nOrdenando los algoritmos:");
            System.out.println("----------------");

            // Test del quicksort
            LinkedList<Integer> quickSortList = new LinkedList<>();
            quickSortList.toList(copyArray(originalArray));
            testSorting(quickSortList, "Metodo QuickSort", comparator);

            // Test del MergeSort
            LinkedList<Integer> mergeSortList = new LinkedList<>();
            mergeSortList.toList(copyArray(originalArray));
            testSorting(mergeSortList, "Metodo MergeSort", comparator);

            // Test del ShellSort
            LinkedList<Integer> shellSortList = new LinkedList<>();
            shellSortList.toList(copyArray(originalArray));
            testSorting(shellSortList, "Metodo ShellSort", comparator);

            // Usa el valor del medio para la busqueda
            Integer targetValue = originalArray[size / 2]; 

            // Test de la busqueda lineal
            LinkedList<Integer> linearSearchList = new LinkedList<>();
            linearSearchList.toList(copyArray(originalArray));
            testSearching(linearSearchList, "Busqueda Lineal", targetValue, comparator);

            // Test de la busqueda binaria
            LinkedList<Integer> binarySearchList = new LinkedList<>();
            binarySearchList.toList(copyArray(originalArray));
            binarySearchList.quickSort(comparator); 
            testSearching(binarySearchList, "Busqueda Binaria", targetValue, comparator);
            System.out.println("\n");
        }
        
    
    }
}

