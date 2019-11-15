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
                cambio.setFecha(delimitar.next());
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

    public static void aniadirCambio(Cambio cambio, String user) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ControlPath.path+user+"/"+ControlPath.changes, true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(cambio.getCambio()+","+cambio.getFecha()+","+cambio.getArchivo()+"\n");
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
    
    public static void aniadirCambios(ArrayList<Cambio> cambios, String user) {
        aniadirCambios(cambios,false, user);
    }
    
    public static void aniadirCambios(ArrayList<Cambio> cambios, boolean sobreescribe, String user) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ControlPath.path+user+"/"+ControlPath.changes, sobreescribe);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            for(Cambio cambio: cambios)
                bfwriter.write(cambio.getCambio()+","+cambio.getFecha()+","+cambio.getArchivo()+"\n");
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
}
