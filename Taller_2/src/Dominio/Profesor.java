/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.ListaParalelos;
import Logica.Persona;

/**
 *
 * @author crist
 */
public class Profesor extends Persona{
    private int salario;
    private ListaParalelos listaParalelos;

    public Profesor(String rut, String correo, String contraseña, int salario) {
        super(rut, correo, contraseña);
        this.salario = salario;
        listaParalelos = new ListaParalelos (4);
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public ListaParalelos getListaParalelos() {
        return listaParalelos;
    }

    public void setListaParalelos(ListaParalelos listaParalelos) {
        this.listaParalelos = listaParalelos;
    }

    
    
}
