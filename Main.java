import java.util.Scanner;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrar();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 : registrar();
                break;
                case 2 : mostrartodos();
                break;
                case 3 : eliminar();
                break;
                case 4 : actualizar();
                break;
                case 5 : mostraruno();
                break;
                case 6 : System.out.println("Cerrando el sistema. Hasta pronto.");
                break;
                default : System.out.println("Opción inválida. Intente de nuevo.");
                break;
            }
            System.out.println();
        } while (opcion != 6);

        sc.close();
    }

    static void mostrar() {
        System.out.println("=== Biblioteca San Thomas: Gestión de Turnos ===");
        System.out.println("1. Registrar nuevo lector");
        System.out.println("2. Mostrar todos los lectores");
        System.out.println("3. Eliminar a lector");
        System.out.println("4. Registrar un préstamo");
        System.out.println("5. Consultar prestamos del lector");
        System.out.println("6. Salir");
    }

 static void registrar() {
      try {
         int idSec = 1;
         Usuario u = new Usuario();
         
           String name = Main.leerTexto("Digite el nombre del usuario");
           String lastname = Main.leerTexto("Digite el apellido del usuario");
           int telefono = Main.leerEntero("Digite su numero de telefono:");
           u.setId(idSec++);
           u.setName(name);
           u.setLastname(lastname);
           u.setTelefono(telefono);
           u.crearUsuario(u);
          
          List<Usuario> usuarios = u.leerUsuarios();
         Iterator<Usuario> it = usuarios.iterator();

         while (it.hasNext()) {
            System.out.println(it.next());
         }
        u.actualizarUsuario(2, "Manuel", "Contreras",300500834);

      } catch (IllegalArgumentException e) {
         System.out.println(e.getMessage());
      } catch (IOException e) {
         System.out.println(e.getMessage() );
      }

   }


    // ================= ROL A: feature/menu-base =================
    // Responsable de: mostrarMenu (ya dado), registrarTurno, mostrarTurnos

    static void mostrartodos() {

    }

    

    static void eliminar() {
        // TODO (Rol B)
        // Pedir el ID, usar buscarIndicePorId y mostrar los datos o un mensaje de "no existe".
    }

    static void actualizarTurno() {
        // TODO (Rol B)
        // Pedir el ID, verificar que exista y mostrar un submenú para elegir
        // qué campo modificar: paciente, especialidad, duración o valor por minuto.
    }

    static void cancelarTurno() {
        // TODO (Rol B)
        // Pedir el ID, verificar que exista, pedir confirmación (S/N) y eliminar
        // con turnos.remove(indice);
    }


    static void actualizar() {

    }

    static void mostraruno() {

    }

    // ====== Utilidades (ya implementadas, no es necesario modificarlas) ======

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