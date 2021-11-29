
package Logica;

import Dominio.*;
import java.util.HashSet;
import java.util.Set;


public class SistemaUCNImpl implements SistemaUCR {

    private ListaPersonas listaPersonas;
    private ListaAsignaturas listaAsignaturas;
    private ListaParalelos listaParalelos;
    
    public SistemaUCNImpl(){
        listaPersonas = new ListaPersonas(1000);
        listaAsignaturas = new ListaAsignaturas(650);
        listaParalelos = new ListaParalelos(100);
    }
    
    

    @Override
    public boolean ingresarAlumno(String rut, String correo, String contraseña) {
        Persona alumno = new Alumno (rut, correo, contraseña,0);
        
        return listaPersonas.ingresar(alumno);
    }

    @Override
    public boolean ingresarProfesor(String rut, String correo, String contraseña, int salario) {
        Persona profesor = new Profesor(rut, correo, contraseña, salario);
        
        return listaPersonas.ingresar(profesor);
    }

    @Override
    public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int creditos, int cantCreditosPreRequisito) {
        Asignatura asignaturaOpc = new AsignaturaOpcional(codigo, nombre, creditos, cantCreditosPreRequisito);
        
        return listaAsignaturas.ingresar(asignaturaOpc);
    }

    @Override
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int creditos, int nivelMalla) {
        Asignatura asignaturaObligatoria = new AsignaturaObligatoria(codigo, nombre, creditos, nivelMalla);
        
        return listaAsignaturas.ingresar(asignaturaObligatoria);
    }

    @Override
    public boolean ingresarParalelo(int numeroParalelo) {
        Paralelo paralelo = new Paralelo (numeroParalelo);
        
        return listaParalelos.ingresar(paralelo);
    }

    @Override
    public boolean ingresarAsociarAlumnoAsignatura(String rutAlumno, String codigoAsignatura, int notaFinal) {
        Persona p = listaPersonas.buscar(rutAlumno);
        
        
        
        if(p != null){
            Asignatura asig = listaAsignaturas.buscar(codigoAsignatura);
            
            if(asig != null){
                
                if(p instanceof Alumno){
                    int nivelAsigMin = 11;
                    
                    if(asig instanceof AsignaturaObligatoria){
                        if(nivelAsigMin > ((AsignaturaObligatoria) asig).getNivelEnMalla()){
                            nivelAsigMin = ((AsignaturaObligatoria) asig).getNivelEnMalla();
                        }
                    }
                    
                   
                    asig.setNota(notaFinal);
                    ((Alumno) p).setNivelAlumno(nivelAsigMin);
                    return ((Alumno) p).getListaAsignaturasCursadas().ingresar(asig);
                    
                }
                
            }else{
                throw new NullPointerException("La asignatura no existe");
            }
            
            
        }else{
            throw new NullPointerException("La persona no existe");
        }
        
        return false;
    }
    
    

    @Override
    public boolean ingresarAsociarParaleoAsignaturaProfesor(int numeroParalelo, String codigoAsignatura, String rut) {
        Persona p = listaPersonas.buscar(rut);
        
        if(p != null){
            Asignatura a = listaAsignaturas.buscar(codigoAsignatura);
            if (a != null){
                Paralelo pl = listaParalelos.buscar(numeroParalelo);
                
                pl.setProfesor((Profesor)p);
                pl.setAsignatura(a);
                return true;
                
            }else{
                throw new NullPointerException("La Asignatura no ha sido encontrada !");
            }
            
        }else{
            throw new NullPointerException("La persona no ha sido encontrada !");
        }
    }

    @Override
    public String obtenerAsignaturasDisponibles(String rut) {
        Persona p = listaPersonas.buscar(rut);
        String salida = "";
        salida += "Asignaturas disponibles: ";
        if(p!= null){
            Alumno a = (Alumno)p;
            ListaAsignaturas laACursadas = a.getListaAsignaturasCursadas();
            
            for (int i = 0; i < listaAsignaturas.getCantAsignaturas(); i++) {
                Asignatura asg = laACursadas.getAsignaturaI(i);
                
                if(!asg.getCodigoAsignatura().equals(listaAsignaturas.getAsignaturaI(i).getCodigoAsignatura())){
                    
                    if(asg instanceof AsignaturaObligatoria){
                        salida +="\nAsignaturas obligatorias";
                        if(((AsignaturaObligatoria) asg).getNivelEnMalla()== a.getNivelAlumno()){
                            salida += "\n\tNombre asignatura: "+asg.getNombre()+ " ,Codigo" + asg.getCodigoAsignatura();
                        }
                    }else if(asg instanceof AsignaturaOpcional){
                        salida +="\nAsignaturas opcionales";
                        if(((AsignaturaObligatoria) asg).getNivelEnMalla()> a.getNivelAlumno()){
                            salida += "\n\tNombre asignatura: "+asg.getNombre()+ " ,Codigo" + asg.getCodigoAsignatura();
                        }
                    }
                    
                    
                }
            }
            
            
        }else{
            throw new NullPointerException("La persona no existe");
        }
        return salida;
        
    }

    @Override
    public String obtenerParalelosDisponibles(String codigoAsignaturas) {
        Asignatura asig = listaAsignaturas.buscar(codigoAsignaturas);
        String salida = "";
        if(asig != null){
            salida +="Los paralelos disponibles son: ";
            for (int i = 0; i < listaParalelos.getCantParalelos(); i++) {
                Paralelo p = listaParalelos.getPersonaI(i);
                if(p.getCupoParalelo()!=0){
                    if(p.getAsignatura().getCodigoAsignatura().equals(asig.getCodigoAsignatura())){
                        salida +="\n\t"+p.getNumeroParalelo();
                    }
                }
                
            }
            
        }else{
            throw new NullPointerException("La ingresada asignatura NO EXISTE !");
        }
        return salida;
    }

    @Override
    public boolean insertarAsignaturaInscrita(String rut, String codigoAsignatura, int numeroParalelo) {
        Persona p = listaPersonas.buscar(rut);
        
        // VERIFICAR SI LA ASIGNATURA ES OBLIGATORIA O OPCIONAL Y VER LOS CREDITOS
        
        if(p!= null){
            if(p instanceof Alumno){
                Asignatura asignatura = listaAsignaturas.buscar(codigoAsignatura);
                ((Alumno) p).getListaAsignaturas().ingresar(asignatura);
            }
        }
    }

    @Override
    public boolean ingresarAsociarAlumnoAsignaturaInscrita(String rutAlumno, String codigoAsignatura, int numeroParalelo) {
        
    }
    
    
    @Override
    public boolean inscribirAsignatura(String rut, String codigoAsignatura, int numeroParalelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAsignaturaInscrita(String rut) {
        String salida = "";
        
        Persona p = listaPersonas.buscar(rut);
        
        if(p != null){
            if(p instanceof Alumno){
                Alumno a = (Alumno)p;
                ListaAsignaturas laI = a.getListaAsignaturasInscritas();
                if(laI.getCantAsignaturas() != 0){
                    salida += "Asignaturas inscritas por "+a.getRutPersona()+":";
                    for (int i = 0; i < laI.getCantAsignaturas(); i++) {
                        Asignatura asig = laI.getAsignaturaI(i);
                        salida +="\n\t Nombre Asignatura: "+ asig.getNombre() + " (Codigo: "+asig.getCodigoAsignatura()+")";
                    }
                }else{
                    throw new NullPointerException("El alumno "+a.getRutPersona()+ " no tiene asignaturas inscritas");
                }
                
            }
        }else{
            throw new NullPointerException ("La persona no existe");
        }
        return salida;
    }

    @Override
    public boolean eliminarAsignatura(String rut, String código) {
        Persona p = listaPersonas.buscar(rut);
        
        if(p != null){
            if(p instanceof Alumno){               
                Alumno alumno = (Alumno)p;
                ListaAsignaturas laInsAlum = alumno.getListaAsignaturasInscritas();
                Asignatura asig = laInsAlum.buscar(código);
                
                if(asig != null){
                    return laInsAlum.eliminar(código);
                }else{
                    throw new NullPointerException("La Asignatura no esta inscrita por "+alumno.getRutPersona());
                }
            }
            
            
        }else{
            throw new NullPointerException("El alumno no existe");
        }
        return false;
    }

    @Override
    public String obtenerParalelosProfesor(String rut) {
        String salida = "";
        Persona persona = listaPersonas.buscar(rut);
        
        if(persona != null){
            if(persona instanceof Profesor){
                Profesor profesor =(Profesor) persona;
                
                ListaParalelos lParalelosProf = profesor.getListaParalelos();
                
                if(lParalelosProf.getCantParalelos() !=0){
                    salida += "\nParalelos profesor "+ profesor.getRutPersona()+ ": ";
                    for (int i = 0; i < lParalelosProf.getCantParalelos(); i++) {
                        Paralelo paralelo = lParalelosProf.getParaleloI(i);
                        salida += "\n\tNumero paralelo: "+ paralelo.getNumeroParalelo()+" (Asignatura: "+paralelo.getAsignatura().getNombre()+")";
                    }
                }else{
                    throw new NullPointerException("El profesor "+profesor.getRutPersona()+", no tiene paralelos");
                }
            }
        }else{
            throw new NullPointerException("La persona no existe !");
        }
        return salida;
    }

    
    // REVISAR DEL WORD PQ ESTA REPETIDO
    @Override
    public String obtenerParalelosProfesor(String rut, int numeroParalelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo) {
        Persona p = listaPersonas.buscar(rut);
        
        if(p!= null){
            if(p instanceof Profesor){
                Profesor profesor = (Profesor)p;
                ListaParalelos lpProfe = profesor.getListaParalelos();
                
                Paralelo paraleloP = lpProfe.buscar(numeroParalelo);
                
                if(paraleloP != null){
                    
                }else{
                    throw new NullPointerException("El profesor "+profesor.getRutPersona()+" ,no tiene paralelos");
                }
                
                
            }
            
        }else{
            throw new NullPointerException("La persona no existe !");
        }
    }

    @Override
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, int notaFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprobarNotaFinalAsignaturas(String codigoAsignatura, String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarAlumno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
