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
public class Asignatura {
    private String codigoAsignatura;
    private String nombre;
    private int creditos;
    private int nota;

    public Asignatura(String codigoAsignatura, String nombre, int creditos) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.creditos = creditos;
        this.nota = nota;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
    
}
