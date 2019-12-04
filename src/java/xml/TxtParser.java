/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import control.ControlPath;
import datos.Cambio;
import datos.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TxtParser {


    public static ArrayList<Cambio> leerCambios(String user) {
        File file = new File(ControlPath.path+user+"/"+ControlPath.changes);
        ArrayList<Cambio> cambios = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                Cambio cambio = new Cambio();
                cambio.setCambio(delimitar.next());
                cambio.setCantidad(Integer.parseInt(delimitar.next()));
                cambio.setArchivo(delimitar.next());
                cambios.add(cambio);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return cambios;
    }

    public static void aniadirCambio(Cambio cambio, String user, String location) {
        ArrayList<Cambio> cambios = leerCambios(user);
        boolean estado=false;
        for(Cambio c: cambios){
            if(c.getArchivo().equals(cambio.getArchivo())){
                c.setCantidad(c.getCantidad()+1);
                estado=true;
            }
        }
        if(!estado){
            cambios.add(cambio);
        }
        aniadirCambios(cambios,false,user,location);
    }
    
    public static void aniadirCambios(ArrayList<Cambio> cambios, String user, String location) {
        aniadirCambios(cambios,false, user, location);
    }
    
    public static void aniadirCambios(ArrayList<Cambio> cambios, boolean sobreescribe, String user, String location) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ControlPath.path+user+"/"+location, sobreescribe);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            for(Cambio cambio: cambios)
                bfwriter.write(cambio.getCambio()+","+cambio.getCantidad()+","+cambio.getArchivo()+"\n");
            bfwriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static User login(String user, String password) {
        File file = new File(ControlPath.users);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                User usuario= new User(delimitar.next(),delimitar.next(),delimitar.next(),delimitar.next(),delimitar.next());
                if(user.equals(usuario.getUser()) && password.equals(usuario.getPassword()))
                    return usuario;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String readFile(final String path, final String user) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(ControlPath.path + user + "/" + path + "_LOG.log", new String[0]));
        return new String(encoded, StandardCharsets.US_ASCII);
    }
}
