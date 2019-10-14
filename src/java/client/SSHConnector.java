/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import control.ControlPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSHConnector {
 

    private static final String ENTER_KEY = "\n";

    private Session session;
 
    public void connect(String username, String password, String host, int port)
        throws JSchException, IllegalAccessException {
        if (this.session == null || !this.session.isConnected()) {
            JSch jsch = new JSch();
 
            this.session = jsch.getSession(username, host, port);
            this.session.setPassword(password);
 
            // Parametro para no validar key de conexion.
            this.session.setConfig("StrictHostKeyChecking", "no");
 
            this.session.connect();
        } else {
            throw new IllegalAccessException("Sesion SSH ya iniciada.");
        }
    }
 
    public final String executeCommand(String command)
        throws IllegalAccessException, JSchException, IOException {
        if (this.session != null && this.session.isConnected()) {
 
            // Abrimos un canal SSH. Es como abrir una consola.
            ChannelExec channelExec = (ChannelExec) this.session.
                openChannel("exec");
 
            InputStream in = channelExec.getInputStream();
 
            // Ejecutamos el comando.
            channelExec.setCommand(command);
            channelExec.connect();
 
            // Obtenemos el texto impreso en la consola.
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String linea;
 
            while ((linea = reader.readLine()) != null) {
                builder.append(linea);
                builder.append(ENTER_KEY);
            }
 
            // Cerramos el canal SSH.
            channelExec.disconnect();
 
            // Retornamos el texto impreso en la consola.
            return builder.toString();
        } else {
            throw new IllegalAccessException("No existe sesion SSH iniciada.");
        }
    }
    
    public final void getXml(String file){
        
        try {
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            System.out.println(ControlPath.pinDirectory+file);
            System.out.println(ControlPath.path + file);
            sftpChannel.get(ControlPath.pinDirectory+file, ControlPath.path + file);
            sftpChannel.exit();
        } catch (JSchException ex) {
            Logger.getLogger(SSHConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SftpException ex) {
            Logger.getLogger(SSHConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public final void putXml(String file){
        
        try {
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            
            sftpChannel.put(ControlPath.path + file, ControlPath.pinDirectory+ file);
            sftpChannel.exit();
        } catch (JSchException ex) {
            Logger.getLogger(SSHConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SftpException ex) {
            Logger.getLogger(SSHConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    public final void disconnect() {
        this.session.disconnect();
    }
}
