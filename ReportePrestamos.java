import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportePrestamos {
    public static void mostrarLectoresConMasPrestamos() throws IOException {
        List<Usuario> usuarios = Usuario.leerUsuarios();
        List<prestamo> prestamos = prestamo.leerPrestamos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay lectores registrados.");
            return;
        }
        List<String[]> reporte = new ArrayList<>();
        for (Usuario u : usuarios) {
            int total = 0, activos = 0, devueltos = 0;
            for (prestamo p : prestamos) {
                if (p.getIdu() == u.getId())
                    total++;
                if (p.getFechad().isEmpty()) {
                    activos++;
                } else {
                    devueltos++;
                }
            }

            String[] dato = { String.valueOf(u.getId()), u.getName(), u.getLastname(), String.valueOf(total),
                    String.valueOf(activos), String.valueOf(devueltos)
            };
            reporte.add(dato);
        }
        for (

                int i = 0; i < reporte.size() - 1; i++) {
            int posicionMayor = i;
            for (int j = i + 1; j < reporte.size(); j++) {
                int totalJ = Integer.parseInt(reporte.get(j)[3]);
                int totalMayor = Integer.parseInt(reporte.get(posicionMayor)[3]);
                if (totalJ > totalMayor) {
                    posicionMayor = j;
                } else if (totalJ == totalMayor) {
                    String apellidoJ = reporte.get(j)[2];
                    String apellidoMayor = reporte.get(posicionMayor)[2];
                    if (apellidoJ.compareToIgnoreCase(apellidoMayor) < 0) {
                        posicionMayor = j;
                    }
                }
            }
            if (posicionMayor != i) {
                String[] temporal = reporte.get(i);
                reporte.set(i, reporte.get(posicionMayor));
                reporte.set(posicionMayor, temporal);
            }
        }
        System.out.println();
        System.out.println("==========================================");
        System.out.println("       LECTORES CON MÁS PRÉSTAMOS");
        System.out.println("==========================================");
        System.out.println("ID | Lector | Total | Activos | Devueltos");
        System.out.println("------------------------------------------");
        for (String[] dato_ : reporte) {
            String nombreCompleto = dato_[1] + " " + dato_[2];
            System.out
                    .println(dato_[0] + " | " + nombreCompleto + " | " + dato_[3] + " | " + dato_[4] + "| " + dato_[5]);
        }
    }
}