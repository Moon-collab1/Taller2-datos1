public class prestamo {
    private int idprestamo;
    private int idlector;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private lector lector;

    public prestamo(int idprestamo, int idlector, String fechaPrestamo, String fechaDevolucion, lector lector) {
        this.idprestamo = idprestamo;
        this.idlector = idlector;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.lector = lector;
    }
    public int getIdprestamo() {
        return idprestamo;
    }
    public int getIdlector() {
        return idlector;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    public lector getLector() {
        return lector;          
    }
    public String toString() {
        return idprestamo + "," + idlector + "," + fechaPrestamo + "," + fechaDevolucion;
    }
}