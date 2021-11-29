/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;


import Logica.*;

/**
 *
 * @author crist
 */
public class Paralelo {
    private int numeroParalelo;
    private Profesor profesor;
    private Asignatura asignatura;
    private int cupoParalelo; // cambio espacio en paralelo
    private ListaPersonas listaPersonas; //lista de estudiantes maximo 100

    public Paralelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.cupoParalelo =100; // cambio espacio en paralelo
        listaPersonas = new ListaPersonas(100); //lista de estudiantes maximo 100
        
    }

    public int getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public int getCupoParalelo() {  // cambio espacio en paralelo
        return cupoParalelo;
    }

    public void setCupoParalelo(int cupoParalelo) {  // cambio espacio en paralelo
        this.cupoParalelo = cupoParalelo;
    }

    public ListaPersonas getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ListaPersonas listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    
    
}
