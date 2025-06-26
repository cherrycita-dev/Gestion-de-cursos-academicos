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
      · nombre
      · ID
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


Link planner https://planner.cloud.microsoft/webui/v1/plan/49dO-hg3SUyIBkPguUcFSGQABzTO?tid=f94bf4d9-8097-4794-adf6-a5466ca28563
