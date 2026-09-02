import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//mys
class Usuario {
    private int id;
    private String name;
    private String lastname;
    private int telefono;

    public Usuario(int id, String name, String lastname, int telefono) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (!Character.isUpperCase(name.charAt(0)))
            throw new IllegalArgumentException("Debe digitar el nombre con la primera letra mayuscula.");
        this.name = name;
    }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public static void crearUsuario(Usuario usuario) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv", true))) {
            bw.write(usuario.toString());
            bw.newLine();
        }
    }

    public static List<Usuario> leerUsuarios() throws IOException {
        List<Usuario> lista = new ArrayList<>();
        File archivo = new File("usuarios.csv");
        if (!archivo.exists()) return lista;

        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isBlank()) continue;
                String[] datos = linea.split(",");
                lista.add(new Usuario(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3])));
            }
        }
        return lista;
    }

    public static boolean actualizarUsuario(int id, String nuevoNombre, String nuevoLastname, int nuevoTelefono) throws IOException {
        List<Usuario> lista = leerUsuarios();
        boolean encontrado = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"))) {
            for (Usuario u : lista) {
                if (u.getId() == id) {
                    u.setName(nuevoNombre);
                    u.setLastname(nuevoLastname);
                    u.setTelefono(nuevoTelefono);
                    encontrado = true;
                }
                bw.write(u.toString());
                bw.newLine();
            }
        }
        return encontrado;
    }

    public static boolean eliminarUsuario(int id) throws IOException {
        List<Usuario> lista = leerUsuarios();
        boolean encontrado = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"))) {
            for (Usuario u : lista) {
                if (u.getId() == id) {
                    encontrado = true;
                    continue;
                }
                bw.write(u.toString());
                bw.newLine();
            }
        }
        return encontrado;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + lastname + "," + telefono;
    }
}