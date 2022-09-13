package Corte2.TareaEstudiantes;


import java.io.Serial;
import java.io.Serializable;

public class Estudiante implements Serializable {
    @Serial
    private static final long serialVersionUID = 8293625034435629L;
    //Atributos
    private int Codigo;
    private String nombre;
    private float nota;

    public Estudiante() {
    }

    public Estudiante(int codigo, String nombre, float nota) {
        Codigo = codigo;
        this.nombre = nombre;
        this.nota = nota;
    }

    public int getCodigo() {
        return Codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getNota() {
        return nota;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "Codigo=" + Codigo +
                ", nombre='" + nombre + '\'' +
                ", nota=" + nota +
                '}';
    }
}
