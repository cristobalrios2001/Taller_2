/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.Asignatura;
import Logica.ListaParalelos;

/**
 *
 * @author crist
 */
public class AsignaturaObligatoria extends Asignatura{
    private int nivelEnMalla;
    private ListaParalelos listaParalelos;

    public AsignaturaObligatoria( String codigo, String nombre, int creditos, int nivelEnMalla) {
        super(codigo, nombre, creditos);
        this.nivelEnMalla = nivelEnMalla;
        listaParalelos = new ListaParalelos (1);
    }

    public int getNivelEnMalla() {
        return nivelEnMalla;
    }

    public void setNivelEnMalla(int nivelEnMalla) {
        this.nivelEnMalla = nivelEnMalla;
    }

    public ListaParalelos getListaParalelos() {
        return listaParalelos;
    }

    public void setListaParalelos(ListaParalelos listaParalelos) {
        this.listaParalelos = listaParalelos;
    }

    
    
}