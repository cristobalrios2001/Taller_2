/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Paralelo;

/**
 *
 * @author crist
 */
public class Asignatura {
    private String codigoAsignatura;
    private String nombre;
    private int creditos;
    private Double nota;
    private int paralelo;

    public Asignatura(String codigoAsignatura, String nombre, int creditos) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.creditos = creditos;
        nota = null;
        paralelo = 0;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigo(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public int getParalelo() {
        return paralelo;
    }

    public void setParalelo(int paralelo) {
        this.paralelo = paralelo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
    
   
}
