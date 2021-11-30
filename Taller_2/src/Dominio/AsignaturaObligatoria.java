/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.Asignatura;
import Logica.ListaParalelos;


public class AsignaturaObligatoria extends Asignatura{
    private int nivelEnMalla;
    private ListaParalelos listaParalelos;
    private String [] asigPre;
    private int cantAsigPre;
    

    public AsignaturaObligatoria( String codigo, String nombre, int creditos, int nivelEnMalla, int cantAsigPre) {
        super(codigo, nombre, creditos);
        this.nivelEnMalla = nivelEnMalla;
        listaParalelos = new ListaParalelos (1);
        asigPre = new String[cantAsigPre];
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
    
    public boolean ingresarAsigPreReq(String asignaturaPreReq){
        if(cantAsigPre<asigPre.length){
            asigPre[cantAsigPre]=asignaturaPreReq;
            cantAsigPre++;
            return true;
        }
        return false;
    }
    
    public String getAsigPreI(int i){
        return asigPre[i];
    }
    
    public int getCantAsigPre(){
        return cantAsigPre;
    }
}
