package Corte2.TareaEstudiantes;

import javax.swing.*;
import java.io.*;

public class Entrada {
    private GestionEstudiantes gestor;

    public static void main(String[] args) {
        new Entrada();
    }

    public Entrada() {
        gestor = new GestionEstudiantes();
        this.menu();
    }

    private void menu() {
        int opcion;
        ObjectOutputStream oos;
        File archivo;
        try {
            archivo= new File("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin");
            if (!archivo.exists()){
                oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
                oos.writeObject(gestor);
                oos.close();
            }
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                    ===OPCIONES===
                    1.CREAR ESTUDIANTE
                    2.BUSCAR ESTUDIANTE
                    3.VER TODOS
                    4.ELIMINAR ESTUDIANTE
                    5.MODIFICAR ESTUDIANTE
                    0.SALIR
                    """));
            switch (opcion) {
                case 1:
                    this.gestor.nuevoEstudiante();
                    break;
                case 2:
                    this.gestor.buscaEstudiante();
                    break;
                case 3:
                    this.gestor.verTodos();
                    break;
                case 4:
                    this.gestor.eliminarEstudiante();
                    break;
                case 5:
                    this.menuModificar();
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ese codigo para el menu no existe!!!");
                    break;
            }
        } while (opcion != 0);
    }

    private void menuModificar () {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                    ===OPCIONES===
                    1.MODIFICAR CODIGO
                    2.MODIFICAR NOMBRE
                    3.MODIFICAR NOTA
                    0.REGRESAR
                    """));
            switch (opcion) {
                case 1:
                    this.gestor.modifyCodigo();
                    break;
                case 2:
                    this.gestor.modifyNombre();
                    break;
                case 3:
                    this.gestor.modifyNota();
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ese codigo para el menu no existe!!!");
                    break;
            }
        } while (opcion != 0);
    }
}


