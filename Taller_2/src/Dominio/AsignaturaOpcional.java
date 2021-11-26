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
public class AsignaturaOpcional extends Asignatura {
    private int cantCreditosPreRequisito;
    private ListaParalelos listaParalelos;

    public AsignaturaOpcional(String codigo, String nombre, String creditos, int cantCreditosPreRequisito) {
        super(codigo, nombre, creditos);
        this.cantCreditosPreRequisito = cantCreditosPreRequisito;
    }

    public int getCantCreditosPreRequisito() {
        return cantCreditosPreRequisito;
    }

    public void setCantCreditosPreRequisito(int cantCreditosPreRequisito) {
        this.cantCreditosPreRequisito = cantCreditosPreRequisito;
    }

    public ListaParalelos getListaParalelos() {
        return listaParalelos;
    }

    public void setListaParalelos(ListaParalelos listaParalelos) {
        this.listaParalelos = listaParalelos;
    }
    
    
}
