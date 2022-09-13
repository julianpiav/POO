package Corte2.TareaEstudiantes;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GestionEstudiantes implements Serializable{
    @Serial
    private static final long serialVersionUID = 8293625034465629L;
    private  ArrayList<Estudiante> listaEstudiantes;
    //Nuevo estudiante y modificar leen y escriben
    public GestionEstudiantes() {
        listaEstudiantes = new ArrayList<>();
    }

    public void nuevoEstudiante() {
        int codigo;
        String nombre;
        float nota;
        boolean verificar;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos=(GestionEstudiantes)  ois.readObject();
            ois.close();
            do {
                codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite el codigo del estudiante"));
                verificar = this.existeCodigo(codigo);
            } while (verificar);
            nombre = JOptionPane.showInputDialog("Digite los nombres del estudiante");
            do {
                nota = Float.parseFloat(JOptionPane.showInputDialog("Digite la nota del estudiante"));
            }while (nota<0 || nota>5);
            Estudiante estudiante = new Estudiante(codigo, nombre, nota);
            alumnos.listaEstudiantes.add(estudiante);
            oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            oos.writeObject(alumnos);
            oos.close();
            JOptionPane.showMessageDialog(null, "EL ESTUDIANTE SE HA GUARDADO!");
        }  catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
    }

    public void buscaEstudiante() {
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            int codigo;
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del estudiante que quiere encontrar"));
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo) {
                        JOptionPane.showMessageDialog(null, x.toString());
                        break;
                    }
                }
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
    }

    public void verTodos() {
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            if (hayEstudiantes()){
                JOptionPane.showMessageDialog(null, alumnos.listaEstudiantes);
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
    }

    public void eliminarEstudiante() {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            int codigo;
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del estudiante que quiere eliminar"));
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo)
                        alumnos.listaEstudiantes.remove(x);
                    break;
                }
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");
            oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            oos.writeObject(alumnos);
            oos.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
    }

    public void modifyCodigo() {
        int codigo, ncodigo;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del estudiante que quiere cambiarle el codigo"));
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo) {
                        do {
                            ncodigo = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo codigo del estudiante"));
                        }while (this.existeCodigo(ncodigo));
                        x.setCodigo(ncodigo);
                        break;
                    }
                }
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");
            oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            oos.writeObject(alumnos);
            oos.close();
            JOptionPane.showMessageDialog(null, "EL ESTUDIANTE SE HA GUARDADO!");
        } catch (Exception e) {
            System.out.println("Fallo cargando el archivo listaEstudiantes..!!");
        }
    }

    public void modifyNombre() {
        int codigo;
        String nNombre;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del estudiante que quiere cambiarle el nombre"));
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo) {
                        nNombre = JOptionPane.showInputDialog("Digite el nuevo nombre del estudiante");
                        x.setNombre(nNombre);
                        break;
                    }
                }
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");
            oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            oos.writeObject(alumnos);
            oos.close();
            JOptionPane.showMessageDialog(null, "EL ESTUDIANTE SE HA GUARDADO!");
        } catch (Exception e) {
            System.out.println("Fallo cargando el archivo listaEstudiantes..!!");
        }
    }

    public void modifyNota() {
        int codigo;
        float nNota;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del estudiante que quiere cambiarle la nota"));
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo) {
                        do {
                            nNota = Float.parseFloat(JOptionPane.showInputDialog("Digite la nota del estudiante"));
                        }while (nNota<0 || nNota>5);
                        x.setNota(nNota);
                        break;
                    }
                }
            }else
                JOptionPane.showMessageDialog(null,"No hay estudiantes");
            oos = new ObjectOutputStream((new FileOutputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            oos.writeObject(alumnos);
            oos.close();
            JOptionPane.showMessageDialog(null, "EL ESTUDIANTE SE HA GUARDADO!");
        } catch (Exception e) {
            System.out.println("Fallo cargando el archivo listaEstudiantes..!!");
        }
    }

    private boolean existeCodigo(int codigo) {
        boolean hay = false;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            if (hayEstudiantes()){
                for (Estudiante x : alumnos.listaEstudiantes) {
                    if (x.getCodigo() == codigo) {
                        hay = true;
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
        return hay;
    }

    private boolean hayEstudiantes() {
        boolean hay = false;
        ObjectInputStream ois;
        GestionEstudiantes alumnos;
        try {
            ois = new ObjectInputStream((new FileInputStream("./POO2/src/Corte2/TareaEstudiantes/estudiantes.bin")));
            alumnos = (GestionEstudiantes) ois.readObject();
            ois.close();
            if (!alumnos.listaEstudiantes.isEmpty()) {
                hay = true;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No digito un numero para el codigo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontro");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase no se encontro");
        }
        return hay;
    }
}