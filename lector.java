public class lector {
private int id;
private String nombre;
private String apellido;
private String telefono;
public lector(int id, String nombre, String apellido, String telefono) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
}
public int getId() {
    return id;
}
public String getNombre() {
    return nombre;
}
public String getApellido() {
    return apellido;
}
public String getTelefono() {       
    return telefono;
}
public String toString() {
    return id + "," + nombre + "," + apellido + "," + telefono;
}
}   