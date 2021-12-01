
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
    public boolean ingresarAlumno(String rut, String correo,int nivelAlumno, String contraseña) {
        Persona alumno = new Alumno (rut, correo,nivelAlumno, contraseña);
        
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
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int creditos, int nivelMalla, int cantAsigPre) {
        Asignatura asignaturaObligatoria = new AsignaturaObligatoria(codigo, nombre, creditos, nivelMalla, cantAsigPre);
        
        
        return listaAsignaturas.ingresar(asignaturaObligatoria);
    }
    
    
    
    @Override
    public boolean asociarCodigoAsignaturaObligatoria(String codigo,String codigoAsigPre){
        Asignatura asignatura = listaAsignaturas.buscar(codigo);
        boolean ingreso = false;
        
        if(asignatura instanceof AsignaturaObligatoria){
            AsignaturaObligatoria asigOb = (AsignaturaObligatoria) asignatura;
            
            ingreso =  asigOb.ingresarAsigPreReq(codigoAsigPre);
        }
        return ingreso;
    }

    @Override
    public boolean ingresarParalelo(int numeroParalelo) {
        Paralelo paralelo = new Paralelo (numeroParalelo);
        
        return listaParalelos.ingresar(paralelo);
    }
    
    @Override 
    public boolean ingresarAsociarAlumnoAsignaturaCursada(String rutAlumno, String codigoAsignatura, Double notaFinal) {
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
    public boolean ingresarAsociarAlumnoAsignaturaInscrita(String rutAlumno, String codigoAsignatura, int numeroParalelo) {
        Persona p = listaPersonas.buscar(rutAlumno);
        
        if(p != null){
            Asignatura asig = listaAsignaturas.buscar(codigoAsignatura);
            
            if(asig != null){
                
                if(p instanceof Alumno){
                    ((Alumno) p).getListaAsignaturasInscritas().ingresar(asig);
                    
                    Paralelo paralelo = listaParalelos.buscar(numeroParalelo);
                    
                    paralelo.getListaPersonas().ingresar(p);
                   
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
    public String obtenerAsignaturasDisponiblesObligatorias(String rut) {
        Persona p = listaPersonas.buscar(rut);
        String salida = "";
        salida += "Asignaturas disponibles: ";
        if(p!= null){
            if(p instanceof Alumno){
                Alumno alumno = (Alumno)p;
                
                ListaAsignaturas listaAsigCurs = alumno.getListaAsignaturasCursadas();   
                ListaAsignaturas listaAsigIns = alumno.getListaAsignaturasInscritas();
                for (int i = 0; i < listaAsignaturas.getCantAsignaturas(); i++) {
                    Asignatura asigGen = listaAsignaturas.getAsignaturaI(i);
                    if(asigGen instanceof AsignaturaObligatoria){
                        AsignaturaObligatoria asigObGen = (AsignaturaObligatoria) asigGen;
                        for (int j = 0; j < listaAsigCurs.getCantAsignaturas(); j++) {
                            Asignatura asigAlumCurs = listaAsigCurs.getAsignaturaI(j);
                            if(asigAlumCurs instanceof AsignaturaObligatoria){
                                AsignaturaObligatoria asigObAlumCurs = (AsignaturaObligatoria) asigAlumCurs;
                                
                                if((asigObGen.getCodigoAsignatura().equals(asigObAlumCurs.getCodigoAsignatura())) && asigObAlumCurs.getNota()<3.95){
                                    
                                }
                            }
                        }
                        
                        
                        for (int k = 0; k < listaAsigIns.getCantAsignaturas(); k++) {
                            Asignatura asigAlumIns = listaAsigIns.getAsignaturaI(k);
                            if(asigAlumIns instanceof AsignaturaObligatoria){
                                AsignaturaObligatoria asigObAlumIns = (AsignaturaObligatoria) asigAlumIns;
                                
                                if(!asigObGen.getCodigoAsignatura().equals(asigObAlumIns.getCodigoAsignatura())){
                                    salida += "\n\t"+asigObGen.getCodigoAsignatura();
                                }
                            }
                            
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
    public String obtenerAsignaturasDisponiblesOpcionales(String rut) {
        String dato = "";
        Persona p = listaPersonas.buscar(rut);
        if(p != null && p instanceof Alumno) {
            Alumno alumno =(Alumno)p;
            for(int i=0;i<listaAsignaturas.getCantAsignaturas();i++) {
                Asignatura asig = listaAsignaturas.getAsignaturaI(i);
                if(asig instanceof AsignaturaOpcional) {
                    AsignaturaOpcional asigOp = (AsignaturaOpcional)asig;
                    boolean encontrado=false;
                    int k;
                    for(k=0;k<alumno.getListaAsignaturasCursadas().getCantAsignaturas();k++) {
                        Asignatura asigCur = alumno.getListaAsignaturasCursadas().getAsignaturaI(k);
                        if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) &&asigCur.getNota() <3.95 ) {
                            dato += asigOp.getCodigoAsignatura()+" "+asigOp.getNombre()+" "+asigOp.getCreditos()+" "+asigOp.getCantCreditosPreRequisito()+"\n";
                            encontrado = true;
                            break;
                        }
                        if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNota() >=3.95 ) {
                            break;
                        }
                    }
                    if(k==alumno.getListaAsignaturasCursadas().getCantAsignaturas() && !encontrado) {
                        boolean reconocida = true;
                        for(int j=0;j<alumno.getListaAsignaturasInscritas().getCantAsignaturas();j++) {
                            Asignatura asigEstudi = alumno.getListaAsignaturasInscritas().getAsignaturaI(j);
                            if(asigOp.getCodigoAsignatura().equals(asigEstudi.getCodigoAsignatura())) {
                                reconocida = false;
                                break;
                            }
                        }
                        if (reconocida) {
                            dato += asigOp.getCodigoAsignatura()+" "+asigOp.getNombre()+" "+asigOp.getCreditos()+" "+asigOp.getCantCreditosPreRequisito()+"\n";
                        }
                    }    
                }
            }
        }
        return dato;
        
    }
    
    @Override
    public String obtenerParalelosDisponibles(String codigoAsignaturas) {
        Asignatura asig = listaAsignaturas.buscar(codigoAsignaturas);
        String salida = "";
        if(asig != null){
            salida +="Los paralelos disponibles son: ";
            for (int i = 0; i < listaParalelos.getCantParalelos(); i++) {
                Paralelo p = listaParalelos.getParaleloI(i);
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
    public boolean inscribirAsignatura(String rut, String codigoAsignatura, int numeroParalelo) {
        Persona persona = listaPersonas.buscar(rut);
        
        if(persona != null){
            if(persona instanceof Alumno){
                Alumno alum = (Alumno) persona;
                Asignatura asig = listaAsignaturas.buscar(codigoAsignatura);
                
                if(asig != null){
                    if(asig instanceof AsignaturaObligatoria){
                        AsignaturaObligatoria asigOblig = (AsignaturaObligatoria)asig;
                        int cantPreReq = asigOblig.getCantAsigPre();
                        
                        for (int j = 0; j < asigOblig.getCantAsigPre(); j++) {
                            for (int i = 0; i < alum.getListaAsignaturasCursadas().getCantAsignaturas(); i++) {
                                if(asigOblig.getAsigPreI(j).equals(alum.getListaAsignaturasCursadas().getAsignaturaI(i).getCodigoAsignatura())){
                                    cantPreReq -=1;
                                }
                            }
                        }
                        
                        if (cantPreReq == 0){
                            alum.getListaAsignaturasInscritas().ingresar(asigOblig);
                            listaParalelos.buscar(numeroParalelo).getListaPersonas().ingresar(alum);
                            
                            return true;
                        }
                        
                    }
                    
                    else if(asig instanceof AsignaturaOpcional){
                        AsignaturaOpcional asigObpc = (AsignaturaOpcional)asig;
                        if(alum.getTotalCreditos() <= asigObpc.getCantCreditosPreRequisito()){

                           alum.getListaAsignaturasInscritas().ingresar(asigObpc);
                           listaParalelos.buscar(numeroParalelo).getListaPersonas().ingresar(alum);
                           alum.setTotalCreditos(alum.getTotalCreditos()-asigObpc.getCantCreditosPreRequisito());
                           return true;
                        }
                         
                    }
                    
                }else{
                    throw new NullPointerException("La asignatura no existe");
                }               
            }
        }else{
            throw new NullPointerException("La persona no existe !");
        }
        return false;
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

    //arreglar contrato, en vez de buscar que coincida la asignatura, recorrer el paralelo donde esta el profesor
    
    @Override
    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo) {
        Persona p = listaPersonas.buscar(rut); // se puede borrar pq se buscar el paralelo y se recorre su lista de personas
        String salida = "";
        salida += "\nAlumnos del paralelo "+numeroParalelo;
        Paralelo paralelo = listaParalelos.buscar(numeroParalelo);
        ListaPersonas lpersonas = paralelo.getListaPersonas();
        if(lpersonas.getCantPersonas()!=0){
            for (int i = 0; i < lpersonas.getCantPersonas(); i++) {
                Persona persona = lpersonas.getPersonaI(i);

                if(persona instanceof Alumno){
                    Alumno alumno = (Alumno) persona;
                    salida += "\n\tAlumno: "+alumno.getRutPersona();
                }
            }
        }else{
            salida+= "Este paralelo no tiene alumnos inscritos !";
        }
        
        
        /*
        if(p!= null){
            if(p instanceof Profesor){
                Profesor profesor = (Profesor)p;
                ListaParalelos lpProfe = profesor.getListaParalelos();
                
                Paralelo paraleloP = lpProfe.buscar(numeroParalelo);
                
                if(paraleloP != null){
                    for (int i = 0; i < listaPersonas.getCantPersonas(); i++) {
                        Persona persona = listaPersonas.getPersonaI(i);
                        
                        if(persona instanceof Alumno){
                            Alumno alumno = (Alumno) persona;
                            ListaAsignaturas laAsig = alumno.getListaAsignaturasInscritas();
                            
                            for (int j = 0; j < laAsig.getCantAsignaturas(); j++) {
                                if(paraleloP.getAsignatura().getCodigoAsignatura().equals(laAsig.getAsignaturaI(i).getCodigoAsignatura())){
                                    salida += "\n\tAlumno: "+ alumno.getRutPersona();
                                }
                            }
                        }
                    }
                    
                }else{
                    throw new NullPointerException("El profesor "+profesor.getRutPersona()+" ,no tiene paralelos");
                }                
            }            
        }else{
            throw new NullPointerException("La persona no existe !");
        }
        */
        return salida;
    }

    @Override
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, Double notaFinal) {
        Persona persona = listaPersonas.buscar(rut);
        
        if(persona != null){
            if(persona instanceof Alumno){
                Alumno alumno = (Alumno) persona;
                ListaAsignaturas laInsAlum = alumno.getListaAsignaturasInscritas();
            
                Asignatura asig = laInsAlum.buscar(codigoAsignatura);
            
                if(asig != null){
                    asig.setNota(notaFinal);
                    return true;
                }else{
                    throw new NullPointerException("La asignatura no existe !");
                }
            }
            
        }else{
            throw new NullPointerException("La persona no existe !");
        }
        return false;
    }

    @Override
    public boolean comprobarNotaFinalAsignaturas(String codigoAsignatura, String rut) {
        Persona persona = listaPersonas.buscar(rut);
        
        if(persona != null){
            if(persona instanceof Alumno){
                Alumno alumno = (Alumno) persona;
                ListaAsignaturas laInsAlum = alumno.getListaAsignaturasInscritas();
                
                Asignatura asig = laInsAlum.buscar(codigoAsignatura);
                
                if(asig != null){
                    if(asig.getNota()>3.95){
                        alumno.getListaAsignaturasCursadas().ingresar(asig);
                        laInsAlum.eliminar(codigoAsignatura);
                        return true;
                    }
                    
                }else{
                    throw new NullPointerException("La asignatura no existe !");
                }
            }
        }else{
            throw new NullPointerException("La persona no existe !");
        }
        return false;
    }

    @Override
    public boolean eliminarAlumno() {
        for (int i = 0; i < listaPersonas.getCantPersonas(); i++) {
            Persona persona = listaPersonas.getPersonaI(i);
            
            if(persona instanceof Alumno){
                Alumno alumno = (Alumno) persona;
                
                if(alumno.getNivelAlumno() == 10){
                    listaPersonas.eliminar(alumno.getRutPersona());
                    return true;
                }
            }
        }
        return false;
    }


    

    

    
    
}
