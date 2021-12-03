/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author crist
 */
public class Taller_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        SistemaUCR system =new SistemaUCNImpl();
        lecturaAsignatura(system);        
        System.out.println("\n------------------");
        lecturaProfesor(system);        
        System.out.println("\n------------------");
        lecturaParalelo(system);
        System.out.println("\n------------------");
        lecturaEstudiantes(system);
       // menu(system);
        System.out.println("\n------------------");
        
    }
    
    public static void lecturaEstudiantes(SistemaUCR system)throws IOException{
        Scanner s = new Scanner(new File("estudiantes.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();            
            String [] partes = line.split(",");
            String rut = partes[0];            
            String correo = partes[1];                  
            int nivelAlumno = Integer.parseInt(partes[2]);            
            String constraseña = partes[3];
            try{
                boolean ingreso = system.ingresarAlumno(rut, correo, nivelAlumno, constraseña);
                
                if(ingreso){
                    line = s.nextLine();
                    int cantAsigCursadas = Integer.parseInt(line);
                    for (int i = 0; i < cantAsigCursadas; i++) {               
                        line = s.nextLine();
                        String [] partes2 = line.split(",");
                        String codigo = partes2[0];                        
                        Double notaFinal = Double.parseDouble(partes2[1]);
                        try{
                            
                            boolean ingresoAsignaturaCurs = system.ingresarAsociarAlumnoAsignaturaCursada(rut, codigo, notaFinal);
                           
                            if(!ingresoAsignaturaCurs){
                                
                                System.out.println("Asignatura Cursada Alumno NO PUDO ser Ingresada");
                            }
                        }
                       
                        catch(Exception ex){
                            
                            System.out.println(ex.getMessage());
                            
                        }
                    }
                        
                    line = s.nextLine();
                    
                    int cantAsigInscritas = Integer.parseInt(line);
                    for (int i = 0; i < cantAsigInscritas; i++) {               
                        line = s.nextLine();
                        String [] partes3 = line.split(",");
                        String codigo = partes3[0];
                        
                        int numeroParalelo = Integer.parseInt(partes3[1]);
                        
                        try{
                            
                            boolean ingresoAsignaturaIns = system.ingresarAsociarAlumnoAsignaturaInscrita(rut, codigo, numeroParalelo);
                            
                            if(!ingresoAsignaturaIns){
                                
                                System.out.println("Asignatura inscrita Alumno NO PUDO ser Ingresada");
                            }
                        }
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                   
                }


            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    
    public static void lecturaAsignatura(SistemaUCR system)throws IOException{
        Scanner s = new Scanner(new File("asignaturas.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            String codigoAsig = partes[0];
            String nombreAsig = partes[1];
            int creditosAsig = Integer.parseInt(partes[2]);
            String tipoAsignatura = partes[3];
            if(tipoAsignatura.equals("obligatoria")){
                try{
                    int nivelMalla = Integer.parseInt(partes[4]);                    
                    int cantAsigPreReq = Integer.parseInt(partes[5]);
                    try{
                        boolean ingresoOblig = system.ingresarAsignaturaObligatoria(codigoAsig, nombreAsig, creditosAsig, nivelMalla, cantAsigPreReq);                          
                        for (int i = 6; i < partes.length; i++) {
                            String codigoAsigPreReq = partes[i];
                            try{
                                   
                                boolean asociarIngreso = system.asociarCodigoAsignaturaObligatoria( codigoAsig, codigoAsigPreReq);
                                if (!ingresoOblig &&  !asociarIngreso){
                                    System.out.println("Asignatura Obligatoria no ha podido ser ingresada");
                                }
                            }catch(Exception ex){
                                System.out.println(ex.getMessage());
                            }
                        }
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            else if (tipoAsignatura.equals("opcional")){
                try{
                    int creditosPre = Integer.parseInt(partes[4]);
                    system.ingresarAsignaturaOpcional(codigoAsig, nombreAsig, creditosAsig, creditosPre);
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            
        }  

    }
    
    public static void lecturaProfesor(SistemaUCR system)throws IOException{
        Scanner s = new Scanner(new File("profesores.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            String rut = partes[0];
            String correo = partes[1];
            String contraseña = partes[2];
            int salario = Integer.parseInt(partes[3]);
            try{
                system.ingresarProfesor(rut, correo, contraseña, salario);
                
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }  

    }
    
    public static void lecturaParalelo(SistemaUCR system)throws IOException{
        Scanner s = new Scanner(new File("paralelos.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            int numeroParalelo = Integer.parseInt(partes[0]);
            String codigoAsig = partes[1];
            String rutProfesor = partes[2];
            
            try{
                boolean ingresoParalelo = system.ingresarParalelo(numeroParalelo, codigoAsig, rutProfesor);
                if(!ingresoParalelo){
                    System.out.println("Paralelo no ingresado");
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }  

    }

    private static void menu(SistemaUCR system) throws ParseException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el correo: ");
        String correo=sc.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String contraseña=sc.nextLine();
        if(!correo.equals("Admin") && !contraseña.equals("GHI_789")){
            boolean op = iniciarSesion(correo,contraseña);
            while(!op){
                int opcion=0;
                System.out.println("Correo o contraseña invalida");
                while(opcion==0){
                try{                
                    System.out.println("Desea volver a intentarlo (1) o cerrar el sistema (2)");
                    opcion=Integer.parseInt(sc.nextLine());                
                }catch(Exception e){
                    System.out.println("Ingrese una opcion valida");
                    opcion=Integer.parseInt(sc.nextLine());
                }if(opcion==1){
                    System.out.println("Ingrese el correo");
                    correo=sc.nextLine();
                    System.out.println("Ingrese la contraseña");
                    contraseña=sc.nextLine();
                    op=iniciarSesion(correo,contraseña);
                }else if (opcion==2){
                    System.out.println("Cerrando sistema...");
                    System.exit(0);
                }else{
                    System.out.println("ingrese un numero valido entre 1 y 2");
                    opcion=0;
                    }   
                }            
            }
        }int periodo=ingresoFecha();
        if(periodo==1){
            inicioDeSemestre(correo);
        }else if(periodo==2){
            mitadDeSemestre(correo);
        }else if(periodo==3){
            finDeSemetre(correo);
        }else if(periodo==4){
            cierreDelSemestre(correo);
        }else{
            System.out.println("Disfrute de sus vacaciones");   
        }
    
    }

    private static boolean iniciarSesion(String correo, String contraseña) {
        if (correo.equals("")&&contraseña.equals("")){
            return true;
            
        }else{
            return false;
        }
        
    }

    private static void menuAdmin() {
        
    }

    private static int ingresoFecha() throws ParseException {
        Scanner sc= new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        Date date1=dateFormat.parse("08/03/2021");
        Date date2=dateFormat.parse("02/05/2021");
        Date date3=dateFormat.parse("03/05/2021");
        Date date4=dateFormat.parse("11/07/2021");
        Date date5=dateFormat.parse("12/07/2021");
        Date date6=dateFormat.parse("25/07/2021");
        Date date7=dateFormat.parse("26/07/2021");
        Date inputDate=null; 
        System.out.println("Ingrese fecha con formato dd/MM/yyyy");
        String date=sc.nextLine();           
        boolean confirmacion=false;
        while(!confirmacion){                
            try{
                inputDate=dateFormat.parse(date);
                confirmacion=true;
            }
             catch(Exception e){
                    System.out.println("formato invalido ");
                      
                    System.out.println("Ingrese fecha con formato yyyy-MM-dd");
                    date=sc.nextLine();      
                    }  
            }
        if(inputDate.compareTo(date1)>=0 && inputDate.compareTo(date2)<=0){
            System.out.println("Inicio de semestre");
            return 1;
        }else if(inputDate.compareTo(date3)>=0 && inputDate.compareTo(date4)<=0){
            System.out.println("Mitad de semestre");
            return 2;
        }else if(inputDate.compareTo(date5)>=0 && inputDate.compareTo(date6)<=0){
            System.out.println("Fin de semestre");
            return 3;
        }else if(inputDate.compareTo(date7)==0){
            System.out.println("Cierre del semestre");
            return 4;
        }else{           
            return 5;
        }
    } 

    private static void inicioDeSemestre(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void mitadDeSemestre(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void finDeSemetre(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void cierreDelSemestre(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
