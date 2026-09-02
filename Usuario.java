import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private int id;
    private String name;
    private String lastname;
    private int telefono;

    public Usuario() {
    }

    public Usuario(int id, String name, String lastname, int telefono) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        if (!Character.isUpperCase(name.charAt(0)))
            throw new IllegalArgumentException("Debe digitar el nombre con la primera letra mayuscula.");
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

        public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void crearUsuario(Usuario usuario)
            throws IOException {
        FileWriter fw = new FileWriter("usuarios.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(usuario.toString());
        bw.newLine();
        bw.close();
    }

    public List<Usuario> leerUsuarios()
            throws IOException {
        List<Usuario> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File("usuarios.csv"));

        while (sc.hasNextLine()) {
            String[] datos = sc.nextLine().split(",");
            lista.add(new Usuario(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    datos[2],
                    Integer.parseInt(datos[3])));
        }
        sc.close();
        return lista;
    }

    public void actualizarUsuario(int id,
            String nuevoNombre, String nuevoLastname, int nuevoTelefono)
            throws IOException {

        List<Usuario> lista = this.leerUsuarios();
        BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"));

        for (Usuario u : lista) {
            if (u.getId() == id) {
                u.setName(nuevoNombre);
                u.setLastname(nuevoLastname);
                u.setTelefono(nuevoTelefono);
            }
            bw.write(u.toString());
            bw.newLine();
        }
        bw.close();
    }

    public  void eliminarUsuario(int id)
            throws IOException {

        List<Usuario> lista = this.leerUsuarios();
        BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"));

        for (Usuario u : lista) {
            if (u.getId() != id) {
                bw.write(u.toString());
                bw.newLine();
            }
        }
        bw.close();
    }

    @Override
    public String toString() {
        return String.valueOf(this.id) + ',' + this.name + ',' + this.lastname+","+String.valueOf(this.telefono);
    }
}

