/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.AlterationOfferingT;
import datos.BalanceElementT;
import datos.BundledT;
import datos.ChargeOfferingT;
import datos.ChargeSelectorSpecT;
import datos.GenericSelectorT;
import datos.HolidayT;
import datos.ImpactCategoryT;
import datos.Nodo;
import datos.PackageT;
import datos.RolloverT;
import datos.RumT;
import datos.TimeModelT;
import datos.TriggerSpecT;
import datos.ZoneModelT;
import datos.alteration.AlterationRatePlanT;
import datos.ratePlan.ChargeRatePlanT;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class ControlPath {
    //PATHS
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
    public static String genericSelectorPath = "";
    public static String constants = "";
    public static String path = "";
    
    //OTRO
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
    public static String alterationEventForm = "";

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
    
    public static String alterationRatePath= "";
    public static String alterationRatePointer= "";
    public static String alterationRateClick= "";
    public static String alterationRateView= "";
    public static String alterationRateForm= "";
    public static String arpCompositeView="";
    
    public static String alterationSelectorClick= "";
    public static String alterationSelectorPointer= "";
    public static String alterationSelectorPath= "";

    public static String chargeSelectorSpecPointer = "";
    public static String chargeSelectorSpecClick = "";
    public static String chargeSelectorSpecView = "";
    public static String chargeSelectorSpecForm = "";
    
    public static String genericSelectorClick = "";
    public static String genericSelectorPointer = "";
    public static String genericSelectorView = "";
    public static String genericSelectorForm = "";
    public static String ruleForm = "";
    public static String modelDataForm = "";
    
    public static String customRuleClick = "";
    public static String customRulePath = "";
    public static String customRulePointer = "";
    public static String changes= "";
    public static String users="C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/users.txt";
    public static String mainView= "";
    
    public static String pinDirectory= "";
    public static String pinImportExport= "";
    public static String pinPort= "";
    public static String pinHost= "";
    public static String arpCompositeForm="";
    public static String boundForm="";
    public static String alterationForm="";
    public static String errorMessage="";
    
    
    
    public static void LoadParameters(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/Joseph Ramírez/Documents/EXPORT_PDC/Infranet.properties");
            try {
                PropertyResourceBundle properties= new PropertyResourceBundle(fis);
                
                pinDirectory = properties.getString("pinDirectory");
                pinImportExport = properties.getString("pinImportExport");
                pinPort= properties.getString("pinPort");
                pinHost= properties.getString("pinHost");
                path = properties.getString("path").trim();
                zoneModelsPath =  properties.getString("zoneModelsPath").trim();
                impactCategoriesPath =  properties.getString("impactCategoriesPath").trim();
                triggerSpecPath =  properties.getString("triggerSpecPath").trim();
                balanceElementPath =  properties.getString("balanceElementPath").trim();
                rumConfigPath =  properties.getString("rumConfigPath").trim();
                rolloverPath =  properties.getString("rolloverPath").trim();
                timeModelsPath =  properties.getString("timeModelsPath").trim();
                bundledPath =  properties.getString("bundledPath").trim();
                packagePath =  properties.getString("packagePath").trim();
                attributeSpecMapPath =  properties.getString("attributeSpecMapPath").trim();
                chargeOfferingPath =  properties.getString("chargeOfferingPath").trim();
                holidayPath =  properties.getString("holidayPath").trim();
                alterationOfferingPath =  properties.getString("alterationOfferingPath").trim();
                eventAttributeSpecPath =  properties.getString("eventAttributeSpecPath").trim();
                chargeRatePath =  properties.getString("chargeRatePath").trim();
                alterationRatePath = properties.getString("alterationRatePath").trim();
                alterationSelectorPath = properties.getString("alterationSelectorPath").trim();
                uscMapPath =  properties.getString("uscMapPath").trim();
                chargeSelectorSpecPath =  properties.getString("chargeSelectorSpecPath").trim();
                genericSelectorPath =  properties.getString("genericSelectorPath").trim();
                customRulePath =  properties.getString("customRulePath").trim();
                glidPath =  properties.getString("glidPath").trim();
                changes =  properties.getString("changes").trim();
                
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
                alterationEventForm= properties.getString("alterationEventForm");

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
                
                alterationRatePointer= properties.getString("alterationRatePointer");
                alterationRateView= properties.getString("alterationRateView");
                alterationRateForm= properties.getString("alterationRateForm");
                alterationRateClick= properties.getString("alterationRateClick");
                arpCompositeView= properties.getString("arpCompositeView");
                arpCompositeForm=properties.getString("arpCompositeForm");
                boundForm=properties.getString("boundForm");
                alterationForm=properties.getString("alterationForm");
                
                alterationSelectorClick= properties.getString("alterationSelectorClick");
                alterationSelectorPointer= properties.getString("alterationSelectorPointer");
                

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
                
                genericSelectorPointer= properties.getString("genericSelectorPointer");
                genericSelectorClick= properties.getString("genericSelectorClick");
                genericSelectorView = properties.getString("genericSelectorView");
                genericSelectorForm = properties.getString("genericSelectorForm");
                ruleForm = properties.getString("ruleForm");
                modelDataForm = properties.getString("modelDataForm");
                
                customRuleClick = properties.getString("customRuleClick");
                customRulePointer = properties.getString("customRulePointer");
                
                mainView= properties.getString("mainView");
                errorMessage= properties.getString("errorMessage");
                
            } finally {
                fis.close();
            }
        } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ControlPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Nodo getNodo(String path, int item) {
        if(path.equals(zoneModelsPath)){return new ZoneModelT(item);}
        else if(path.equals(impactCategoriesPath)){return new ImpactCategoryT(item);}
        else if(path.equals(triggerSpecPath)){return new TriggerSpecT(item);}
        else if(path.equals(balanceElementPath)){return new BalanceElementT(item);}
        else if(path.equals(rumConfigPath)){return new RumT(item);}
        else if(path.equals(rolloverPath)){return new RolloverT(item);}
        else if(path.equals(timeModelsPath)){return new TimeModelT(item);}
        else if(path.equals(bundledPath)){return new BundledT(item);}
        else if(path.equals(packagePath)){return new PackageT(item);}
        else if(path.equals(chargeOfferingPath)){return new ChargeOfferingT(item);}
        else if(path.equals(holidayPath)){return new HolidayT(item);}
        else if(path.equals(chargeRatePath)){return new ChargeRatePlanT(item);}
        else if(path.equals(chargeSelectorSpecPath)){return new ChargeSelectorSpecT(item);}
        else if(path.equals(genericSelectorPath)){return new GenericSelectorT(item);}
        else if(path.equals(alterationRatePath)){return new AlterationRatePlanT(item);}
        else if(path.equals(alterationOfferingPath)){return new AlterationOfferingT(item);}
        
        else return null;
    }
    
    public static String getPath(String user, String nodo){
        return path+user+"/"+nodo;
    }

}
