/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;

/**
 *
 * @author Joseph Ramírez
 */
public interface ResultI{
    public void getRumCurrency(String rum,String currency, String user);
    public void getPDF(Element element);
}
