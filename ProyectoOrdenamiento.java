import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProyectoOrdenamiento {
    private static List<Integer> listaDatos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); 

    public static void mostrarInformacion() {
        String universidad = "Universidad Da Vinci de Guatemala";
        String curso = "Estructura de Datos";
        String docente = "Ing. Brandon Chitay";

        System.out.print("Ingrese su nombre: ");
        String estudiante = scanner.nextLine().trim();

        System.out.println("\n========================================");
        System.out.println(universidad);
        System.out.println(curso);
        System.out.println(docente + "\n");
        System.out.println("Nombre del estudiante: " + estudiante + "\n");

        System.out.println("Información del Desarrollador:");
        System.out.println("Nombre: Carlos Eduardo Toj Dardón");
        System.out.println("Carrera: Estudiante Ingeniería en Sistemas");
        System.out.println("Carné: 202403768");
        System.out.println("Correo: toj2492@gmail.com");
        System.out.println("\n========================================");

        List<String> puntosHechos = new ArrayList<>();

        puntosHechos.add("1. Información del Desarrollador");
        puntosHechos.add("2. Menú Principal");
        puntosHechos.add("3. Carga de Datos desde un CSV");
        puntosHechos.add("4. Algoritmo de Ordenamiento - Bubble Sort");
        puntosHechos.add("5. Algoritmo de Ordenamiento - Enhanced Bubble Sort");
        puntosHechos.add("6. Algoritmo de Ordenamiento - Quick Sort");
        puntosHechos.add("7. Algoritmo de Ordenamiento - Selection Sort");
        puntosHechos.add("8. Algoritmo de Ordenamiento - Merge Sort");
        puntosHechos.add("9. Algoritmo de Búsqueda - Binary Search");
        puntosHechos.add("10. Presentación Final en YouTube");

        System.out.println("PUNTOS HECHOS:");
        for (String punto : puntosHechos) {
            System.out.println("✅ " + punto);
        }

        System.out.println("========================================");
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Información del Desarrollador");
            System.out.println("2. Cargar datos desde un archivo CSV");
            System.out.println("3. Ordenar datos usando Bubble Sort");
            System.out.println("4. Ordenar datos usando Enhanced Bubble Sort");
            System.out.println("5. Ordenar datos usando Quick Sort");
            System.out.println("6. Ordenar datos usando Selection Sort");
            System.out.println("7. Ordenar datos usando Merge Sort");
            System.out.println("8. Buscar un número con Binary Search");
            System.out.println("9. Presentación Final en YouTube");
            System.out.println("10. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    mostrarInformacionDesarrollador();
                    break;
                case 2:
                    opcionCargarDatos();
                    break;
                case 3:
                    BubbleSort.opcionBubbleSort(listaDatos);
                    break;
                case 4:
                    EnhancedBubbleSort.opcionEnhancedBubbleSort(listaDatos);
                    break;
                case 5:
                    QuickSort.opcionQuickSort(listaDatos);
                    break;
                case 6:
                    SelectionSort.opcionSelectionSort(listaDatos);
                    break;
                case 7:
                    MergeSort.opcionMergeSort(listaDatos);
                    break;
                case 8:
                    BinarySearch.opcionBinarySearch(listaDatos);
                    break;
                case 9:
                    mostrarPresentacionYouTube();
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (true);
    }

    private static void opcionCargarDatos() {
        System.out.println("\n========================================");
        System.out.println("Información del Desarrollador:");
        System.out.println("Nombre: Carlos Eduardo Toj Dardón");
        System.out.println("Carrera: Estudiante Ingeniería en Sistemas");
        System.out.println("Carné: 202403768");
        System.out.println("Correo: toj2492@gmail.com");
        System.out.println("========================================");

        System.out.print("Ingrese la ruta del archivo CSV: ");
        String archivo = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            archivo = scanner.nextLine().trim();
            if (archivo.isEmpty()) {
                System.out.println("No ingresó una ruta. Intente de nuevo.");
                System.out.print("Ingrese la ruta del archivo CSV: ");
            } else {
                entradaValida = true;
            }
        }

        System.out.println("Intentando abrir: " + archivo);
        listaDatos.clear();
        List<Integer> datosCargados = cargarDesdeCSV(archivo);

        if (datosCargados.isEmpty()) {
            System.out.println("No se cargaron datos del archivo. Verifique la ruta, el contenido (solo números separados por comas), o los permisos del archivo.");
        } else {
            listaDatos.clear();
            listaDatos.addAll(datosCargados);
            System.out.println("Datos cargados correctamente.");
            System.out.println("Datos cargados: " + listaDatos);
        }
    }

    private static List<Integer> cargarDesdeCSV(String archivo) {
        List<Integer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] numeros = linea.split(",");
                for (String num : numeros) {
                    try {
                        String numeroLimpio = num.trim();
                        if (!numeroLimpio.isEmpty()) {
                            lista.add(Integer.parseInt(numeroLimpio));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al procesar el número: " + num + " (ignorado)");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    static class BubbleSort {
        public static void ordenar(List<Integer> lista) {
            int n = lista.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (lista.get(j) > lista.get(j + 1)) {
                        int temp = lista.get(j);
                        lista.set(j, lista.get(j + 1));
                        lista.set(j + 1, temp);
                    }
                }
            }
            System.out.println("Lista ordenada con Bubble Sort.");
        }

        public static void opcionBubbleSort(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }
            ordenar(lista);
            System.out.println("Lista ordenada: " + lista);
        }
    }

    static class EnhancedBubbleSort {
        public static void ordenar(List<Integer> lista) {
            int n = lista.size();
            boolean intercambiado;
            for (int i = 0; i < n - 1; i++) {
                intercambiado = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (lista.get(j) > lista.get(j + 1)) {
                        int temp = lista.get(j);
                        lista.set(j, lista.get(j + 1));
                        lista.set(j + 1, temp);
                        intercambiado = true;
                    }
                }
                if (!intercambiado) break;
            }
            System.out.println("Lista ordenada con Enhanced Bubble Sort.");
        }

        public static void opcionEnhancedBubbleSort(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }
            ordenar(lista);
            System.out.println("Lista ordenada: " + lista);
        }
    }

    static class QuickSort {
        public static void ordenar(List<Integer> lista, int inicio, int fin) {
            if (inicio < fin) {
                int indicePivote = particion(lista, inicio, fin);
                ordenar(lista, inicio, indicePivote - 1);
                ordenar(lista, indicePivote + 1, fin);
            }
        }

        private static int particion(List<Integer> lista, int inicio, int fin) {
            int pivote = lista.get(fin);
            int i = inicio - 1;
            for (int j = inicio; j < fin; j++) {
                if (lista.get(j) < pivote) {
                    i++;
                    int temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
            int temp = lista.get(i + 1);
            lista.set(i + 1, lista.get(fin));
            lista.set(fin, temp);
            return i + 1;
        }

        public static void opcionQuickSort(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }
            ordenar(lista, 0, lista.size() - 1);
            System.out.println("Lista ordenada con Quick Sort.");
            System.out.println("Lista ordenada: " + lista);
        }
    }

    static class SelectionSort {
        public static void ordenar(List<Integer> lista) {
            int n = lista.size();
            for (int i = 0; i < n - 1; i++) {
                int indiceMinimo = i;
                for (int j = i + 1; j < n; j++) {
                    if (lista.get(j) < lista.get(indiceMinimo)) {
                        indiceMinimo = j;
                    }
                }
                int temp = lista.get(i);
                lista.set(i, lista.get(indiceMinimo));
                lista.set(indiceMinimo, temp);
            }
            System.out.println("Lista ordenada con Selection Sort.");
        }

        public static void opcionSelectionSort(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }
            ordenar(lista);
            System.out.println("Lista ordenada: " + lista);
        }
    }

    static class MergeSort {
        public static void ordenar(List<Integer> lista) {
            if (lista.size() < 2) return;

            int mitad = lista.size() / 2;
            List<Integer> izquierda = new ArrayList<>(lista.subList(0, mitad));
            List<Integer> derecha = new ArrayList<>(lista.subList(mitad, lista.size()));

            ordenar(izquierda);
            ordenar(derecha);
            merge(lista, izquierda, derecha);
        }

        private static void merge(List<Integer> lista, List<Integer> izquierda, List<Integer> derecha) {
            int i = 0, j = 0, k = 0;
            while (i < izquierda.size() && j < derecha.size()) {
                if (izquierda.get(i) < derecha.get(j)) {
                    lista.set(k++, izquierda.get(i++));
                } else {
                    lista.set(k++, derecha.get(j++));
                }
            }
            while (i < izquierda.size()) {
                lista.set(k++, izquierda.get(i++));
            }
            while (j < derecha.size()) {
                lista.set(k++, derecha.get(j++));
            }
        }

        public static void opcionMergeSort(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }
            ordenar(lista);
            System.out.println("Lista ordenada con Merge Sort.");
            System.out.println("Lista ordenada: " + lista);
        }
    }

    static class BinarySearch {
        public static int buscar(List<Integer> lista, int objetivo) {
            int inicio = 0;
            int fin = lista.size() - 1;

            while (inicio <= fin) {
                int medio = inicio + (fin - inicio) / 2;
                if (lista.get(medio) == objetivo) return medio;
                if (lista.get(medio) < objetivo) inicio = medio + 1;
                else fin = medio - 1;
            }
            return -1;
        }

        public static void opcionBinarySearch(List<Integer> lista) {
            if (lista.isEmpty()) {
                System.out.println("No hay datos cargados. Cargue datos primero.");
                return;
            }

            boolean estaOrdenada = true;
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    estaOrdenada = false;
                    break;
                }
            }
            if (!estaOrdenada) {
                System.out.println("La lista no está ordenada. Ordene primero con un algoritmo (opciones 3-7).");
                return;
            }

            System.out.print("Ingrese el número que desea buscar: ");
            try {
                int objetivo = Integer.parseInt(scanner.nextLine().trim());
                int resultado = buscar(lista, objetivo);
                if (resultado == -1) {
                    System.out.println("El número no está en la lista.");
                } else {
                    System.out.println("El número " + objetivo + " se encuentra en el índice: " + resultado);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
    }

    private static void mostrarInformacionDesarrollador() {
        System.out.println("\n========================================");
        System.out.println("Información del Desarrollador:");
        System.out.println("Nombre: Carlos Eduardo Toj Dardón");
        System.out.println("Carrera: Estudiante Ingeniería en Sistemas");
        System.out.println("Carné: 202403768");
        System.out.println("Correo: toj2492@gmail.com");
        System.out.println("========================================");
    }

    private static void mostrarPresentacionYouTube() {
        System.out.println("\n========================================");
        System.out.println("Presentación Final en YouTube:");
        System.out.println("Título del Video: Proyecto Estructura de Datos: Implementación de Algoritmos de Ordenamiento y Búsqueda | Universidad Da Vinci");
        System.out.println("Enlace: Pronto");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        mostrarInformacion();
        scanner.close();
    }
}