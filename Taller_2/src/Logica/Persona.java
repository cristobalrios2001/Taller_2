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
public class Persona {
    private String rut;
    private String correo;
    private String contraseña;

    public Persona(String rut, String correo, String contraseña) {
        this.rut = rut;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getRutPersona() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
