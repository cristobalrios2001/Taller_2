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
    
    /**
     * add a student in the system
     * pos:
     *      add a student in the system
     * @param rut rut of the student
     * @param correo student's mail
     * @param nivelAlumno student's semester level
     * @param contraseña student password account
     * @return return a boolean true in case of the operation results successful
     */
    public boolean ingresarAlumno(String rut, String correo,int nivelAlumno ,String contraseña);
    
    /**
     * add a teacher in the system
     * pos:
     *      new teacher in the system
     * @param rut rut of the teacher
     * @param correo teacher's mail
     * @param contraseña teacher password account
     * @param salario int representing teacher's salary
     * @return return a boolean true in case of the operation results succesful
     */
    
    public boolean ingresarProfesor (String rut, String correo,  String contraseña, int salario);
    
    /**
     * add a mandatory university course in the system
     * pos:
     *      add a mandatory university course in the system
     * @param codigo course code
     * @param nombre name of the course
     * @param creditos credits requiered for the course
     * @param nivelMalla level of the semester course
     * @param cantAsigPre number of pre-requisite courses
     * @return return a boolean true in case of the operation results succesful
     */
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int creditos, int nivelMalla, int cantAsigPre);
    
    /**
     * associates a code with a requiered course
     * pre:
     *      that the course exits
     * @param codigo code of course
     * @param codigoAsigPre code of pre-course
     * @return return a boolean ture in case of operation results succesful
     */
    public boolean asociarCodigoAsignaturaObligatoria(String codigo,String codigoAsigPre);

    /**
     * add a optional university course in the system
     * pos:
     *      add a course in the system
     * @param codigo course code
     * @param nombre name of course
     * @param creditos credits requiered for the course
     * @param cantCreditosPreRequisito int credits prerequisites
     * @return returna boolean true in case of the operation results succesful
     */
    public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int creditos, int cantCreditosPreRequisito);

    /**
     * add a section of course in the system
     * pre:
     *      that the course exits
     * @param numeroParalelo num of the section
     * @param codigoAsignatura code of the course
     * @param rutProfesor teacher's rut
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean ingresarParalelo(int numeroParalelo, String codigoAsignatura, String rutProfesor);
    
    /**
     * associates a student with a course taken
     * pre:
     *      that the student exists
     *      that te course exists
     * @param rutAlumno rut of the student
     * @param codigoAsignatura code of the course
     * @param notaFinal final rating
     * @return return a boolean true in case of operation results succesful
     */
    public boolean ingresarAsociarAlumnoAsignaturaCursada(String rutAlumno, String codigoAsignatura, Double notaFinal);
    
    /**
     * assciates a studen with a course registered
     * pre:
     *      that the student exists
     *      that te course exists
     * @param rutAlumno student's rut
     * @param codigoAsignatura code of course
     * @param numeroParalelo num of the section
     * @return return a boolean true in case of operation results succesful
     */
    public boolean ingresarAsociarAlumnoAsignaturaInscrita(String rutAlumno, String codigoAsignatura, int numeroParalelo);
    
    /**
     * obtains a string with the available optional courses
     * pre:
     *      that the student exits
     * @param rut rut of the student
     * @return return a string with the text of the optional courses available
     */
    public String obtenerAsignaturasDisponiblesOpcionales(String rut);
    
    /**
     * obtains a string with the available compulsory courses
     * pre:
     *      that the student exits
     * @param rut rut of the student
     * @return return a string with the text of the compulsory courses available
     * 
     */
    public String obtenerAsignaturasDisponiblesObligatorias(String rut);
    
    /**
     * section available for each subject
     * pre:
     *      that the exits course
     * @param codigoAsignaturas code of the course
     * @return return a string with the text of te sections available
     */
    public String obtenerParalelosDisponibles(String codigoAsignaturas);
    
    /**
     * register a course
     * pre:
     *      that the student exists
     *      that the course exists
     *      that the section exists
     * pos:
     *      enroll a student in a course
     * @param rut rut of the student
     * @param codigoAsignatura code of the course
     * @param numeroParalelo number of the section
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean inscribirAsignatura(String rut, String codigoAsignatura, int numeroParalelo);
    
    /**
     * obtain enrolled subject
     * pre:
     *      that the exits student
     * @param rut rut of the student
     * @return return a string with text of the subjects
     */
    public String obtenerAsignaturaInscrita(String rut);
    
    /**
     * delete an asginture entered by a student
     * pre:
     *      that the exists student
     *      who have enrolled in courses
     * pos:
     *      deletes a student's associated course
     * @param rut rut of the student
     * @param código code of the course
     * @return return a boolean true in case of opertaion results successful
     */
    public boolean eliminarAsignatura(String rut, String código);
    
    /**
     * get the sections you dictate a teacher
     * @param rut rut of the teacher
     * @return return a string in text form with the information on their sections
     */
    public String obtenerParalelosProfesor(String rut);
    
    /**
     * gets students from a teacher's parallel
     * pre:
     *      that te teacher exists
     * @param rut rut of the teacher
     * @param numeroParalelo number of the section
     * @return return a string in text form with the students from section
     */
    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo);
    
    /**
     * enters a final grade for a student in a course
     * pre:
     *      that the student exists
     * pos:
     *      enters a final grade of course for a student
     * @param codigoAsignatura code of course
     * @param rut rut of the student
     * @param notaFinal grade of the student's course
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, Double notaFinal);
    
    /**
     * removes a student from the system
     * pre:
     *      that the student exists
     * pos:
     *      delete a student of the system
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean eliminarAlumno ();
    
    /**
     * overwrite the text file
     * @return returns a string with the overwrite
     */
    public String sobreescribir();
    
    /**
     * the system searches for mail
     * @param correo mail of the person
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean buscarCorreo(String correo);
    
    /**
     * verifies if the password entered is correct
     * pre:
     *      that the email exists
     * @param correo e-mail of the person
     * @param contraseña password of the person
     * @return return a boolean true in case of opertaion results succesful
     */
    public boolean contraseñaCorrecta(String correo, String contraseña);
    
    /**
     * obtains the person's routine through the mail
     * pre:
     *      that the e-mail exists
     * @param correo e-mail of the person
     * @return return a string returns a string in text form with the searched rut
     */
    public String obtenerRut(String correo);
    
    /**
     * search whether you are a teacher or a student
     * @param correo e-mail of the person
     * @return return a number 1 symbolizes the student and 2 symbolizes the teacher.
     */
    public int tipoPersona(String correo);
}
