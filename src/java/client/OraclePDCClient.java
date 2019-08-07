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

    private static final String PDC_USER="";
    private static final String PDC_PASSWORD="";
    private static final String FILE = "C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/EM_20190731_HOLIDAY_CALENDAR_config.xml";
    private static final String URL = "http://10.149.137.42:22537/pdc/PricingGatewayPort?wsdl";
    private static final int BUFFER_SIZE= 1024;
    
    public static void crearPricing() throws Exception{
        //Abre el puerto WS
        PricingGateway port = new PricingGateway();
        PricingGatewayPortType pricingGatewayPortType =  port.getPricingGatewayPort();

        //Autenticacion HttpHeader
        Map<String, Object> rc = ((BindingProvider)pricingGatewayPortType).getRequestContext();
        rc. put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
        rc.put(BindingProvider.USERNAME_PROPERTY,PDC_USER);
        rc.put(BindingProvider.PASSWORD_PROPERTY,PDC_PASSWORD);

        //Crear objeto UserContextType
        UserContextType userContext = new UserContextType();
        userContext.setUserid(PDC_USER);

        //Crea y asigna a PricingInputXMLType el objeto anterior
        PricingInputXMLType pricingInputXMLType = new PricingInputXMLType();
        pricingInputXMLType.setUserContext(userContext);

        //comprime el xml
        byte[] bytesToRet = leerXml();
        
        //agrega a princingInputXMLType el bytesToRet con el xml compreso
        pricingInputXMLType.setXmlBinaryString(bytesToRet);

        //Envia el objeto bytesToRet con el xml
        PDCResponseType pDCResponseType = pricingGatewayPortType.createPricingAndSubmit(pricingInputXMLType);
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
            ZipEntry ze = new ZipEntry("PDC");
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
    
    public static void main(String[] args) throws Exception {  
        byte[] bytesToRet= leerXml();
        unzip(bytesToRet);
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
