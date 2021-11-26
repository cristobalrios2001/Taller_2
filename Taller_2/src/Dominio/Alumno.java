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
    private ListaAsignaturas listaAsignaturas;

    public Alumno( String rut, String correo, String contraseña, int nivelAlumno) {
        super(rut, correo, contraseña);
        this.nivelAlumno = nivelAlumno;
    }

    public int getNivelAlumno() {
        return nivelAlumno;
    }

    public void setNivelAlumno(int nivelAlumno) {
        this.nivelAlumno = nivelAlumno;
    }

    public ListaAsignaturas getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(ListaAsignaturas listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }
    
    
}
