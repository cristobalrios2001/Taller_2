/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author crist
 */
public interface SistemaUCR {
    public boolean ingresarAlumno(String rut, String correo, String contraseña);
    
    public boolean ingresarProfesor (String rut, String correo,  String contraseña, int salario);
    
    public boolean ingresarAsignaturaOpcional(String código, String nombre, int creditos, int cantCreditosPreRequisito);

    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre,  int creditos, int nivelMalla);

    public boolean ingresarParalelo(int numeroParalelo);
    
    public boolean ingresarAsociarAlumnoAsignatura(String rutAlumno, String codigoAsignatura, int notaFinal);

    public boolean ingresarAsociarParaleoAsignaturaProfesor(int numeroParalelo, String codigoAsignatura, String rut);

    public String obtenerAsignaturasDisponibles(String rut);

    public String obtenerParalelosDisponibles(String codigoAsignaturas);

    public boolean insertarAsignaturaInscrita (String rut, String codigoAsignatura, int numeroParalelo);

    public boolean inscribirAsignatura(String rut, String codigoAsignatura, int numeroParalelo);

    public String obtenerAsignaturaInscrita(String rut);

    public boolean eliminarAsignatura(String rut, String código);

    public String obtenerParalelosProfesor(String rut);

    public String obtenerParalelosProfesor(String rut, int numeroParalelo);

    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo);
    
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, int notaFinal);

    public boolean  comprobarNotaFinalAsignaturas (String codigoAsignatura, String rut);
    
    public boolean eliminarAlumno ();
}
