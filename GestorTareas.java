import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();


public void agregarTarea(Tarea tarea){
    System.out.println("Tarea agregada");
    tareas.add(tarea);
}
public void mostrarTarea(){
    boolean encontrado = false;
    System.out.println("***Lista de tareas***");
    for(Tarea tarea : tareas){
        System.out.println(tarea.toString());
        encontrado = true;
    }
    if(!encontrado){
        System.out.println("No hay tareas");
    }
}

public void completarTarea(int id){
    boolean encontrado = false;
    for(Tarea tarea : tareas){
        if(tarea.getId() == id){
            tarea.setCompletada(true);
            System.out.println("Tarea completada");
            encontrado = true;
            break;
        }
    }
    if(!encontrado){
        System.out.println("Tarea no encontrada");
    }    
}

public void eliminarTarea(int id){
    boolean encontrado = false;
    Iterator<Tarea> iter = tareas.iterator();
    while(iter.hasNext()){
        Tarea tarea = iter.next();
        if(tarea.getId() == id){
            iter.remove();
            System.out.println("Tarea eliminada");
            encontrado = true;
            break;
        }
    }
    if(!encontrado){
        System.out.println("Tarea no encontrada");
    }
}

public void mostrarTareaCompletada(){
    boolean encontrado = false;
    System.out.println("***Lista de tareas completadas***");
    for(Tarea tarea : tareas){
        if(tarea.getCompletada()){
            System.out.println(tarea.toString());
            encontrado = true;
        }
    }
    if(!encontrado){
        System.out.println("No hay tareas completadas");
    }
}

public void mostrarTareaIncompletada(){
    boolean encontrado = false;
    System.out.println("***Lista de tareas incompletadas***");
    for(Tarea tarea : tareas){
        if(!tarea.getCompletada()){
            System.out.println(tarea.toString());
            encontrado = true;
        }
    }
    if(!encontrado){
        System.out.println("No hay tareas incompletadas");
    }
}

public void filtrarPorPrioridad(String prioridad){
    boolean encontrado = false;
    System.out.println("***Lista de tareas por prioridad***");
    for(Tarea tarea : tareas){
        if(tarea.getPrioridad().equals(prioridad)){
            System.out.println(tarea.toString());
            encontrado = true;
        }
    }
    if(!encontrado){
        System.out.println("No hay tareas con la prioridad " + prioridad);
    }
}

public void buscarPorTitulo(String titulo){
    boolean encontrado = false;
    System.out.println("***Lista de tareas por titulo***");
    for(Tarea tarea : tareas){
        if(tarea.getTitulo().toLowerCase().contains(titulo)){
            System.out.println(tarea.toString());
            encontrado = true;
        }
    }
    if(!encontrado){
        System.out.println("No hay tareas con el titulo " + titulo);
    }
} 

public void ordenarPorFecha() {
    tareas.sort((t1, t2) -> {
        LocalDate fecha1 = t1.getFechaLimite();
        LocalDate fecha2 = t2.getFechaLimite();
        return fecha1.compareTo(fecha2);
    });

    mostrarTarea();
}

public void ordenarPorPrioridad() {
    tareas.sort((t1, t2) -> {
        int p1 = prioridadValor(t1.getPrioridad());
        int p2 = prioridadValor(t2.getPrioridad());
        return Integer.compare(p1, p2); // de menor a mayor (alta es menor)
    });

    mostrarTarea();
}

private int prioridadValor(String prioridad) {
    switch (prioridad.toLowerCase()) {
        case "alta": return 1;
        case "media": return 2;
        case "baja": return 3;
        default: return 4; // por si acaso
    }
}


public void guardarTareas(){
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("tareas.txt"))){
    for(Tarea tarea : tareas){
        String linea = String.format("%d|%s|%s|%s|%b|%s\n",tarea.getId(),tarea.getTitulo(),tarea.getDescripcion(),tarea.getFechaLimite(),tarea.getCompletada(),tarea.getPrioridad()); 
        writer.write(linea);
        writer.newLine();
    }   }
    catch(Exception e){
        System.out.println("Error al guardar las tareas");
    }
}

public void cargarTareas() {
    try (BufferedReader reader = new BufferedReader(new FileReader("tareas.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            try {
                String[] datos = linea.split("\\|");
                if (datos.length == 6) {
                    int id = Integer.parseInt(datos[0]);
                    String titulo = datos[1];
                    String descripcion = datos[2];
                    LocalDate fechaLimite = LocalDate.parse(datos[3]);
                    boolean completada = Boolean.parseBoolean(datos[4]);
                    String prioridad = datos[5];
                    Tarea tarea = new Tarea(id, titulo, descripcion, fechaLimite,prioridad );
                    tarea.setCompletada(completada);
                    tareas.add(tarea);
                }
            } catch (Exception e) {
                System.err.println("Error al procesar la línea: " + linea);
                e.printStackTrace();
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
        e.printStackTrace();
    }
}
}