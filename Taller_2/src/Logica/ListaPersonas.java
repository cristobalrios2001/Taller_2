
package Logica;


public class ListaPersonas {
    private int maxPersonas;
    private int cantPersonas;
    private Persona [] listaPersonas;

    public ListaPersonas(int maxPersonas){
        this.maxPersonas=maxPersonas;
        cantPersonas=0;
        listaPersonas = new Persona[maxPersonas];
    }
    public int getCantPersonas(){
        return cantPersonas;
    }

    public boolean ingresar(Persona persona){
        if(cantPersonas<maxPersonas){
            listaPersonas[cantPersonas]=persona;
            cantPersonas++;
            return true;
        }
        return false;
    }
    public boolean eliminar(String rutPersona){
        int i;
        for(i=0;i<cantPersonas;i++){
            if(listaPersonas[i].getRutPersona().equals(rutPersona)){
                    break;
            }
        }
        if(i==cantPersonas){
            return false;
        }
        else{
            for(int k=i;k<cantPersonas;k++){
                listaPersonas[k]=listaPersonas[k+1];
            }
            cantPersonas--;
            return true;
        }
    }
    public Persona buscar(String rutPersona){
        for(int i=0;i<cantPersonas;i++){
            if(listaPersonas[i].getRutPersona().equals(rutPersona)){
                return listaPersonas[i];
            }
        }
        return null;
    }
    public Persona getPersonaI(int posicion){
        return listaPersonas[posicion];
    }
}
