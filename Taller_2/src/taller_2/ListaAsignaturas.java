/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_2;

/**
 *
 * @author crist
 */
public class ListaAsignaturas {
    private int maxAsignaturas;
    private int cantAsignaturas;
    private Asignatura [] listaAsignaturas;

    public ListaAsignaturas(int maxAsignaturas){
        this.maxAsignaturas=maxAsignaturas;
        cantAsignaturas=0;
        listaAsignaturas = new Asignatura[maxAsignaturas];
}
    public int getCantAsignaturas(){
        return cantAsignaturas;
    }

    public boolean ingresar(Asignatura asignatura){
        if(cantAsignaturas<maxAsignaturas){
            listaAsignaturas[cantAsignaturas]=asignatura;
            cantAsignaturas++;
            return true;
        }
        return false;
    }
    public boolean eliminar(String codigoAsignatura){
        int i;
        for(i=0;i<cantAsignaturas;i++){
            if(listaAsignaturas[i].getCodigoAsignatura().equals(codigoAsignatura)){
                break;
            }
        }
        if(i==cantAsignaturas){
            return false;
        }
        else{
            for(int k=i;k<cantAsignaturas;k++){
                listaAsignaturas[k]=listaAsignaturas[k+1];
            }
            cantAsignaturas--;
            return true;
        }
    }
    public Asignatura buscar(String codigoAsignatura){
        for(int i=0;i<cantAsignaturas;i++){
            if(listaAsignaturas[i].getCodigoAsignatura().equals(codigoAsignatura)){
                return listaAsignaturas[i];
            }
        }
        return null;
    }
    public Asignatura getElementoI(int posicion){
        return listaAsignaturas[posicion];
    }
}
