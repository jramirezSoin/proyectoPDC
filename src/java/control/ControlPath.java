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
    
    
    public static void LoadParameters(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/Infranet.properties");
            try {
                PropertyResourceBundle properties= new PropertyResourceBundle(fis);
                path = properties.getString("path").trim();
                zoneModelsPath = path + properties.getString("zoneModelsPath").trim();
                System.out.println(zoneModelsPath);
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
            } finally {
                fis.close();
            }
        } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String zoneModelsPointer = "standardZoneModel";
    public static String zoneModelsView = "/views/zoneModelView.jsp";
    public static String zoneModelForm = "/views/zoneModelForm.jsp";
    public static String zoneItemForm = "/views/zoneItemForm.jsp";
    public static String zoneModelsClick = "/zoneModels";
    
    public static String impactCategoriesPointer = "zoneResultConfiguration";
    public static String impactCategoriesView = "/views/impactCategoryView.jsp";
    public static String impactCategoriesForm = "/views/impactCategoryForm.jsp";
    public static String impactCategoriesClick = "/impactCategories";
    
    public static String triggerSpecPointer = "triggerSpec";
    public static String triggerSpecClick = "/triggerSpec";
    public static String triggerSpecView = "/views/triggerSpecView.jsp";
    public static String triggerSpecForm = "/views/triggerSpecForm.jsp";
    public static String expressionForm = "/views/expressionForm.jsp";

    public static String balanceElementPointer = "balanceElements";
    public static String balanceElementView = "/views/balanceElementView.jsp";
    public static String balanceElementForm = "/views/balanceElementForm.jsp";
    public static String roundingRuleForm = "/views/roundingRuleForm.jsp";
    public static String balanceElementClick = "/balanceElement";
    
    public static String rumConfigPointer = "rumConfigurations";
    public static String rumConfigView = "/views/rumConfigView.jsp";
    public static String rumConfigForm = "/views/rumConfigForm.jsp";
    public static String rumConfigClick = "/rumConfig";

    public static String rolloverPointer = "rolloverRatePlan";
    public static String rolloverView = "/views/rolloverView.jsp";
    public static String rolloverForm = "/views/rolloverForm.jsp";
    public static String rolloverClick = "/rollover";

    public static String timeModelsPointer = "timeModel";
    public static String timeModelsView = "/views/timeModelView.jsp";
    public static String timeModelForm = "/views/timeModelForm.jsp";
    public static String timeModelTagForm = "/views/timeModelTagForm.jsp";
    public static String timeModelsClick = "/timeModel";

    public static String bundledPointer = "bundledProductOffering";
    public static String bundledView = "/views/bundledView.jsp";
    public static String bundledForm = "/views/bundledForm.jsp";
    public static String bundledItemForm = "/views/bundledItemForm.jsp";
    public static String bundledClick = "/bundled";
    
    public static String packagePointer = "package";
    public static String packageView = "/views/packageView.jsp";
    public static String packageForm = "/views/packageForm.jsp";
    public static String packageItemForm = "/views/packageItemForm.jsp";
    public static String packageBalanceForm = "/views/packageBalanceForm.jsp";
    public static String packageClick = "/package";

    public static String listView = "/views/list.jsp";

    public static String attributeSpecMapPointer = "attributeSpecMaps";
    public static String attributeSpecMapView = "/views/attributeSpecMapView.jsp";
    public static String attributeSpecMapForm = "/views/attributeSpecMapForm.jsp";
    public static String attributeSpecMapItemForm = "/views/attributeSpecMapItemForm.jsp";
    public static String attributeSpecMapClick = "/attributeSpecMap";

    public static String chargeOfferingPointer = "chargeOffering";
    public static String chargeOfferingView = "/views/chargeOfferingView.jsp";
    public static String chargeOfferingForm = "/views/chargeOfferingForm.jsp";
    public static String chargeOfferingClick = "/charge";
    public static String chargeEventForm = "/views/chargeEventForm.jsp";

    public static String alterationOfferingPointer = "alterationOffering";
    public static String alterationOfferingView = "/views/alterationOfferingView.jsp";
    public static String alterationOfferingForm = "/views/alterationOfferingForm.jsp";
    public static String alterationOfferingClick = "/alterationOffering";

    public static String holidayPointer = "holidayCalendar";
    public static String holidayView = "/views/holidayView.jsp";
    public static String holidayForm = "/views/holidayForm.jsp";
    public static String holidayClick = "/holiday";
    public static String holidayItemForm = "/views/holidayItemForm.jsp";

    public static String eventAttributeSpecPointer = "eventAttributeSpec";
    public static String eventAttributeSpecClick = "/event";

    public static String chargeRatePointer = "chargeRatePlan";
    public static String chargeRateView = "/views/chargeRatePlanView.jsp";
    public static String chargeRateForm = "/views/chargeRateForm.jsp";
    public static String chargeRateClick = "/chargeRate";
    public static String crpRelDateForm = "/views/crpRelDateForm.jsp";
    public static String zoneRateForm = "/views/zoneRateForm.jsp";
    public static String crpCompositeView = "/views/crpCompositeView.jsp";
    public static String crpCompositeForm = "/views/crpCompositeForm.jsp";
    public static String priceTierPeriodForm = "/views/priceTierPeriodForm.jsp";
    public static String chargeForm = "/views/chargeForm.jsp";

    public static String glidClick = "/glid";
    public static String glidPointer = "glid";

    public static String uscMapPointer = "uscSelector";
    public static String uscMapPointer2 = "validityPeriod";
    public static String uscMapView = "/views/uscMapView.jsp";
    public static String uscMapForm = "/views/uscMapForm.jsp";
    public static String uscMapClick = "/uscMap";

    public static String chargeSelectorSpecPointer = "chargeSelectorSpec";
    public static String chargeSelectorSpecClick = "/chargeSelectorSpec";
    public static String chargeSelectorSpecView = "/views/chargeSelectorSpecView.jsp";
    public static String chargeSelectorSpecForm = "/views/chargeSelectorSpecForm.jsp";

}
