# <p align= center> GESTION DE CURSOS ACADEMICO </p>

## Objetivo General
El estudiante desarrollará un sistema completo utilizando Progrmación Orienta a Objetos que integre:
  - Clases e instancias.
  - Encapsulamiento.
  - Herencia.
  - Polimorfismo.
  - Abstracción.
  - Interfaces.
  - Exepciones personalizadas.
  - Traducción de código de Java a Python.

## Escenario del problema
La institución desea implementar un sistema de gestión de curso. El sistema debe registrar a profesores como a estudiantes, así como administrar cursos en los que pueden participar. 
El sistema debe:
  - Permitir registrar cursos y personas.
  - Calcular el salario de profesores de tiempo completo y por horas.
  - Calcular el promedio de calificaciones de los estudiantes.
  - Lanzar exepciones personalizadas si ocurren errores de lógica.
  - Implementar primero en Java y luego traducirse a Python.

## Requirimientos funcionales

### 1. Definición de clases

Interfaces:
  - Pagable -> Método calcularPago() (para profesores).
  - Calificable -> Método calcularPromedio() (para estudiantes).
Clase abstracta:
  - Persona -> Atributos:
      - nombre
      - ID
Subclases de Persona:
  - ProfesorTiempoCompleto (atribuuto adicional: salarioMensual).
  - ProfesorPorHoras (atributos adicionales: horasTrabajadas, pagoPorHora).
  - Estudiantes (atributo adicional: lista de calificaciones List<Double>).
Clase:
  - Curso (atributos: nombreCurso, lista de estudiantes, profesor asignado).

### 2. Polimorfismo:

  - Procesar listas de personas (Persona) y cursos (Curso), mostrando información utilizando polimorfismo.

### 3. Exepciones personalizadas:

  - PagoInvalidoExeption -> Si el pago de un profesor es <= 0.
  - PromedioInvalidoExeption -> Si un estudiante no tiene calificaiones registradas.

## Actividades del estudiante

### Fase 1 (Desarrollo en Java):
  - Diseñar y codificar el sistema completo en Java.
  - Validar funcionamiento de:
      - Interfaces.
      - Herencia.
      - Polimorfismo.
      - Exepciones personalizadas.

### Fase 2 (Traducción a Python):

  - Traducir el código funcional de Java a Python.
  - Adaptar:
      - Interfaces (usando abc en Python).
      - Exepciones.
      - Polimorfismo.

### Gestión del desarrollo

  - Uso de repositorio.
  - Uso de tablero para asignación de actividades.

## Pasos para Ejecutar el Programa

1. **Clonar el Repositorio**:
   Si no tienes el proyecto localmente, clónalo con el siguiente comando:
   
   ```bash
   [git clone ]
   
2. Abrir el Proyecto: Abre el archivo .py en tu IDE o editor preferido.

3. Compilar el Programa.

4. Ejecutar el Programa.


## Tecnologías Utilizadas
- Lenguaje: [Python, Java]  
- GitHub para el control de versiones.
- Planner.
Link planner https://planner.cloud.microsoft/webui/v1/plan/49dO-hg3SUyIBkPguUcFSGQABzTO?tid=f94bf4d9-8097-4794-adf6-a5466ca28563

## Autores
Desarrollado por 

Carranza Mercado Jesus Eduardo  
Gonzalez Pérez Monserrat  
Pérez Méndez Nancy Esmeralda  

## Licencia
Este proyecto está bajo la Licencia MIT. Puedes usar, modificar y distribuir este código de acuerdo con los términos de la licencia.
