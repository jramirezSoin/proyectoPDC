/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import datos.ListaT;
import datos.Nodo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xml.XmlParser;


public class FirstPDF {
    public static Font.FontFamily fontfamily = Font.FontFamily.HELVETICA;
    public static int r=1, g=2, b=3;
    public static BaseColor AZUL= new BaseColor(14,85,99);
    public static BaseColor AMARILLO= new BaseColor(255,196,12);
    public static BaseColor GRIS= new BaseColor(242,242,242);
    
    public static Font h1 = new Font(fontfamily, 18,Font.BOLD,AZUL);
    public static Font h2 = new Font(fontfamily, 16,Font.BOLD,AZUL);
    public static Font h3 = new Font(fontfamily, 14,Font.BOLD,AZUL);
    public static Font h4 = new Font(fontfamily, 12,Font.BOLD,AZUL);
    public static Font h5 = new Font(fontfamily, 10,Font.BOLD,AZUL);
    public static Font white = new Font(fontfamily, 12,Font.BOLD,BaseColor.WHITE);
    public static Font p = new Font(fontfamily,10,Font.NORMAL,AZUL);


    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public static void crearPDF(String filename, Nodo nodo, String user){
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+user+"/PDF/"+filename));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.h1));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            line.setLineColor(AZUL);
            document.add(line);
            nodo.getPDF(document);
            document.close();    
            } catch (DocumentException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    public static PdfPCell createTableHeader(String name){
        PdfPCell c1 = new PdfPCell(new Phrase(name,white));
        c1.setUseVariableBorders(true);
        c1.setBorderColor(BaseColor.WHITE);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(AZUL);
        return c1;
    }
    
    public static PdfPCell createTableCell(String name){
        return createTableCell(name,GRIS,p,BaseColor.WHITE);
    }
    
    public static PdfPCell createTableCell(String name, BaseColor base,Font font, BaseColor border){
        PdfPCell c1 = new PdfPCell(new Phrase(name,font));
        c1.setUseVariableBorders(true);
        c1.setBorderColor(border);
        c1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        c1.setBackgroundColor(base);
        return c1;
    }
    
    public static PdfPTable createDescription(String s1,String s2){
        try {
            PdfPTable table = new PdfPTable(2);
            float[] columnWidths = new float[]{10f, 20f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100f);
            table.addCell(createTableCell(s1,AMARILLO,h5,BaseColor.WHITE));
            table.addCell(createTableCell(s2));
            return table;
        } catch (DocumentException ex) {
            Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void crearListaPDF(String name, ArrayList<ListaT> nodo, String user) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+user+"/PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.h1));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            line.setLineColor(AZUL);
            document.add(line);
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Lista", FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(1);
            //float[] columnWidths = new float[]{30f, 10f, 10f, 20f};
            //table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Items"));
            for(ListaT item : nodo){
                if(item.visibilidad){
                table.addCell(FirstPDF.createTableCell(item.valor));
                }
            }
            preface.add(table);
            document.add(preface);
            document.close();    
            } catch (DocumentException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static void crearListaPDF(String name, ArrayList<ListaT> nodo, ArrayList<Integer> checks, String user) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+user+"/PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.h1));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            line.setLineColor(AZUL);
            document.add(line);
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Lista", FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(1);
            //float[] columnWidths = new float[]{30f, 10f, 10f, 20f};
            //table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Items"));
            for(ListaT item : nodo){
                if(item.visibilidad && checks.contains(item.id)){
                table.addCell(FirstPDF.createTableCell(item.valor));
                }
            }
            preface.add(table);
            document.add(preface);
            document.close();    
            } catch (DocumentException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            }      
    }

    public static void crearListaDPDF(String name, ArrayList<ListaT> nodo, ArrayList<Integer> checks, String path, String user) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+user+"/PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.h1));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            document.add(line);
            for(int item : checks){
                ArrayList<String> lis= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,path)) , item);
                Nodo node= ControlPath.getNodo(path, item);
                node.procesar(lis, 1, user);
                node.getPDF(document);
            }
            document.close();    
            } catch (DocumentException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    public static void crearListaDPDF(String name, ArrayList<ListaT> nodo, String path, String user) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+user+"/PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.h1));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            line.setLineColor(AZUL);
            document.add(line);
            for(ListaT item : nodo){
                ArrayList<String> lis= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,path)) , item.id);
                Nodo node= ControlPath.getNodo(path, item.id);
                node.procesar(lis, 1, user);
                node.getPDF(document);
            }
            document.close();    
            } catch (DocumentException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FirstPDF.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
