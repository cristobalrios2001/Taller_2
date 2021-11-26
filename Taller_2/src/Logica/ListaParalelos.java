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
public class ListaParalelos {
    private int maxParalelos;
    private int cantParalelos;
    private Paralelo [] listaParalelos;

    public ListaParalelos(int maxParalelos){
        this.maxParalelos=maxParalelos;
        cantParalelos=0;
        listaParalelos = new Paralelo[maxParalelos];
    }
    public int getCantParalelos(){
        return cantParalelos;
    }

    public boolean ingresar(Paralelo paralelo){
        if(cantParalelos<maxParalelos){
            listaParalelos[cantParalelos]=paralelo;
            cantParalelos++;
            return true;
        }
        return false;
    }
    public boolean eliminar(int numeroParalelo){
        int i;
        for(i=0;i<cantParalelos;i++){
            if(listaParalelos[i].getNumeroParalelo() == numeroParalelo){
                break;
            }
        }
        if(i==cantParalelos){
            return false;
        }
        else{
            for(int k=i;k<cantParalelos;k++){
                listaParalelos[k]=listaParalelos[k+1];
            }
            cantParalelos--;
            return true;
        }
    }
    public Paralelo buscar(int numeroParalelo){
        for(int i=0;i<cantParalelos;i++){
            if(listaParalelos[i].getNumeroParalelo() == numeroParalelo){
                return listaParalelos[i];
            }
        }
        return null;
    }
    public Paralelo getPersonaI(int posicion){
        return listaParalelos[posicion];
    }
    
    
}
