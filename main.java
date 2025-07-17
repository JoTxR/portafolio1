import java.time.LocalDate;
import java.util.Scanner;

public class main {
public static void main(String[] args) {
    System.out.println("hola, bienvenido al gestor de tareas");
    GestorTareas gestorTareas = new GestorTareas();
    gestorTareas.cargarTareas();
    int opcion = 0;
    while(opcion != 11){
        System.out.println("\nMenu:");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Mostrar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Mostrar solo tareas completadas");
        System.out.println("6. Mostrar solo tareas incompletas");
        System.out.println("7. Filtrar tareas por prioridad");
        System.out.println("8. Buscar por titulo");
        System.out.println("9. Ordenar por fecha");
        System.out.println("10. Ordenar por prioridad");
        System.out.println("11. Salir");
        Scanner scanner = new Scanner(System.in);
        opcion = Integer.parseInt(scanner.nextLine());
        switch(opcion){
            case 1:
                int newid=Tarea.getLastId();
                System.out.println("Ingrese el titulo de la tarea:");
                String titulo = scanner.nextLine();
                System.out.println("Ingrese la descripcion de la tarea:");
                String descripcion = scanner.nextLine();
                System.out.println("Ingrese la fecha limite de la tarea:");
                String fechaLimite = scanner.nextLine();
                System.out.println("Ingrese la prioridad de la tarea (Alta, Media, Baja):");
                String prioridad = scanner.nextLine();
                Tarea tarea = new Tarea(newid, titulo, descripcion, LocalDate.parse(fechaLimite),prioridad);
                gestorTareas.agregarTarea(tarea);
                break;
            case 2:
                gestorTareas.mostrarTarea();
                break;
            case 3:
                System.out.println("Ingrese el id de la tarea:");
                int id = Integer.parseInt(scanner.nextLine());
                gestorTareas.completarTarea(id);
                break;
            case 4:
                System.out.println("Ingrese el id de la tarea:");
                id = Integer.parseInt(scanner.nextLine());
                gestorTareas.eliminarTarea(id);
                break;
            case 5:
                gestorTareas.mostrarTareaCompletada();
                break;
            case 6:
                gestorTareas.mostrarTareaIncompletada();
                break;
            case 7:
                System.out.println("Ingrese la prioridad de la tarea (Alta, Media, Baja):");
                prioridad = scanner.nextLine();
                gestorTareas.filtrarPorPrioridad(prioridad);
                break;
            case 8:
                System.out.println("Ingrese el titulo de la tarea:");
                titulo = scanner.nextLine();
                gestorTareas.buscarPorTitulo(titulo);
                break;
            case 9:
                gestorTareas.ordenarPorFecha();
                break;
            case 10:
                gestorTareas.ordenarPorPrioridad();
                break;
            case 11:
                System.out.println("Hasta luego");
                gestorTareas.guardarTareas();
                scanner.close();
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
 
}

}
}