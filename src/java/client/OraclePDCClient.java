/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.oracle.communications.pricing.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.xml.ws.BindingProvider;
import pricinggateway.PricingGateway;
import pricinggateway.PricingGatewayPortType;

/**
 *
 * @author Joseph Ramírez
 */
public class OraclePDCClient {

    private static final String FILE = "C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/occuser-25/ROLLOVER_RATE_PLAN_pricing.xml";
    private static final int BUFFER_SIZE= 1024;
    private static String pdcUrl = "http://10.149.137.42:22537/pdc/PricingGatewayPort?wsdl";
    private static String pdcUserName="occuser-25";
    private static String pdcUserPassword="P4sspdc25";
    private static String PDC="PDC";
    
    
    public static void crearPricing() throws Exception{
        //Abre el puerto WS
        System.setProperty("https.protocols", "SSLv3");
        
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
            new javax.net.ssl.HostnameVerifier(){

                public boolean verify(String hostname,
                        javax.net.ssl.SSLSession sslSession) {
                    return hostname.equals("localhost");
                }
            });
        
        PricingGateway port = new PricingGateway();
        PricingGatewayPortType pricingGatewayPortType =  port.getPricingGatewayPort();

        Map<String, Object> rc = ((BindingProvider)pricingGatewayPortType).getRequestContext();
        //rc. put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, pdcUrl);
        rc.put(BindingProvider.USERNAME_PROPERTY,pdcUserName);
        rc.put(BindingProvider.PASSWORD_PROPERTY,pdcUserPassword);

        UserContextType userContext = new UserContextType();
        userContext.setUserid(pdcUserName);

        PricingInputXMLType pricingInputXMLType = new PricingInputXMLType();
        pricingInputXMLType.setUserContext(userContext);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(bs);
                FileInputStream fl = null;
                try {
                    fl = new FileInputStream(new File(FILE));
                    // Set the compression ratio
                    out.setLevel(Deflater.BEST_COMPRESSION);
                    ZipEntry ze = new ZipEntry(PDC);
                    out.putNextEntry(ze);
                    byte[] data = new byte[BUFFER_SIZE];            int count = 0;
                    BufferedInputStream in = new BufferedInputStream(fl);
                    while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
                        out.write(data, 0, count);
                    }
                } catch (Exception e) {
                    throw e;
                } finally {
                    if (fl != null) {
                        fl.close();
                    }
                    if (bs != null) {
                        bs.flush();
                    }
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
                byte[] bytesToRet = null;
                if (bs != null) {
                    bytesToRet = bs.toByteArray();
                }
        pricingInputXMLType.setXmlBinaryString(bytesToRet);
        PDCResponseType pDCResponseType = pricingGatewayPortType.createPricing(pricingInputXMLType);
        System.out.println(pDCResponseType.getStatus());
    }
    
    public static byte[] leerXml() throws Exception{
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(bs);
        FileInputStream fl = null;
        try {
            fl = new FileInputStream(new File(FILE));
            // Set the compression ratio
            out.setLevel(Deflater.BEST_COMPRESSION);
            ZipEntry ze = new ZipEntry(PDC);
            out.putNextEntry(ze);
            byte[] data = new byte[BUFFER_SIZE];            int count = 0;
            BufferedInputStream in = new BufferedInputStream(fl);
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
                out.write(data, 0, count);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (fl != null) {
                fl.close();
            }
            if (bs != null) {
                bs.flush();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        byte[] bytesToRet = null;
        if (bs != null) {
            //pasa bs a bytesToRet
            bytesToRet = bs.toByteArray();
        }
        
        return bytesToRet;
    
    }
    
    public static void unzip(byte[] bytesToRet) throws Exception, IOException{
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayInputStream bs = new ByteArrayInputStream(bytesToRet);
        ZipInputStream zis = new ZipInputStream(bs);
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            FileOutputStream fos = new FileOutputStream("C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/unzip.xml");
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
    
}
