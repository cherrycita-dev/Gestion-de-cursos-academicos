import abc
from abc import ABC, abstractmethod
import os
import platform

if platform.system() == "Windows":
    os.system('cls')
else:
    os.system('clear')


# --- Interfaces (definidas como Clases Abstractas Base) ---

class Pagable(ABC):
    """
    Interfaz para objetos que pueden recibir un pago.
    """
    @abstractmethod
    def calcular_pago(self):
        """Calcula el pago. Lanza PagoInvalidoException si el monto es inválido."""
        pass

class Calificable(ABC):
    """
    Interfaz para objetos que pueden ser calificados.
    """
    @abstractmethod
    def calcular_promedio(self):
        """Calcula el promedio de calificaciones. Lanza PromedioInvalidoException si no hay calificaciones."""
        pass

# --- Excepciones Personalizadas ---

class PagoInvalidoException(Exception):
    """Excepción para pagos inválidos (menores o iguales a cero)."""
    pass

class PromedioInvalidoException(Exception):
    """Excepción para cuando se intenta calcular un promedio sin calificaciones."""
    pass

# --- Clase Abstracta Persona ---

class Persona(ABC):
    """
    Clase abstracta que representa a una persona en el sistema.
    """
    def __init__(self, nombre: str, id_persona: str):
        self._nombre = nombre
        self._id = id_persona

    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
    def nombre(self, nombre: str):
        self._nombre = nombre

    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, id_persona: str):
        self._id = id_persona

    @abstractmethod
    def mostrar_informacion(self):
        """Muestra la información detallada de la persona."""
        pass

    def __str__(self):
        return f"ID: {self._id}, Nombre: {self._nombre}"

# --- Subclases de Persona ---

class ProfesorTiempoCompleto(Persona, Pagable):
    """Representa a un profesor con salario mensual fijo."""
    def __init__(self, nombre: str, id_persona: str, salario_mensual: float):
        super().__init__(nombre, id_persona)
        self.salario_mensual = salario_mensual

    def calcular_pago(self) -> float:
        if self.salario_mensual <= 0:
            raise PagoInvalidoException(f"El salario mensual debe ser mayor a 0. Salario actual: {self.salario_mensual}")
        return self.salario_mensual

    def mostrar_informacion(self):
        print(f"Profesor Tiempo Completo - {self}, Salario Mensual: ${self.salario_mensual:,.2f}")

class ProfesorPorHoras(Persona, Pagable):
    """Representa a un profesor que cobra por horas trabajadas."""
    def __init__(self, nombre: str, id_persona: str, horas_trabajadas: int, pago_por_hora: float):
        super().__init__(nombre, id_persona)
        self.horas_trabajadas = horas_trabajadas
        self.pago_por_hora = pago_por_hora

    def calcular_pago(self) -> float:
        pago_total = self.horas_trabajadas * self.pago_por_hora
        if pago_total <= 0:
            raise PagoInvalidoException(f"El pago total debe ser mayor a 0. Pago calculado: {pago_total}")
        return pago_total

    def mostrar_informacion(self):
        print(f"Profesor Por Horas - {self}, Horas: {self.horas_trabajadas}, Pago/Hora: ${self.pago_por_hora:,.2f}")

class Estudiante(Persona, Calificable):
    """Representa a un estudiante que puede tener calificaciones."""
    def __init__(self, nombre: str, id_persona: str):
        super().__init__(nombre, id_persona)
        self.calificaciones = []

    def agregar_calificacion(self, calificacion: float):
        if 0 <= calificacion <= 10:
            self.calificaciones.append(calificacion)
            print(f"Calificación {calificacion} agregada exitosamente.")
        else:
            print("Calificación inválida. Debe estar entre 0 y 10.")

    def calcular_promedio(self) -> float:
        if not self.calificaciones:
            raise PromedioInvalidoException(f"El estudiante {self.nombre} no tiene calificaciones registradas.")
        return sum(self.calificaciones) / len(self.calificaciones)

    def mostrar_informacion(self):
        print(f"Estudiante - {self}, Calificaciones: {self.calificaciones}")
        try:
            promedio = self.calcular_promedio()
            print(f"Promedio: {promedio:.2f}")
        except PromedioInvalidoException as e:
            print(f"Error: {e}")

# --- Clase Curso ---

class Curso:
    """Representa un curso académico con un profesor y estudiantes."""
    def __init__(self, nombre_curso: str, profesor_asignado: Persona):
        self.nombre_curso = nombre_curso
        self.profesor_asignado = profesor_asignado
        self.estudiantes = []

    def agregar_estudiante(self, estudiante: Estudiante):
        if estudiante not in self.estudiantes:
            self.estudiantes.append(estudiante)
            print(f"Estudiante {estudiante.nombre} agregado al curso {self.nombre_curso}")
        else:
            print("El estudiante ya está registrado en el curso.")

    def remover_estudiante(self, estudiante: Estudiante):
        try:
            self.estudiantes.remove(estudiante)
            print(f"Estudiante {estudiante.nombre} removido del curso {self.nombre_curso}")
        except ValueError:
            print("El estudiante no está registrado en el curso.")
            
    def mostrar_informacion_curso(self):
        print("\n=== INFORMACIÓN DEL CURSO ===")
        print(f"Curso: {self.nombre_curso}")
        print(f"Profesor: {self.profesor_asignado.nombre}")
        print(f"Número de estudiantes: {len(self.estudiantes)}")
        
        if self.estudiantes:
            print("Estudiantes matriculados:")
            for estudiante in self.estudiantes:
                print(f"  - {estudiante.nombre}")

# --- Clase Principal del Sistema ---

class SistemaGestionCursos:
    """
    Clase que gestiona toda la lógica del sistema, incluyendo el menú interactivo.
    """
    def __init__(self):
        self.personas = []
        self.cursos = []

    def registrar_persona(self, persona: Persona):
        self.personas.append(persona)

        print(f"Persona registrada: {persona.nombre}")

    def registrar_curso(self, curso: Curso):
        self.cursos.append(curso)
        print(f"Curso registrado: {curso.nombre_curso}")

    def mostrar_todas_las_personas(self):
        print("\n=== LISTADO DE PERSONAS (Polimorfismo) ===")
        if not self.personas:
            print("No hay personas registradas.")
            return
        for persona in self.personas:
            persona.mostrar_informacion() # Demostración de polimorfismo

    def mostrar_todos_los_cursos(self):
        print("\n=== LISTADO DE CURSOS ===")
        if not self.cursos:
            print("No hay cursos registrados.")
            return
        for curso in self.cursos:
            curso.mostrar_informacion_curso()

    def procesar_pagos(self):
        print("\n=== PROCESAMIENTO DE PAGOS ===")
        hay_profesores = False
        for persona in self.personas:
            if isinstance(persona, Pagable):
                hay_profesores = True
                try:
                    pago = persona.calcular_pago()
                    print(f"Pago para {persona.nombre}: ${pago:,.2f}")
                except PagoInvalidoException as e:
                    print(f"Error en pago: {e}")
        if not hay_profesores:
            print("No hay profesores registrados para procesar pagos.")

    def procesar_promedios(self):
        print("\n=== PROCESAMIENTO DE PROMEDIOS ===")
        hay_estudiantes = False
        for persona in self.personas:
            if isinstance(persona, Calificable):
                hay_estudiantes = True
                try:
                    promedio = persona.calcular_promedio()
                    print(f"Promedio de {persona.nombre}: {promedio:.2f}")
                except PromedioInvalidoException as e:
                    print(f"Error en promedio: {e}")
        if not hay_estudiantes:
            print("No hay estudiantes registrados para procesar promedios.")
            
    # --- Métodos del Menú Interactivo ---
    
    def registrar_profesor_tiempo_completo(self):
        print("\n=== REGISTRAR PROFESOR TIEMPO COMPLETO ===")
        try:
            nombre = input("Nombre: ")
            id_persona = input("ID: ")
            salario = float(input("Salario mensual: $"))
            profesor = ProfesorTiempoCompleto(nombre, id_persona, salario)
            self.registrar_persona(profesor)
        except ValueError:
            print("Error: Ingrese un salario válido.")

    def registrar_profesor_por_horas(self):
        print("\n=== REGISTRAR PROFESOR POR HORAS ===")
        try:
            nombre = input("Nombre: ")
            id_persona = input("ID: ")
            horas = int(input("Horas trabajadas: "))
            pago_hora = float(input("Pago por hora: $"))
            profesor = ProfesorPorHoras(nombre, id_persona, horas, pago_hora)
            self.registrar_persona(profesor)
        except ValueError:
            print("Error: Ingrese valores numéricos válidos.")

    def registrar_estudiante(self):
        print("\n=== REGISTRAR ESTUDIANTE ===")
        nombre = input("Nombre: ")
        id_persona = input("ID: ")
        estudiante = Estudiante(nombre, id_persona)
        self.registrar_persona(estudiante)
        
    def _get_profesores(self):
        return [p for p in self.personas if isinstance(p, (ProfesorTiempoCompleto, ProfesorPorHoras))]

    def _get_estudiantes(self):
        return [p for p in self.personas if isinstance(p, Estudiante)]

    def crear_curso(self):
        print("\n=== CREAR CURSO ===")
        profesores = self._get_profesores()
        if not profesores:
            print("Error: Debe registrar al menos un profesor antes de crear un curso.")
            return

        nombre_curso = input("Nombre del curso: ")
        print("Profesores disponibles:")
        for i, prof in enumerate(profesores):
            print(f"{i + 1}. {prof.nombre} (ID: {prof.id})")
        
        try:
            seleccion = int(input("Seleccione el número del profesor: ")) - 1
            if 0 <= seleccion < len(profesores):
                curso = Curso(nombre_curso, profesores[seleccion])
                self.registrar_curso(curso)
            else:
                print("Selección inválida.")
        except ValueError:
            print("Error: Ingrese un número válido.")
            
    def agregar_estudiante_a_curso(self):
        print("\n=== AGREGAR ESTUDIANTE A CURSO ===")
        if not self.cursos:
            print("Error: No hay cursos registrados.")
            return
        estudiantes = self._get_estudiantes()
        if not estudiantes:
            print("Error: No hay estudiantes registrados.")
            return

        # Mostrar cursos
        print("Cursos disponibles:")
        for i, curso in enumerate(self.cursos):
            print(f"{i + 1}. {curso.nombre_curso}")

        try:
            curso_sel = int(input("Seleccione el número del curso: ")) - 1
            if 0 <= curso_sel < len(self.cursos):
                # Mostrar estudiantes
                print("Estudiantes disponibles:")
                for i, est in enumerate(estudiantes):
                    print(f"{i + 1}. {est.nombre} (ID: {est.id})")
                
                est_sel = int(input("Seleccione el número del estudiante: ")) - 1
                if 0 <= est_sel < len(estudiantes):
                    self.cursos[curso_sel].agregar_estudiante(estudiantes[est_sel])
                else:
                    print("Selección de estudiante inválida.")
            else:
                print("Selección de curso inválida.")
        except ValueError:
            print("Error: Ingrese un número válido.")

    def agregar_calificacion_a_estudiante(self):
        print("\n=== AGREGAR CALIFICACIÓN A ESTUDIANTE ===")
        estudiantes = self._get_estudiantes()
        if not estudiantes:
            print("Error: No hay estudiantes registrados.")
            return
        
        print("Estudiantes disponibles:")
        for i, est in enumerate(estudiantes):
            print(f"{i + 1}. {est.nombre} (ID: {est.id})")

        try:
            seleccion = int(input("Seleccione el número del estudiante: ")) - 1
            if 0 <= seleccion < len(estudiantes):
                calificacion = float(input("Ingrese la calificación (0-10): "))
                estudiantes[seleccion].agregar_calificacion(calificacion)
            else:
                print("Selección inválida.")
        except ValueError:
            print("Error: Ingrese valores válidos.")

    def buscar_persona_por_id(self):
        print("\n=== BUSCAR PERSONA POR ID ===")
        id_buscado = input("Ingrese el ID a buscar: ")
        encontrado = False
        for persona in self.personas:
            if persona.id.lower() == id_buscado.lower():
                print("Persona encontrada:")
                persona.mostrar_informacion()
                encontrado = True
                break
        if not encontrado:
            print(f"No se encontró ninguna persona con el ID: {id_buscado}")

    def buscar_curso_por_nombre(self):
        print("\n=== BUSCAR CURSO POR NOMBRE ===")
        nombre_buscado = input("Ingrese el nombre del curso a buscar: ").lower()
        encontrado = False
        for curso in self.cursos:
            if nombre_buscado in curso.nombre_curso.lower():
                curso.mostrar_informacion_curso()
                encontrado = True
                break
        if not encontrado:
            print(f"No se encontró ningún curso que contenga: '{nombre_buscado}'")

    def ejecutar_menu(self):
        while True:
            print("\n╔══════════════════════════════════════════════╗")
            print("║      SISTEMA DE GESTIÓN DE CURSOS            ║")
            print("╠══════════════════════════════════════════════╣")
            print("║ 1. Registrar Profesor Tiempo Completo        ║")
            print("║ 2. Registrar Profesor Por Horas              ║")
            print("║ 3. Registrar Estudiante                      ║")
            print("║ 4. Crear Curso                               ║")
            print("║ 5. Agregar Estudiante a Curso                ║")
            print("║ 6. Agregar Calificación a Estudiante         ║")
            print("║ 7. Mostrar Todas las Personas                ║")
            print("║ 8. Mostrar Todos los Cursos                  ║")
            print("║ 9. Procesar Pagos                            ║")
            print("║ 10. Procesar Promedios                       ║")
            print("║ 11. Buscar Persona por ID                    ║")
            print("║ 12. Buscar Curso por Nombre                  ║")
            print("║ 0. Salir                                     ║")
            print("╚══════════════════════════════════════════════╝")
            
            opcion = input("Seleccione una opción: ")
            
            opciones = {
                "1": self.registrar_profesor_tiempo_completo,
                "2": self.registrar_profesor_por_horas,
                "3": self.registrar_estudiante,
                "4": self.crear_curso,
                "5": self.agregar_estudiante_a_curso,
                "6": self.agregar_calificacion_a_estudiante,
                "7": self.mostrar_todas_las_personas,
                "8": self.mostrar_todos_los_cursos,
                "9": self.procesar_pagos,
                "10": self.procesar_promedios,
                "11": self.buscar_persona_por_id,
                "12": self.buscar_curso_por_nombre
            }
            
            if opcion == "0":
                print("¡Gracias por usar el Sistema de Gestión de Cursos!")
                break
            
            if opcion in opciones:
                opciones[opcion]()
            else:
                print("Opción inválida. Por favor, seleccione una opción válida.")
            
            if opcion != "0":
                input("\nPresione Enter para continuar...")


if __name__ == "__main__":
    print("╔══════════════════════════════════════════════╗")
    print("║   BIENVENIDO AL SISTEMA DE GESTIÓN DE        ║")
    print("║         CURSOS ACADÉMICOS                    ║")
    print("╚══════════════════════════════════════════════╝")
    
    sistema = SistemaGestionCursos()
    sistema.ejecutar_menu()