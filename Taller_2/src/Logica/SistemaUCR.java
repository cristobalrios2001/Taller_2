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
    public boolean ingresarAlumno(String rut, String correo,int nivelAlumno ,String contraseña);
    
    public boolean ingresarProfesor (String rut, String correo,  String contraseña, int salario);
    
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int creditos, int nivelMalla, int cantAsigPre);
    
    public boolean asociarCodigoAsignaturaObligatoria(String codigo,String codigoAsigPre);

    public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int creditos, int cantCreditosPreRequisito);

    public boolean ingresarParalelo(int numeroParalelo);
    
    public boolean ingresarAsociarAlumnoAsignaturaCursada(String rutAlumno, String codigoAsignatura, Double notaFinal);
    
    public boolean ingresarAsociarAlumnoAsignaturaInscrita(String rutAlumno, String codigoAsignatura, int numeroParalelo);

    public boolean ingresarAsociarParaleoAsignaturaProfesor(int numeroParalelo, String codigoAsignatura, String rut);

    public String obtenerAsignaturasDisponiblesOpcionales(String rut);
    
    public String obtenerAsignaturasDisponiblesObligatorias(String rut);

    public String obtenerParalelosDisponibles(String codigoAsignaturas);

    

    public boolean inscribirAsignatura(String rut, String codigoAsignatura, int numeroParalelo);

    public String obtenerAsignaturaInscrita(String rut);

    public boolean eliminarAsignatura(String rut, String código);

    public String obtenerParalelosProfesor(String rut);

    

    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo);
    
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, Double notaFinal);

    public boolean  comprobarNotaFinalAsignaturas (String codigoAsignatura, String rut);
    
    public boolean eliminarAlumno ();
    
    
}
