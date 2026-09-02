import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class lectura {
    public static List<lector> leerLectores() throws IOException {
        List<lector> lista = new ArrayList<>();
        BufferedReader br =
                new BufferedReader(new FileReader("lectores.csv"));
        String linea = br.readLine();
        linea = br.readLine();
        while (linea != null) {
            if (!linea.isEmpty()) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                String telefono = datos[3];
                    lector lector =new lector(id, nombre, apellido, telefono);
                lista.add(lector);
            }
            linea = br.readLine();
        }
        br.close();
        return lista;
    }

    public static void listarLectores() throws IOException {
        List<lector> lista = leerLectores();
        if (lista.isEmpty()) {
            System.out.println("No hay lectores registrados.");
            return;
        }
        System.out.println("===== LISTA DE LECTORES =====");
        for (lector l : lista) {
            System.out.println(
                    "ID: " + l.getId() +" | Nombre: " + l.getNombre() +" | Apellido: " + l.getApellido() +" | Telefono: " + l.getTelefono());
        }
    }
    public static void eliminarLector(int id) throws IOException {
        List<lector> lista = leerLectores();
        boolean encontrado = false;
        for (lector l : lista) {
            if (l.getId() == id) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El lector no existe.");
            return;
        }
        if (tienePrestamoActivo(id)) {
            System.out.println(
                    "No se puede eliminar el lector porque tiene "
                    + "un prestamo activo."
            );
            return;
        }
        BufferedWriter bw =
        new BufferedWriter(new FileWriter("lectores.csv"));
        bw.write("id_lector,nombre,apellido,telefono");
        bw.newLine();
        for (lector l : lista) {
            if (l.getId() != id) {
                bw.write(l.toString());
                bw.newLine();
            }
        }

        bw.close();

        System.out.println("Lector eliminado correctamente.");
    }


    public static boolean tienePrestamoActivo(int idLector)
            throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader("prestamos.csv"));

        String linea = br.readLine();

        // Saltar encabezado
        linea = br.readLine();

        while (linea != null) {

            if (!linea.isEmpty()) {

                String[] datos = linea.split(",", -1);

                int id = Integer.parseInt(datos[1]);

                String fechaDevolucion = datos[4];
                if (id == idLector &&
                        fechaDevolucion.isEmpty()) {
                    br.close();
                    return true;
                }
            }
            linea = br.readLine();
        }
        br.close();
        return false;
    }
}