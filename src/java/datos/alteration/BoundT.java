/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.ListaT;
import datos.Nodo;

/**
 *
 * @author Joseph Ramírez
 */
public class BoundT extends Nodo{
    private String tipo="";
    private ListaT leftOperand= new ListaT();
    private ListaT rightOperand= new ListaT();
    private String binaryOperator="";
    
}
