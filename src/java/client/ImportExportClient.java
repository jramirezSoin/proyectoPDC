/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jcraft.jsch.JSchException;
import control.ControlPath;
import java.io.IOException;

/**
 *
 * @author Joseph Ramírez
 */
public class ImportExportClient {
 
    public static boolean exportPricing(String file, String type, String pinUser, String pinPwdUser, String pinPwdPDC, String exportado){
        boolean estado= false;
        try {
            String exportar="";
            String arch="";
            if(!exportado.equals("")){
                exportar = "-n \""+exportado+"\"";
                arch="2";
            }
            System.out.println("Inicia ssh...");
            SSHConnector sshConnector = new SSHConnector(); 
            sshConnector.connect(pinUser, pinPwdUser, ControlPath.pinHost, Integer.parseInt(ControlPath.pinPort));
            System.out.println("Conexión iniciada...");
            String comandos="cd "+ControlPath.pinDirectory+" ;";
            comandos+="[ -f "+file+arch+"_"+type+".xml ] && rm "+file+arch+"_"+type+".xml ;";
            comandos+="echo "+pinPwdPDC+" | "+ControlPath.pinImportExport+" -export "+file+arch+" -"+type+" "+file+" "+exportar+" &> "+file+"_LOG.log ;";
            comandos+="[ -f "+file+arch+"_"+type+".xml ] && echo \"SUCCESS\" ;";
            System.out.println("Exportando "+type+" "+file+"...");
            System.out.println(comandos);
            String s= sshConnector.executeCommand(comandos);
            s= s.replaceAll("\n", "-");
            if(s.matches("(.*)SUCCESS(.*)")){
                System.out.println("Descargando archivo "+file+"...");
                sshConnector.getXml(file+arch+"_"+type+".xml",pinUser);
                estado= true;
            }
            else{
                System.out.println("Error a la hora de exportar archivo "+file+"");
                sshConnector.getXml(file+"_LOG.log",pinUser);
            }
            sshConnector.disconnect();
            System.out.println("Conexión terminada...");
        } catch (JSchException ex) {
            ex.printStackTrace();        
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();         
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();         
            System.out.println(ex.getMessage());
        }
        return estado;
    }
    
    public static boolean importPricing(String file, String type, String pinUser, String pinPwdUser, String pinPwdPDC){
        boolean estado= false;
        try {
            System.out.println("Inicia ssh...");
            SSHConnector sshConnector = new SSHConnector(); 
            sshConnector.connect(pinUser, pinPwdUser, ControlPath.pinHost, Integer.parseInt(ControlPath.pinPort));
            System.out.println("Conexión iniciada...");
            System.out.println("Subiendo archivo "+file+".xml...");
            sshConnector.putXml(file+"_"+type+".xml",pinUser);
            sshConnector.putXml(file+"_"+type+"2.xml",pinUser);
            String comandos="cd "+ControlPath.pinDirectory+" ;";
            comandos+="echo "+pinPwdPDC+" | "+ControlPath.pinImportExport+" -import -"+type+" "+file+"_"+type+"2.xml -v -ow &> "+file+"_LOG.log ;";
            comandos+="[ $? -eq 0 ] && echo \"SUCCESS\" ;  ";
            System.out.println("Importando "+type+" "+file+"...");
            System.out.println(comandos);
            String s= sshConnector.executeCommand(comandos);
            s= s.replaceAll("\n", "-");
            System.out.println(s);
            if(s.matches("(.*)SUCCESS(.*)")){
                System.out.println("Import realizado!, "+file+"...");
                estado= true;
            }
            else{
                System.out.println("Error a la hora de importar archivo "+file+"");
                sshConnector.getXml(file+"_LOG.log",pinUser);
            }
            sshConnector.disconnect();
            System.out.println("Conexión terminada...");
        } catch (JSchException ex) {
            ex.printStackTrace();        
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();         
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();         
            System.out.println(ex.getMessage());
        }
        return estado;
    }
    
}
