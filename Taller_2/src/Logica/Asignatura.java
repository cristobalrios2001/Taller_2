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
    private String creditos;

    public Asignatura(String codigoAsignatura, String nombre, String creditos) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.creditos = creditos;
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

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }
    
    
    
}
