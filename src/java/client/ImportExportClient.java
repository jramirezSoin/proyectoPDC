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
 
    public static boolean exportPricing(String file, String type, String pinUser, String pinPwdUser, String pinPwdPDC){
        boolean estado= false;
        try {
            System.out.println("Inicia ssh...");
            SSHConnector sshConnector = new SSHConnector(); 
            sshConnector.connect(pinUser, pinPwdUser, ControlPath.pinHost, Integer.parseInt(ControlPath.pinPort));
            System.out.println("Conexión iniciada...");
            String comandos="cd "+ControlPath.pinDirectory+" ;";
            comandos+="[ -f "+file+"_"+type+".xml ] && rm "+file+"_"+type+".xml ;";
            comandos+="echo "+pinPwdPDC+" | "+ControlPath.pinImportExport+" -export "+file+" -"+type+" "+file+" ;";
            comandos+="[ -f "+file+"_"+type+".xml ] && echo \"yes\" ;";
            System.out.println("Exportando "+type+" "+file+"...");
            System.out.println(comandos);
            String s= sshConnector.executeCommand(comandos);
            s= s.replaceAll("\n", "-");
            if(s.matches("(.*)-yes(.*)")){
                System.out.println("Descargando archivo "+file+"...");
                sshConnector.getXml(file+"_"+type+".xml",pinUser);
                estado= true;
            }
            else{
                System.out.println("Error a la hora de exportar archivo "+file+"");
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
            String comandos="cd "+ControlPath.pinDirectory+" ;";
            comandos+="echo "+pinPwdPDC+" | "+ControlPath.pinImportExport+" -import -"+type+" "+file+"_"+type+".xml -v -ow ;";
            comandos+="[ $? -eq 0 ] && echo \"yes\" ;";
            System.out.println("Importando "+type+" "+file+"...");
            System.out.println(comandos);
            String s= sshConnector.executeCommand(comandos);
            s= s.replaceAll("\n", "-");
            System.out.println(s);
            if(s.matches("(.*)-yes(.*)")){
                System.out.println("Import realizado!, "+file+"...");
                estado= true;
            }
            else{
                System.out.println("Error a la hora de exportar archivo "+file+"");
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
