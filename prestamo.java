import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//mys
class prestamo {
    private int id;
    private int idu;
    private String name;
    private String fechap;
    private String fechad;

    public prestamo(int id, int idu, String name, String fechap, String fechad) {
        this.id = id;
        this.idu = idu;
        this.name = name;
        this.fechap = fechap;
        this.fechad = fechad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdu() { return idu; }
    public void setIdu(int idu) { this.idu = idu; }

    public String getName() { return name; }
    public void setName(String name) {
        if (!Character.isUpperCase(name.charAt(0)))
            throw new IllegalArgumentException("Debe digitar el nombre con la primera letra mayuscula.");
        this.name = name;
    }

    public String getFechap() { return fechap; }
    public void setFechap(String fechap) { this.fechap = fechap; }

    public String getFechad() { return fechad; }
    public void setFechad(String fechad) { this.fechad = fechad; }

    public static void crearPrestamo(prestamo p) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prestamos.csv", true))) {
            bw.write(p.toString());
            bw.newLine();
        }
    }

    public static List<prestamo> leerPrestamos() throws IOException {
        List<prestamo> lista = new ArrayList<>();
        File archivo = new File("prestamos.csv");
        if (!archivo.exists()) return lista;

        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isBlank()) continue;
                String[] datos = linea.split(",");
                lista.add(new prestamo(
                        Integer.parseInt(datos[0]),
                        Integer.parseInt(datos[1]),
                        datos[2],
                        datos[3],
                        datos[4]));
            }
        }
        return lista;
    }

    public static boolean actualizarPrestamo(int id, int idu, String nuevoNombre, String nuevoFechap, String nuevoFechad) throws IOException {
        List<prestamo> lista = leerPrestamos();
        boolean encontrado = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prestamos.csv"))) {
            for (prestamo p : lista) {
                if (p.getId() == id) {
                    p.setIdu(idu);
                    p.setName(nuevoNombre);
                    p.setFechap(nuevoFechap);
                    p.setFechad(nuevoFechad);
                    encontrado = true;
                }
                bw.write(p.toString());
                bw.newLine();
            }
        }
        return encontrado;
    }

    public static boolean eliminarPrestamo(int id) throws IOException {
        List<prestamo> lista = leerPrestamos();
        boolean encontrado = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prestamos.csv"))) {
            for (prestamo p : lista) {
                if (p.getId() == id) {
                    encontrado = true;
                    continue;
                }
                bw.write(p.toString());
                bw.newLine();
            }
        }
        return encontrado;
    }

    @Override
    public String toString() {
        return id + "," + idu + "," + name + "," + fechap + "," + fechad;
    }
}