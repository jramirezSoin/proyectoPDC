/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class ControlPath {

    public static String zoneModelsPath = "";
    public static String impactCategoriesPath = "";
    public static String triggerSpecPath = "";
    public static String balanceElementPath = "";
    public static String rumConfigPath = "";
    public static String rolloverPath = "";
    public static String timeModelsPath = "";
    public static String bundledPath = "";
    public static String packagePath = "";
    public static String attributeSpecMapPath = "";
    public static String chargeOfferingPath = "";
    public static String holidayPath = "";
    public static String alterationOfferingPath = "";
    public static String eventAttributeSpecPath = "";
    public static String chargeRatePath = "";
    public static String glidPath = "";
    public static String uscMapPath = "";
    public static String chargeSelectorSpecPath = "";
    public static String constants = "";
    public static String path = "";
    
    public static String zoneModelsPointer = "";
    public static String zoneModelsView = "";
    public static String zoneModelForm = "";
    public static String zoneItemForm = "";
    public static String zoneModelsClick = "";
    
    public static String impactCategoriesPointer = "";
    public static String impactCategoriesView = "";
    public static String impactCategoriesForm = "";
    public static String impactCategoriesClick = "";
    
    public static String triggerSpecPointer = "";
    public static String triggerSpecClick = "";
    public static String triggerSpecView = "";
    public static String triggerSpecForm = "";
    public static String expressionForm = "";

    public static String balanceElementPointer = "";
    public static String balanceElementView = "";
    public static String balanceElementForm = "";
    public static String roundingRuleForm = "";
    public static String balanceElementClick = "";
    
    public static String rumConfigPointer = "";
    public static String rumConfigView = "";
    public static String rumConfigForm = "";
    public static String rumConfigClick = "";

    public static String rolloverPointer = "";
    public static String rolloverView = "";
    public static String rolloverForm = "";
    public static String rolloverClick = "";

    public static String timeModelsPointer = "";
    public static String timeModelsView = "";
    public static String timeModelForm = "";
    public static String timeModelTagForm = "";
    public static String timeModelsClick = "";

    public static String bundledPointer = "";
    public static String bundledView = "";
    public static String bundledForm = "";
    public static String bundledItemForm = "";
    public static String bundledClick = "";
    
    public static String packagePointer = "";
    public static String packageView = "";
    public static String packageForm = "";
    public static String packageItemForm = "";
    public static String packageBalanceForm = "";
    public static String packageClick = "";

    public static String listView = "";

    public static String attributeSpecMapPointer = "";
    public static String attributeSpecMapView = "";
    public static String attributeSpecMapForm = "";
    public static String attributeSpecMapItemForm = "";
    public static String attributeSpecMapClick = "";

    public static String chargeOfferingPointer = "";
    public static String chargeOfferingView = "";
    public static String chargeOfferingForm = "";
    public static String chargeOfferingClick = "";
    public static String chargeEventForm = "";

    public static String alterationOfferingPointer = "";
    public static String alterationOfferingView = "";
    public static String alterationOfferingForm = "";
    public static String alterationOfferingClick = "";

    public static String holidayPointer = "";
    public static String holidayView = "";
    public static String holidayForm = "";
    public static String holidayClick = "";
    public static String holidayItemForm = "";

    public static String eventAttributeSpecPointer = "";
    public static String eventAttributeSpecClick = "";

    public static String chargeRatePointer = "";
    public static String chargeRateView = "";
    public static String chargeRateForm = "";
    public static String chargeRateClick = "";
    public static String crpRelDateForm = "";
    public static String zoneRateForm = "";
    public static String crpCompositeView = "";
    public static String crpCompositeForm = "";
    public static String priceTierPeriodForm = "";
    public static String chargeForm = "";

    public static String glidClick = "";
    public static String glidPointer = "";

    public static String uscMapPointer = "";
    public static String uscMapPointer2 = "";
    public static String uscMapView = "";
    public static String uscMapForm = "";
    public static String uscMapClick = "";

    public static String chargeSelectorSpecPointer = "";
    public static String chargeSelectorSpecClick = "";
    public static String chargeSelectorSpecView = "";
    public static String chargeSelectorSpecForm = "";
    
    
    public static void LoadParameters(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/Infranet.properties");
            try {
                PropertyResourceBundle properties= new PropertyResourceBundle(fis);
                path = properties.getString("path").trim();
                zoneModelsPath = path + properties.getString("zoneModelsPath").trim();
                impactCategoriesPath = path + properties.getString("impactCategoriesPath").trim();
                triggerSpecPath = path + properties.getString("triggerSpecPath").trim();
                balanceElementPath = path + properties.getString("balanceElementPath").trim();
                rumConfigPath = path + properties.getString("rumConfigPath").trim();
                rolloverPath = path + properties.getString("rolloverPath").trim();
                timeModelsPath = path + properties.getString("timeModelsPath").trim();
                bundledPath = path + properties.getString("bundledPath").trim();
                packagePath = path + properties.getString("packagePath").trim();
                attributeSpecMapPath = path + properties.getString("attributeSpecMapPath").trim();
                chargeOfferingPath = path + properties.getString("chargeOfferingPath").trim();
                holidayPath = path + properties.getString("holidayPath").trim();
                alterationOfferingPath = path + properties.getString("alterationOfferingPath").trim();
                eventAttributeSpecPath = path + properties.getString("eventAttributeSpecPath").trim();
                chargeRatePath = path + properties.getString("chargeRatePath").trim();
                glidPath = path + properties.getString("glidPath").trim();
                uscMapPath = path + properties.getString("uscMapPath").trim();
                chargeSelectorSpecPath = path + properties.getString("chargeSelectorSpecPath").trim();
                constants = path + properties.getString("constants").trim();
                zoneModelsPointer= properties.getString("zoneModelsPointer");
                zoneModelsView= properties.getString("zoneModelsView");
                zoneModelForm= properties.getString("zoneModelForm");
                zoneItemForm= properties.getString("zoneItemForm");
                zoneModelsClick= properties.getString("zoneModelsClick");

                impactCategoriesPointer= properties.getString("impactCategoriesPointer");
                impactCategoriesView= properties.getString("impactCategoriesView");
                impactCategoriesForm= properties.getString("impactCategoriesForm");
                impactCategoriesClick= properties.getString("impactCategoriesClick");

                triggerSpecPointer= properties.getString("triggerSpecPointer");
                triggerSpecClick= properties.getString("triggerSpecClick");
                triggerSpecView= properties.getString("triggerSpecView");
                triggerSpecForm= properties.getString("triggerSpecForm");
                expressionForm= properties.getString("expressionForm");

                balanceElementPointer= properties.getString("balanceElementPointer");
                balanceElementView= properties.getString("balanceElementView");
                balanceElementForm= properties.getString("balanceElementForm");
                roundingRuleForm= properties.getString("roundingRuleForm");
                balanceElementClick= properties.getString("balanceElementClick");

                rumConfigPointer= properties.getString("rumConfigPointer");
                rumConfigView= properties.getString("rumConfigView");
                rumConfigForm= properties.getString("rumConfigForm");
                rumConfigClick= properties.getString("rumConfigClick");

                rolloverPointer= properties.getString("rolloverPointer");
                rolloverView= properties.getString("rolloverView");
                rolloverForm= properties.getString("rolloverForm");
                rolloverClick= properties.getString("rolloverClick");

                timeModelsPointer= properties.getString("timeModelsPointer");
                timeModelsView= properties.getString("timeModelsView");
                timeModelForm= properties.getString("timeModelForm");
                timeModelTagForm= properties.getString("timeModelTagForm");
                timeModelsClick= properties.getString("timeModelsClick");

                bundledPointer= properties.getString("bundledPointer");
                bundledView= properties.getString("bundledView");
                bundledForm= properties.getString("bundledForm");
                bundledItemForm= properties.getString("bundledItemForm");
                bundledClick= properties.getString("bundledClick");

                packagePointer= properties.getString("packagePointer");
                packageView= properties.getString("packageView");
                packageForm= properties.getString("packageForm");
                packageItemForm= properties.getString("packageItemForm");
                packageBalanceForm= properties.getString("packageBalanceForm");
                packageClick= properties.getString("packageClick");

                listView= properties.getString("listView");

                attributeSpecMapPointer= properties.getString("attributeSpecMapPointer");
                attributeSpecMapView= properties.getString("attributeSpecMapView");
                attributeSpecMapForm= properties.getString("attributeSpecMapForm");
                attributeSpecMapItemForm= properties.getString("attributeSpecMapItemForm");
                attributeSpecMapClick= properties.getString("attributeSpecMapClick");

                chargeOfferingPointer= properties.getString("chargeOfferingPointer");
                chargeOfferingView= properties.getString("chargeOfferingView");
                chargeOfferingForm= properties.getString("chargeOfferingForm");
                chargeOfferingClick= properties.getString("chargeOfferingClick");
                chargeEventForm= properties.getString("chargeEventForm");

                alterationOfferingPointer= properties.getString("alterationOfferingPointer");
                alterationOfferingView= properties.getString("alterationOfferingView");
                alterationOfferingForm= properties.getString("alterationOfferingForm");
                alterationOfferingClick= properties.getString("alterationOfferingClick");

                holidayPointer= properties.getString("holidayPointer");
                holidayView= properties.getString("holidayView");
                holidayForm= properties.getString("holidayForm");
                holidayClick= properties.getString("holidayClick");
                holidayItemForm= properties.getString("holidayItemForm");

                eventAttributeSpecPointer= properties.getString("eventAttributeSpecPointer");
                eventAttributeSpecClick= properties.getString("eventAttributeSpecClick");

                chargeRatePointer= properties.getString("chargeRatePointer");
                chargeRateView= properties.getString("chargeRateView");
                chargeRateForm= properties.getString("chargeRateForm");
                chargeRateClick= properties.getString("chargeRateClick");
                crpRelDateForm= properties.getString("crpRelDateForm");
                zoneRateForm= properties.getString("zoneRateForm");
                crpCompositeView= properties.getString("crpCompositeView");
                crpCompositeForm= properties.getString("crpCompositeForm");
                priceTierPeriodForm= properties.getString("priceTierPeriodForm");
                chargeForm= properties.getString("chargeForm");

                glidClick= properties.getString("glidClick");
                glidPointer= properties.getString("glidPointer");

                uscMapPointer= properties.getString("uscMapPointer");
                uscMapPointer2= properties.getString("uscMapPointer2");
                uscMapView= properties.getString("uscMapView");
                uscMapForm= properties.getString("uscMapForm");
                uscMapClick= properties.getString("uscMapClick");

                chargeSelectorSpecPointer= properties.getString("chargeSelectorSpecPointer");
                chargeSelectorSpecClick= properties.getString("chargeSelectorSpecClick");
                chargeSelectorSpecView= properties.getString("chargeSelectorSpecView");
                chargeSelectorSpecForm= properties.getString("chargeSelectorSpecForm");
            } finally {
                fis.close();
            }
        } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
