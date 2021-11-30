/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.ListaAsignaturas;
import Logica.Persona;

/**
 *
 * @author crist
 */
public class Alumno extends Persona{
    private int nivelAlumno;
    private int TotalCreditos;
    private ListaAsignaturas listaAsignaturasCursadas;
    private ListaAsignaturas listaAsignaturasInscritas;
    

    public Alumno( String rut, String correo, int nivelAlumno, String contraseña) {
        super(rut, correo, contraseña);
        this.nivelAlumno = nivelAlumno;
        this.TotalCreditos = 0;
        listaAsignaturasInscritas = new ListaAsignaturas (12);
        listaAsignaturasCursadas = new ListaAsignaturas (30);
    }

    public int getNivelAlumno() {
        return nivelAlumno;
    }

    public void setNivelAlumno(int nivelAlumno) {
        this.nivelAlumno = nivelAlumno;
    }

    public ListaAsignaturas getListaAsignaturasCursadas() {
        return listaAsignaturasCursadas;
    }

    public void setListaAsignaturasCursadas(ListaAsignaturas listaAsignaturasCursadas) {
        this.listaAsignaturasCursadas = listaAsignaturasCursadas;
    }

    public ListaAsignaturas getListaAsignaturasInscritas() {
        return listaAsignaturasInscritas;
    }

    public void setListaAsignaturasInscritas(ListaAsignaturas listaAsignaturasInscritas) {
        this.listaAsignaturasInscritas = listaAsignaturasInscritas;
    }

    public int getTotalCreditos() {
        return TotalCreditos;
    }

    public void setTotalCreditos(int TotalCreditos) {
        this.TotalCreditos = TotalCreditos;
    }

    
    
    
}
