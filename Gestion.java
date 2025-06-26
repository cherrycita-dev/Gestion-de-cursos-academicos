package com.mycompany.paradigmas;

import java.util.*;

// Interfaces
interface Pagable {
    double calcularPago() throws PagoInvalidoException;
}

interface Calificable {
    double calcularPromedio() throws PromedioInvalidoException;
}

// Excepciones personalizadas
class PagoInvalidoException extends Exception {
    public PagoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

class PromedioInvalidoException extends Exception {
    public PromedioInvalidoException(String mensaje) {
        super(mensaje);
    }
}

// Clase abstracta Persona
abstract class Persona {
    protected String nombre;
    protected String id;
    
    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getId() {
        return id;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    // Método abstracto para mostrar información
    public abstract void mostrarInformacion();
    
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre;
    }
}

// Clase ProfesorTiempoCompleto
class ProfesorTiempoCompleto extends Persona implements Pagable {
    private double salarioMensual;
    
    public ProfesorTiempoCompleto(String nombre, String id, double salarioMensual) {
        super(nombre, id);
        this.salarioMensual = salarioMensual;
    }
    
    public double getSalarioMensual() {
        return salarioMensual;
    }
    
    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }
    
    @Override
    public double calcularPago() throws PagoInvalidoException {
        if (salarioMensual <= 0) {
            throw new PagoInvalidoException("El salario mensual debe ser mayor a 0. Salario actual: " + salarioMensual);
        }
        return salarioMensual;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor Tiempo Completo - " + toString() + ", Salario Mensual: $" + salarioMensual);
    }
}

// Clase ProfesorPorHoras
class ProfesorPorHoras extends Persona implements Pagable {
    private int horasTrabajadas;
    private double pagoPorHora;
    
    public ProfesorPorHoras(String nombre, String id, int horasTrabajadas, double pagoPorHora) {
        super(nombre, id);
        this.horasTrabajadas = horasTrabajadas;
        this.pagoPorHora = pagoPorHora;
    }
    
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
    
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    
    public double getPagoPorHora() {
        return pagoPorHora;
    }
    
    public void setPagoPorHora(double pagoPorHora) {
        this.pagoPorHora = pagoPorHora;
    }
    
    @Override
    public double calcularPago() throws PagoInvalidoException {
        double pagoTotal = horasTrabajadas * pagoPorHora;
        if (pagoTotal <= 0) {
            throw new PagoInvalidoException("El pago total debe ser mayor a 0. Pago calculado: " + pagoTotal);
        }
        return pagoTotal;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor Por Horas - " + toString() + 
                         ", Horas: " + horasTrabajadas + ", Pago/Hora: $" + pagoPorHora);
    }
}

// Clase Estudiante
class Estudiante extends Persona implements Calificable {
    private List<Double> calificaciones;
    
    public Estudiante(String nombre, String id) {
        super(nombre, id);
        this.calificaciones = new ArrayList<>();
    }
    
    public List<Double> getCalificaciones() {
        return calificaciones;
    }
    
    public void agregarCalificacion(double calificacion) {
        if (calificacion >= 0 && calificacion <= 10) {
            calificaciones.add(calificacion);
            System.out.println("Calificación " + calificacion + " agregada exitosamente.");
        } else {
            System.out.println("Calificación inválida. Debe estar entre 0 y 10.");
        }
    }
    
    @Override
    public double calcularPromedio() throws PromedioInvalidoException {
        if (calificaciones.isEmpty()) {
            throw new PromedioInvalidoException("El estudiante " + nombre + " no tiene calificaciones registradas.");
        }
        
        double suma = 0;
        for (double calificacion : calificaciones) {
            suma += calificacion;
        }
        return suma / calificaciones.size();
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Estudiante - " + toString() + ", Calificaciones: " + calificaciones);
        try {
            System.out.println("Promedio: " + String.format("%.2f", calcularPromedio()));
        } catch (PromedioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Clase Curso
class Curso {
    private String nombreCurso;
    private List<Estudiante> estudiantes;
    private Persona profesorAsignado;
    
    public Curso(String nombreCurso, Persona profesorAsignado) {
        this.nombreCurso = nombreCurso;
        this.profesorAsignado = profesorAsignado;
        this.estudiantes = new ArrayList<>();
    }
    
    public String getNombreCurso() {
        return nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    
    public Persona getProfesorAsignado() {
        return profesorAsignado;
    }
    
    public void setProfesorAsignado(Persona profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }
    
    public void agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            System.out.println("Estudiante " + estudiante.getNombre() + " agregado al curso " + nombreCurso);
        } else {
            System.out.println("El estudiante ya está registrado en el curso.");
        }
    }
    
    public void removerEstudiante(Estudiante estudiante) {
        if (estudiantes.remove(estudiante)) {
            System.out.println("Estudiante " + estudiante.getNombre() + " removido del curso " + nombreCurso);
        } else {
            System.out.println("El estudiante no está registrado en el curso.");
        }
    }
    
    public void mostrarInformacionCurso() {
        System.out.println("\n=== INFORMACIÓN DEL CURSO ===");
        System.out.println("Curso: " + nombreCurso);
        System.out.println("Profesor: " + profesorAsignado.getNombre());
        System.out.println("Número de estudiantes: " + estudiantes.size());
        
        if (!estudiantes.isEmpty()) {
            System.out.println("Estudiantes matriculados:");
            for (Estudiante estudiante : estudiantes) {
                System.out.println("  - " + estudiante.getNombre());
            }
        }
    }
}

// Clase principal del sistema
class SistemaGestionCursos {
    private List<Persona> personas;
    private List<Curso> cursos;
    private Scanner scanner;
    
    public SistemaGestionCursos() {
        this.personas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void registrarPersona(Persona persona) {
        personas.add(persona);
        System.out.println("Persona registrada: " + persona.getNombre());
    }
    
    public void registrarCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("Curso registrado: " + curso.getNombreCurso());
    }
    
    // Método que demuestra polimorfismo
    public void mostrarTodasLasPersonas() {
        System.out.println("\n=== LISTADO DE PERSONAS (Polimorfismo) ===");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        for (Persona persona : personas) {
            persona.mostrarInformacion(); // Polimorfismo - cada clase implementa su versión
        }
    }
    
    public void mostrarTodosLosCursos() {
        System.out.println("\n=== LISTADO DE CURSOS ===");
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        for (Curso curso : cursos) {
            curso.mostrarInformacionCurso();
        }
    }
    
    public void procesarPagos() {
        System.out.println("\n=== PROCESAMIENTO DE PAGOS ===");
        boolean hayProfesores = false;
        for (Persona persona : personas) {
            if (persona instanceof Pagable) {
                hayProfesores = true;
                try {
                    double pago = ((Pagable) persona).calcularPago();
                    System.out.println("Pago para " + persona.getNombre() + ": $" + 
                                     String.format("%.2f", pago));
                } catch (PagoInvalidoException e) {
                    System.out.println("Error en pago: " + e.getMessage());
                }
            }
        }
        if (!hayProfesores) {
            System.out.println("No hay profesores registrados para procesar pagos.");
        }
    }
    
    public void procesarPromedios() {
        System.out.println("\n=== PROCESAMIENTO DE PROMEDIOS ===");
        boolean hayEstudiantes = false;
        for (Persona persona : personas) {
            if (persona instanceof Calificable) {
                hayEstudiantes = true;
                try {
                    double promedio = ((Calificable) persona).calcularPromedio();
                    System.out.println("Promedio de " + persona.getNombre() + ": " + 
                                     String.format("%.2f", promedio));
                } catch (PromedioInvalidoException e) {
                    System.out.println("Error en promedio: " + e.getMessage());
                }
            }
        }
        if (!hayEstudiantes) {
            System.out.println("No hay estudiantes registrados para procesar promedios.");
        }
    }
    
    // Métodos del menú interactivo
    public void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE GESTIÓN DE CURSOS          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Registrar Profesor Tiempo Completo       ║");
        System.out.println("║  2. Registrar Profesor Por Horas             ║");
        System.out.println("║  3. Registrar Estudiante                     ║");
        System.out.println("║  4. Crear Curso                              ║");
        System.out.println("║  5. Agregar Estudiante a Curso               ║");
        System.out.println("║  6. Agregar Calificación a Estudiante        ║");
        System.out.println("║  7. Mostrar Todas las Personas               ║");
        System.out.println("║  8. Mostrar Todos los Cursos                 ║");
        System.out.println("║  9. Procesar Pagos                           ║");
        System.out.println("║ 10. Procesar Promedios                       ║");
        System.out.println("║ 11. Buscar Persona por ID                    ║");
        System.out.println("║ 12. Buscar Curso por Nombre                  ║");
        System.out.println("║  0. Salir                                    ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }
    
    public void registrarProfesorTiempoCompleto() {
        System.out.println("\n=== REGISTRAR PROFESOR TIEMPO COMPLETO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Salario mensual: $");
        try {
            double salario = Double.parseDouble(scanner.nextLine());
            ProfesorTiempoCompleto profesor = new ProfesorTiempoCompleto(nombre, id, salario);
            registrarPersona(profesor);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un salario válido.");
        }
    }
    
    public void registrarProfesorPorHoras() {
        System.out.println("\n=== REGISTRAR PROFESOR POR HORAS ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Horas trabajadas: ");
        try {
            int horas = Integer.parseInt(scanner.nextLine());
            System.out.print("Pago por hora: $");
            double pagoPorHora = Double.parseDouble(scanner.nextLine());
            ProfesorPorHoras profesor = new ProfesorPorHoras(nombre, id, horas, pagoPorHora);
            registrarPersona(profesor);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese valores numéricos válidos.");
        }
    }
    
    public void registrarEstudiante() {
        System.out.println("\n=== REGISTRAR ESTUDIANTE ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        Estudiante estudiante = new Estudiante(nombre, id);
        registrarPersona(estudiante);
    }
    
    public void crearCurso() {
        System.out.println("\n=== CREAR CURSO ===");
        if (getProfesores().isEmpty()) {
            System.out.println("Error: Debe registrar al menos un profesor antes de crear un curso.");
            return;
        }
        
        System.out.print("Nombre del curso: ");
        String nombreCurso = scanner.nextLine();
        
        System.out.println("Profesores disponibles:");
        List<Persona> profesores = getProfesores();
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).getNombre() + " (ID: " + profesores.get(i).getId() + ")");
        }
        
        System.out.print("Seleccione el número del profesor: ");
        try {
            int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
            if (seleccion >= 0 && seleccion < profesores.size()) {
                Curso curso = new Curso(nombreCurso, profesores.get(seleccion));
                registrarCurso(curso);
            } else {
                System.out.println("Selección inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
        }
    }
    
    public void agregarEstudianteACurso() {
        System.out.println("\n=== AGREGAR ESTUDIANTE A CURSO ===");
        if (cursos.isEmpty()) {
            System.out.println("Error: No hay cursos registrados.");
            return;
        }
        if (getEstudiantes().isEmpty()) {
            System.out.println("Error: No hay estudiantes registrados.");
            return;
        }
        
        // Mostrar cursos
        System.out.println("Cursos disponibles:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombreCurso());
        }
        
        System.out.print("Seleccione el número del curso: ");
        try {
            int cursoSeleccion = Integer.parseInt(scanner.nextLine()) - 1;
            if (cursoSeleccion >= 0 && cursoSeleccion < cursos.size()) {
                // Mostrar estudiantes
                System.out.println("Estudiantes disponibles:");
                List<Estudiante> estudiantes = getEstudiantes();
                for (int i = 0; i < estudiantes.size(); i++) {
                    System.out.println((i + 1) + ". " + estudiantes.get(i).getNombre() + " (ID: " + estudiantes.get(i).getId() + ")");
                }
                
                System.out.print("Seleccione el número del estudiante: ");
                int estudianteSeleccion = Integer.parseInt(scanner.nextLine()) - 1;
                if (estudianteSeleccion >= 0 && estudianteSeleccion < estudiantes.size()) {
                    cursos.get(cursoSeleccion).agregarEstudiante(estudiantes.get(estudianteSeleccion));
                } else {
                    System.out.println("Selección de estudiante inválida.");
                }
            } else {
                System.out.println("Selección de curso inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
        }
    }
    
    public void agregarCalificacionAEstudiante() {
        System.out.println("\n=== AGREGAR CALIFICACIÓN A ESTUDIANTE ===");
        List<Estudiante> estudiantes = getEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("Error: No hay estudiantes registrados.");
            return;
        }
        
        System.out.println("Estudiantes disponibles:");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i).getNombre() + " (ID: " + estudiantes.get(i).getId() + ")");
        }
        
        System.out.print("Seleccione el número del estudiante: ");
        try {
            int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
            if (seleccion >= 0 && seleccion < estudiantes.size()) {
                System.out.print("Ingrese la calificación (0-10): ");
                double calificacion = Double.parseDouble(scanner.nextLine());
                estudiantes.get(seleccion).agregarCalificacion(calificacion);
            } else {
                System.out.println("Selección inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese valores válidos.");
        }
    }
    
    public void buscarPersonaPorId() {
        System.out.println("\n=== BUSCAR PERSONA POR ID ===");
        System.out.print("Ingrese el ID a buscar: ");
        String id = scanner.nextLine();
        
        for (Persona persona : personas) {
            if (persona.getId().equalsIgnoreCase(id)) {
                System.out.println("Persona encontrada:");
                persona.mostrarInformacion();
                return;
            }
        }
        System.out.println("No se encontró ninguna persona con el ID: " + id);
    }
    
    public void buscarCursoPorNombre() {
        System.out.println("\n=== BUSCAR CURSO POR NOMBRE ===");
        System.out.print("Ingrese el nombre del curso a buscar: ");
        String nombre = scanner.nextLine();
        
        for (Curso curso : cursos) {
            if (curso.getNombreCurso().toLowerCase().contains(nombre.toLowerCase())) {
                curso.mostrarInformacionCurso();
                return;
            }
        }
        System.out.println("No se encontró ningún curso que contenga: " + nombre);
    }
    
    // Métodos auxiliares
    private List<Persona> getProfesores() {
        List<Persona> profesores = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof ProfesorTiempoCompleto || persona instanceof ProfesorPorHoras) {
                profesores.add(persona);
            }
        }
        return profesores;
    }
    
    private List<Estudiante> getEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Estudiante) {
                estudiantes.add((Estudiante) persona);
            }
        }
        return estudiantes;
    }
    
    public void ejecutarMenu() {
        int opcion;
        
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        registrarProfesorTiempoCompleto();
                        break;
                    case 2:
                        registrarProfesorPorHoras();
                        break;
                    case 3:
                        registrarEstudiante();
                        break;
                    case 4:
                        crearCurso();
                        break;
                    case 5:
                        agregarEstudianteACurso();
                        break;
                    case 6:
                        agregarCalificacionAEstudiante();
                        break;
                    case 7:
                        mostrarTodasLasPersonas();
                        break;
                    case 8:
                        mostrarTodosLosCursos();
                        break;
                    case 9:
                        procesarPagos();
                        break;
                    case 10:
                        procesarPromedios();
                        break;
                    case 11:
                        buscarPersonaPorId();
                        break;
                    case 12:
                        buscarCursoPorNombre();
                        break;
                    case 0:
                        System.out.println("¡Gracias por usar el Sistema de Gestión de Cursos!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
                
                if (opcion != 0) {
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                opcion = -1;
                System.out.println("Error: Ingrese un número válido.");
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 0);
    }
}

// Clase principal de Maven
public class Paradigmas {
    
    public static void main(String[] args) {
        SistemaGestionCursos sistema = new SistemaGestionCursos();
        
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    BIENVENIDO AL SISTEMA DE GESTIÓN DE       ║");
        System.out.println("║            CURSOS ACADÉMICOS                 ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        
        // Ejecutar el menú interactivo
        sistema.ejecutarMenu();
    }
}
