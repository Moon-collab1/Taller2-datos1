import java.io.IOException;
import java.util.List;
import java.util.Scanner;
//mys
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 : registrarUsuario();
                break;
                case 2 : listarUsuarios();
                break;
                case 3 : actualizarUsuario();
                break;
                case 4 : eliminarUsuario();
                break;
                case 5 : registrarPrestamo();
                break;
                case 6 : listarPrestamos();
                break;
                case 7 : historialcompleto();
                break;
                case 0 : System.out.println("¡Hasta luego!");
                break;
                default : System.out.println("Opción no válida.");
                break;
            }

        } while (opcion != 0);
    }

    static void mostrarMenu() {
        System.out.println("\n===== MENÚ BIBLIOTECA =====");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Eliminar usuario");
        System.out.println("5. Registrar préstamo");
        System.out.println("6. Listar préstamos");
        System.out.println("7. Historial completo");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    // ---------- USUARIOS ----------

    static int generarNuevoIdUsuario() throws IOException {
        List<Usuario> lista = Usuario.leerUsuarios();
        int maxId = 0;
        for (Usuario u : lista) {
            if (u.getId() > maxId) maxId = u.getId();
        }
        return maxId + 1;
    }

    static boolean verificaridUsuario(int id) throws IOException {
        for (Usuario u : Usuario.leerUsuarios()) {
            if (u.getId() == id) return true;
        }
        return false;
    }

    static void registrarUsuario() {
        try {
            int id = generarNuevoIdUsuario();
            System.out.println("id asignado: " + id);

            System.out.println("Digite nombre:");
            String nombre = sc.nextLine();

            System.out.println("Digite apellido:");
            String lastname = sc.nextLine();

            System.out.println("Digite telefono:");
            int telefono = Integer.parseInt(sc.nextLine());

            Usuario nuevo = new Usuario(id, nombre, lastname, telefono);
            Usuario.crearUsuario(nuevo);
            System.out.println("Usuario registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Hubo un error al registrar el usuario.");
        }
    }

    static void listarUsuarios() {
        try {
            List<Usuario> lista = Usuario.leerUsuarios();
            if (lista.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            System.out.println("\n-- Usuarios --");
            for (Usuario u : lista) {
                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getName()
                        + " " + u.getLastname() + " | Tel: " + u.getTelefono());
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer los usuarios.");
        }
    }


    static void listarUsuario(int id) {
        try {
            List<Usuario> lista = Usuario.leerUsuarios();
            if (lista.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            System.out.println("\n-- Usuarios --");
            for (Usuario u : lista) {
                if(u.getId()==id){
                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getName()
                        + " " + u.getLastname() + " | Tel: " + u.getTelefono());
                }

            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer los usuarios.");
        }
    }

    static void actualizarUsuario() {
        try {
            System.out.println("Digite el id del usuario a actualizar:");
            int id = Integer.parseInt(sc.nextLine());

            if (!verificaridUsuario(id)) {
                System.out.println("No existe un usuario con ese id.");
                return;
            }

            System.out.println("Nuevo nombre:");
            String nombre = sc.nextLine();
            System.out.println("Nuevo apellido:");
            String lastname = sc.nextLine();
            System.out.println("Nuevo telefono:");
            int telefono = Integer.parseInt(sc.nextLine());

            Usuario.actualizarUsuario(id, nombre, lastname, telefono);
            System.out.println("Usuario actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Hubo un error al actualizar el usuario.");
        }
    }

    static void eliminarUsuario() {
        try {
            System.out.println("Digite el id del usuario a eliminar:");
            int id = Integer.parseInt(sc.nextLine());

            if (Usuario.eliminarUsuario(id)) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No existe un usuario con ese id.");
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al eliminar el usuario.");
        }
    }

    // ---------- PRESTAMOS ----------

    static int generarNuevoIdPrestamo() throws IOException {
        List<prestamo> lista = prestamo.leerPrestamos();
        int maxId = 0;
        for (prestamo p : lista) {
            if (p.getId() > maxId) maxId = p.getId();
        }
        return maxId + 1;
    }

    static void registrarPrestamo() {
        try {
            int id = generarNuevoIdPrestamo();
            System.out.println("id asignado: " + id);

            System.out.println("Digite id del usuario que solicita el préstamo:");
            int idu = Integer.parseInt(sc.nextLine());

            if (!verificaridUsuario(idu)) {
                System.out.println("No existe un usuario con ese id.");
                return;
            }

            System.out.println("Digite nombre del artículo/libro:");
            String nombre = sc.nextLine();

            System.out.println("Digite fecha de préstamo (dd/mm/aaaa):");
            String fechap = sc.nextLine();

            System.out.println("Digite si el prestamo esta(ACTIVO/DEVUELTO):");
            String fechad = sc.nextLine();

            prestamo nuevo = new prestamo(id, idu, nombre, fechap, fechad);
            prestamo.crearPrestamo(nuevo);
            System.out.println("Préstamo registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Hubo un error al registrar el préstamo.");
        }
    }

        static void listarPrestamos() {
        try {
            System.out.println("digite el id:");
            int id = Integer.parseInt(sc.nextLine());
            List<prestamo> lista = prestamo.leerPrestamos();
            if (lista.isEmpty()) {
                System.out.println("No hay préstamos registrados.");
                return;
            }
            System.out.println("\n-- Préstamos --");
            for (prestamo p : lista) {
                if(p.getIdu()==id){
                System.out.println("ID: " + p.getId() + " | Usuario: " + p.getIdu()
                        + " | Artículo: " + p.getName() + " | Prestado: " + p.getFechap()
                        + " | ESTADO: " + p.getFechad());
                }

            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer los préstamos.");
        }
    }

    static void listarPrestamo(int id) {
        try {
            List<prestamo> lista = prestamo.leerPrestamos();
            if (lista.isEmpty()) {
                System.out.println("No hay préstamos registrados.");
                return;
            }
            System.out.println("\n-- Préstamos --");
            for (prestamo p : lista) {
                if(p.getIdu()==id){
                System.out.println("ID: " + p.getId() + " | Usuario: " + p.getIdu()
                        + " | Artículo: " + p.getName() + " | Prestado: " + p.getFechap()
                        + " | ESTADO: " + p.getFechad());
                }

            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer los préstamos.");
        }
    }
    static void historialcompleto(){
        try {
        System.out.println("digite el id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("========================================");
        System.out.println("         HISTORIAL DEL LECTOR           ");
        System.out.println("========================================");   
        listarUsuario(id);
        listarPrestamo(id);
        cantidadPrestamos(id);
        } catch (Exception e) {
            System.out.println("hubo un error");
        }


         
                
        

    }
    static void cantidadPrestamos(int id) throws IOException {
    List<prestamo> lista = prestamo.leerPrestamos();
        int total = 0;
        int activo =0;
        int devuelto =0;
        for (prestamo p : lista) {
            if(p.getIdu() == id){
            if (p.getFechad().equalsIgnoreCase("ACTIVO") ) {
                total = total+1;
                activo=activo+1;
            }else{
                total = total+1;
                devuelto=devuelto+1;
            }
            }
        }
        System.out.println("");
        System.out.println("Total de préstamos:"+total);
        System.out.println("Préstamos activos:"+activo);
        System.out.println("Préstamos devueltos:"+devuelto);
    }

    }