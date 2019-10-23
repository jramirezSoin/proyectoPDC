/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import datos.ListaT;
import datos.Nodo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xml.XmlParser;


public class FirstPDF {
    public static Font titleFont = new Font(Font.FontFamily.HELVETICA, 14,Font.BOLD);
    public static Font normalFont = new Font(Font.FontFamily.HELVETICA, 10,Font.NORMAL);
    public static Font subFont = new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD);
    public static Font normalBold = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD);

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public static void crearPDF(String filename, Nodo nodo){
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+"PDF/"+filename));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.titleFont));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
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
        PdfPCell c1 = new PdfPCell(new Phrase(name));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        return c1;
    }



    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void crearListaPDF(String name, ArrayList<ListaT> nodo) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+"PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.titleFont));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            document.add(line);
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Lista", FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(1);
            //float[] columnWidths = new float[]{30f, 10f, 10f, 20f};
            //table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Items"));
            for(ListaT item : nodo){
                if(item.visibilidad){
                table.addCell(item.valor);
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

    public static void crearListaPDF(String name, ArrayList<ListaT> nodo, ArrayList<Integer> checks) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+"PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.titleFont));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            document.add(line);
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Lista", FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(1);
            //float[] columnWidths = new float[]{30f, 10f, 10f, 20f};
            //table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Items"));
            for(ListaT item : nodo){
                if(item.visibilidad && checks.contains(item.id)){
                table.addCell(item.valor);
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

    public static void crearListaDPDF(String name, ArrayList<ListaT> nodo, ArrayList<Integer> checks, String path) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+"PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.titleFont));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            document.add(line);
            for(int item : checks){
                ArrayList<String> lis= XmlParser.LeerSeleccionado(new File(path) , item);
                Nodo node= ControlPath.getNodo(path, item);
                node.procesar(lis, 1);
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

    public static void crearListaDPDF(String name, ArrayList<ListaT> nodo, String path) {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(ControlPath.path+"PDF/"+name));
            document.open();
            document.add(new Paragraph("Reporte de Objetos PDC", FirstPDF.titleFont));
            document.add(new Paragraph(" "));
            LineSeparator line = new LineSeparator();
            document.add(line);
            for(ListaT item : nodo){
                ArrayList<String> lis= XmlParser.LeerSeleccionado(new File(path) , item.id);
                Nodo node= ControlPath.getNodo(path, item.id);
                node.procesar(lis, 1);
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
