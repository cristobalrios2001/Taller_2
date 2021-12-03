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
    private String profesor;
    private String asignatura;
    private int cupoParalelo; // cambio espacio en paralelo
    private ListaPersonas listaPersonas; //lista de estudiantes maximo 100

    public Paralelo(int numeroParalelo, String asignatura, String profesor) {
        this.numeroParalelo = numeroParalelo;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.cupoParalelo =0; // cambio espacio en paralelo
        listaPersonas = new ListaPersonas(100); //lista de estudiantes maximo 100
            
    }

    public int getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(int numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
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
