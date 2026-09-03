import java.io.IOException;
import java.util.List;
import java.util.Scanner;
//mys
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
       try {
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
                case 8 : listadoLectoresPorPrestamos();
                break;
                case 9 : librosPrestamo();
                break;
                case 0 : System.out.println("¡Hasta luego!");
                break;
                default : System.out.println("Opción no válida.");
                break;
            }

        } while (opcion != 0);
       } catch (Exception e) {
        System.out.println("hubo un error, lamentamos la inconvenencia");
       } 
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
        System.out.println("8. usuario con mas prestamos");
        System.out.println("9. libros prestados");
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
//8./////

static void listadoLectoresPorPrestamos() throws IOException{
List<prestamo> prestamos = prestamo.leerPrestamos();
List<Usuario> usuarios =Usuario.leerUsuarios();

int n= usuarios.size();

int[] totales =new int[n];
int[] activos =new int[n];
int[] devueltos = new int[n];

for (int i = 0; i < n; i++) {
    int id = usuarios.get(i).getId();
    for(prestamo p : prestamos){
        if(p.getIdu()==id){
            totales[i]++;
        if(p.getFechad().equalsIgnoreCase("ACTIVO")){
            activos[i]++;
        }else{
            devueltos[i]++;
        }
        }
    }
}

for (int i = 0; i < n-1; i++) {
    for (int j = 0; j < n-1-i; j++) {
        if(debeintercambiar(usuarios.get(j), usuarios.get(j+1), totales[j],totales[j+1])){
            Usuario temp= usuarios.get(j);
            usuarios.set(j, usuarios.get(j+1));
            usuarios.set(j+1, temp);

            int t=totales[j];totales[j]=totales[j+1];totales[j+1]=t;
            int a=activos[j];activos[j]=activos[j+1];activos[j+1]=a;
            int b= devueltos[j];devueltos[j]=devueltos[j+1];devueltos[j+1]=b;
        }
    }
}

     

System.out.println("========================================");
System.out.println("         LECTORES CON MÁS PRÉSTAMOS     ");
System.out.println("========================================");
System.out.printf("%-12s %-20s %15s %12s %8s%n","ID","USUARIO","TOTAL","ACTIVOS","DEVUELTOS");
System.out.println("--------------------------------------------------------------------------------");
for (int i = 0; i < n; i++) {
    Usuario u = usuarios.get(i);
        System.out.printf("%-12d %-20s %15d %12d %8d%n",u.getId(),u.getName()+" "+u.getLastname(),totales[i],activos[i],devueltos[i]);
    
}



}

static boolean debeintercambiar(Usuario a, Usuario b, int A, int B){
if(A!=B){ 
    return A<B;
}
if(!a.getLastname().equalsIgnoreCase(b.getLastname())){
    return a.getLastname().compareTo(b.getLastname())>0;
}
if(!a.getName().equalsIgnoreCase(b.getName())){
    return a.getName().compareTo(b.getName())>0;
}
return a.getId() > b.getId();
}

//////////9///////
    static void librosPrestamo() {
        try {
            List<prestamo> lista = prestamo.leerPrestamos();
            if (lista.isEmpty()) {
                System.out.println("No hay préstamos registrados.");
                return;
            }

            System.out.println("\n-- Préstamos --");
            for (prestamo p : lista) {
                if(p.getFechad().equalsIgnoreCase("ACTIVO")){
                System.out.println("ID: " + p.getId() + " | Usuario: " + p.getIdu()
                        + " | Artículo: " + p.getName() + " | Prestado: " + p.getFechap());
                }

            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer los préstamos.");
        }
    }

    }