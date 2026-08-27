import java.util.ArrayList;
import java.util.Scanner;
public class Main {


    static final int ID = 8;
    static final int PACIENTE = 1;
    static final int ESPECIALIDAD = 2;
    static final int DURACION = 3;
    static final int VALOR_MINUTO = 4;
    static final int CAMPOS = 5;

    static ArrayList<String[]> turnos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> mostrartodos();
                case 3 -> eliminar();
                case 4 -> actualizar();
                case 5 -> mostraruno();
                case 6 -> System.out.println("Cerrando el sistema. Hasta pronto.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 6);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== Clínica San Rafael: Gestión de Turnos ===");
        System.out.println("1. Registrar nuevo turno");
        System.out.println("2. Mostrar todos los turnos");
        System.out.println("3. Buscar turno por ID");
        System.out.println("4. Actualizar un turno");
        System.out.println("5. Cancelar un turno");
        System.out.println("6. Calcular total facturado");
        System.out.println("7. Reporte por especialidad");
        System.out.println("8. Salir");
    }
    static void mostrartodos() {
public static void actualizarUsuario(int id, 
String nuevoNombre, String nuevoEmail) 
throws IOException {
    
    List<usuario> lista = leerUsuarios();
    BufferedWriter bw = 
        new BufferedWriter(new FileWriter("usuarios.csv"));

    for (Usuario u : lista) {
        if (u.getId() == id) {
            u.setNombre(nuevoNombre);
            u.setEmail(nuevoEmail);
        }
        bw.write(u.toString());
        bw.newLine();
    }
    bw.close();
}
      </usuario>
    }
    static void eliminar() {
        // TODO (Rol B)
        // Pedir el ID, usar buscarIndicePorId y mostrar los datos o un mensaje de "no existe".
    }
    static void mostrar() {
        // TODO (Rol B)
        // Pedir el ID, verificar que exista y mostrar un submenú para elegir
        // qué campo modificar: paciente, especialidad, duración o valor por minuto.
    }
    static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }
    static double leerDecimal(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (Exception e) {
                System.out.println("Ingrese un número válido (ej: 1500.50).");
            }
        }
    }
    static String leerTexto(String msg) {
        String valor;
        do {
            System.out.print(msg);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) System.out.println("Este campo no puede quedar vacío.");
        } while (valor.isEmpty());
        return valor;
    }
}