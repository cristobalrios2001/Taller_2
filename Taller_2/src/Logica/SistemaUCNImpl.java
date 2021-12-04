
package Logica;

import Dominio.*;
import java.util.HashSet;
import java.util.Set;


public class SistemaUCNImpl implements SistemaUCR {

    private ListaPersonas listaPersonas;
    private ListaAsignaturas listaAsignaturas;
    private ListaParalelos listaParalelos;
    private ListaPersonas listaPersonasEliminadas;
    
    public SistemaUCNImpl(){
        listaPersonas = new ListaPersonas(1000);
        listaAsignaturas = new ListaAsignaturas(650);
        listaParalelos = new ListaParalelos(100);
        listaPersonasEliminadas = new ListaPersonas(1000);
        
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
    public boolean ingresarParalelo(int numeroParalelo, String codigoAsignatura, String rutProfesor) {
        Paralelo paralelo = new Paralelo (numeroParalelo,codigoAsignatura,rutProfesor);
        boolean ingresado = listaParalelos.ingresar(paralelo);
        for (int i = 0; i < listaPersonas.getCantPersonas(); i++) {
            Persona p = listaPersonas.getPersonaI(i);
            if(p instanceof Profesor){
                Profesor profesor = (Profesor)p;
                if(profesor.getRutPersona().equals(rutProfesor)){
                    ListaParalelos lp = profesor.getListaParalelos();
                    lp.ingresar(paralelo);
                    return true;
                }
            }else{
                throw new NullPointerException("");
            }
        }
        return ingresado;
        
        
    }
    
    @Override 
    public boolean ingresarAsociarAlumnoAsignaturaCursada(String rutAlumno, String codigoAsignatura, Double notaFinal) {
        Persona p = listaPersonas.buscar(rutAlumno);
        
        if(p != null){
            Asignatura asig = listaAsignaturas.buscar(codigoAsignatura);
            if(asig != null){
                if(p instanceof Alumno){
                    Alumno alumno = (Alumno)p;
                    int nivelAsigMin = 11;
                    if(asig instanceof AsignaturaObligatoria){
                        if(nivelAsigMin > ((AsignaturaObligatoria) asig).getNivelEnMalla()){
                            nivelAsigMin = ((AsignaturaObligatoria) asig).getNivelEnMalla();
                        }
                    }
                    
                    asig.setNota(notaFinal);
                    alumno.setNivelAlumno(nivelAsigMin);
                    return alumno.getListaAsignaturasCursadas().ingresar(asig);
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
                    Alumno alumno = (Alumno)p;
                    System.out.println(alumno.getRutPersona());
                    alumno.getListaAsignaturasInscritas().ingresar(asig);
                    
                    Paralelo paralelo = listaParalelos.buscar(numeroParalelo);
                    
                    paralelo.getListaPersonas().ingresar(alumno);
                    
                    paralelo.setCupoParalelo(paralelo.getCupoParalelo()+1);
                    
                    return true;
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
    public String obtenerAsignaturasDisponiblesObligatorias(String rut) {
        String dato = "";
        Persona p = listaPersonas.buscar(rut);
        if(p != null && p instanceof Alumno) {
            Alumno estudiante = (Alumno)p;
            for(int i=0;i<listaAsignaturas.getCantAsignaturas();i++) {
                Asignatura asig = listaAsignaturas.getAsignaturaI(i);
                if(asig instanceof AsignaturaObligatoria ) {
                    AsignaturaObligatoria asigOb = (AsignaturaObligatoria)asig;
                    int j;
                    boolean reconocida = true;
                    for(j=0;j<estudiante.getListaAsignaturasInscritas().getCantAsignaturas();j++) {
                        Asignatura asigIns = estudiante.getListaAsignaturasInscritas().getAsignaturaI(j);
                        if(asigOb.getCodigoAsignatura().equals(asigIns.getCodigoAsignatura())) {
                            reconocida = false;
                            break;
                        }
                    }
                    if(reconocida && asigOb.getNivelEnMalla()<= estudiante.getNivelAlumno() ) {
                        dato+= "\nCodigo: "+asigOb.getCodigoAsignatura()+", Nombre: "+asigOb.getNombre()+" , Creditos: "+asigOb.getCreditos()+" ,Nivel malla: "+asigOb.getNivelEnMalla()+", Cant Asig Obligatorias: "+asigOb.getCantAsigPre()+", ";
                        for(int k=0;k<asigOb.getCantAsigPre();k++) {
                            dato+= "\nAsignaturas pre Requisito: \n\t"+asigOb.getAsigPreI(k)+",";
                        }
                        
                    }
                    if(j==estudiante.getListaAsignaturasInscritas().getCantAsignaturas()) {                       
                        for(int a=0;a<estudiante.getListaAsignaturasCursadas().getCantAsignaturas();a++) {
                            Asignatura asigCur = estudiante.getListaAsignaturasCursadas().getAsignaturaI(a);
                            if(asigOb.getNivelEnMalla() <= estudiante.getNivelAlumno()) {
                                if(asigOb.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNota() <3.95) {
                                    dato+=asigOb.getCodigoAsignatura()+" "+asigOb.getNombre()+" "+asigOb.getCreditos()+" "+asigOb.getNivelEnMalla()+" "+asigOb.getCantAsigPre()+", ";
                                    for(int k=0;k<asigOb.getCantAsigPre();k++) {
                                        dato+= asigOb.getAsigPreI(k)+",";
                                    }
                                    break;
                                }
                                if(asigOb.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNota() >=3.95) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return dato;
        
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
                    
                    int k;
                    for(k=0;k<alumno.getListaAsignaturasCursadas().getCantAsignaturas();k++) {
                        Asignatura asigCur = alumno.getListaAsignaturasCursadas().getAsignaturaI(k);
                        if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) &&asigCur.getNota() <3.95 ) {
                            dato += asigOp.getCodigoAsignatura()+" "+asigOp.getNombre()+" "+asigOp.getCreditos()+" "+asigOp.getCantCreditosPreRequisito()+"\n";
                            break;
                        }
                        if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNota() >=3.95 ) {
                            break;
                        }
                    }
                    if(k==alumno.getListaAsignaturasCursadas().getCantAsignaturas()) {
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
                if(p.getCupoParalelo()<100){
                    if(p.getAsignatura().equals(asig.getCodigoAsignatura())){
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
                    
                    Paralelo paralelo = listaParalelos.buscar(numeroParalelo);
                    if (paralelo!= null){
                        if(asig instanceof AsignaturaObligatoria){
                            int encontradas= 0;
                            AsignaturaObligatoria asigOb = (AsignaturaObligatoria)asig;
                            for (int i = 0; i < alum.getListaAsignaturasCursadas().getCantAsignaturas(); i++) {
                                for (int j = 0; j < asigOb.getCantAsigPre(); j++) {
                                    String asigPre = asigOb.getAsigPreI(j);

                                    if(alum.getListaAsignaturasCursadas().getAsignaturaI(i).getCodigoAsignatura().equals(asigPre) && alum.getListaAsignaturasCursadas().getAsignaturaI(i).getNota()>=3.95){
                                        encontradas++;
                                    }
                                }
                            }

                            if (encontradas == asigOb.getCantAsigPre()){                               
                                if( alum.getTotalCreditos() < 40){
                                    alum.setTotalCreditos(alum.getTotalCreditos()+asig.getCreditos());
                                    alum.getListaAsignaturasInscritas().ingresar(asig);
                                    paralelo.getListaPersonas().ingresar(alum);
                                   
                                    asigOb.setParalelo(paralelo.getNumeroParalelo());
                                    return true;
                                }
                            }
                        
                        }
                    
                        else{
                            AsignaturaOpcional asigOp= (AsignaturaOpcional)asig;
                            if(alum.getTotalCreditos() > asigOp.getCantCreditosPreRequisito() && alum.getTotalCreditos()<40){
                               alum.getListaAsignaturasInscritas().ingresar(asigOp);
                               paralelo.getListaPersonas().ingresar(alum);
                               alum.setTotalCreditos(alum.getTotalCreditos()+asigOp.getCantCreditosPreRequisito());
                               asigOp.setParalelo(paralelo.getNumeroParalelo());
                               return true;
                            }

                        }
                    }else{
                        throw new NullPointerException("El paralelo no existe !");
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
                    salida+= "El alumno no tiene asignaturas inscritas";
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
                Asignatura asig = listaAsignaturas.buscar(código);
                if (asig != null){
                    Asignatura asigAlum = alumno.getListaAsignaturasInscritas().buscar(código);
                    if(asigAlum != null){
                        alumno.getListaAsignaturasInscritas().eliminar(código);
                    }else{
                        throw new NullPointerException("La asignatura no existe en las asignaturas del alumno");
                    }
                }else{
                    throw new NullPointerException("La asignatura no existe");
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
                        salida += "\n\tNumero paralelo: "+ paralelo.getNumeroParalelo()+" (Asignatura: "+paralelo.getAsignatura()+")";
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

    
    @Override
    public String obtenerAlumnosParalelosProfesor(String rut, int numeroParalelo) {
        String salida = "";
        Persona persona = listaPersonas.buscar(rut);
        if(persona != null && persona instanceof Profesor)
        {
            
            Profesor profesor = (Profesor) persona;
            Paralelo paralelo = listaParalelos.buscar(numeroParalelo);
            if(paralelo != null)
            {
                Paralelo paraleloProfesor = profesor.getListaParalelos().buscar(numeroParalelo);
                if(paraleloProfesor != null)
                {
                    salida += "\nAlumnos del Paralelo: "+paraleloProfesor.getNumeroParalelo();
                    ListaPersonas lpersParalelo = paraleloProfesor.getListaPersonas();
                    
                    for (int i = 0; i < lpersParalelo.getCantPersonas(); i++) 
                    {
                        Persona personaParalelo = lpersParalelo.getPersonaI(i);
                        if(personaParalelo instanceof Alumno)
                        {
                            Alumno alumno = (Alumno)personaParalelo;
                            salida += "\n\t Rut Alumno: "+alumno.getRutPersona()+", Correo Alumno: "+alumno.getCorreo();
                        }
                    }
                }else{
                    throw new NullPointerException("El profesor no realiza clases a este paralelo !");
                }
            }else{
                throw new NullPointerException("El paralelo no existe en el sistema!");
            }
            
        }else{
            throw new NullPointerException("El profesor no existe !");
        }
        return salida;
    }

    @Override
    public boolean ingresarNotaFinal(String codigoAsignatura, String rut, Double notaFinal) {
        Persona persona = listaPersonas.buscar(rut);
        if(persona != null && persona instanceof Alumno){
            for (int i = 0; i < listaParalelos.getCantParalelos(); i++) {
                Paralelo paralelo = listaParalelos.getParaleloI(i);
                if(paralelo.getAsignatura().equals(codigoAsignatura)){
                    Asignatura asig = listaAsignaturas.buscar(codigoAsignatura);
                    Alumno alumno =(Alumno)persona;
                    Asignatura asigAlum = alumno.getListaAsignaturasInscritas().buscar(asig.getCodigoAsignatura());
                    asigAlum.setNota(notaFinal);
                    alumno.getListaAsignaturasCursadas().ingresar(asigAlum);
                    alumno.getListaAsignaturasInscritas().eliminar(asigAlum.getCodigoAsignatura());
                    paralelo.getListaPersonas().eliminar(rut);
                    return true;
                }
            }
        }else{
            throw new NullPointerException("El alumno no existe");
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
                    listaPersonasEliminadas.ingresar(alumno);
                    for (int j = 0; j < listaParalelos.getCantParalelos(); j++) {
                        Paralelo paralelo = listaParalelos.getParaleloI(j);
                        ListaPersonas lpersonas = paralelo.getListaPersonas();
                        for (int k = 0; k < lpersonas.getCantPersonas(); k++) {
                            if(lpersonas.getPersonaI(k).equals(alumno)){
                                listaPersonasEliminadas.ingresar(alumno);
                                paralelo.getListaPersonas().eliminar(alumno.getRutPersona());
                                listaPersonas.eliminar(alumno.getRutPersona());
                                paralelo.setCupoParalelo(paralelo.getCupoParalelo()-1);
                                return true;
                            }
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    
    
    @Override
    public String sobreescribir(){
        String salida = "";
        
        for (int i = 0; i < listaPersonasEliminadas.getCantPersonas(); i++) {
            salida +=listaPersonasEliminadas.getPersonaI(i).getRutPersona();
        }
        return salida;
        
    }
    
    @Override
    public boolean buscarCorreo(String correo){
        Persona p= listaPersonas.buscarCorreo(correo);
        if(p==null){
           return false;
        }else{
           return true;
        }
        
    }
    
    @Override
    public boolean contraseñaCorrecta(String correo, String contraseña){
        Persona p= listaPersonas.buscarCorreo(correo);
         if(p!=null){
            if(p.getContraseña().equals(contraseña)){
                return true;
            } 
        }else{
            throw new NullPointerException("contraseña incorrecta");
            
        }return false;   
    }
    
    
    @Override
    public String obtenerRut(String correo){
        Persona p = listaPersonas.buscarCorreo(correo);
        return p.getRutPersona();
    }
    
    @Override
    public int tipoPersona(String correo){
        Persona p= listaPersonas.buscarCorreo(correo);
        if(p instanceof Alumno){
            return 1;
        }else if (p instanceof Profesor){
            return 2;
        }else{
            return 3;
        }        
    }
}
