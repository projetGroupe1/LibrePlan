package projectPlan.groupe1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CalendarListPage extends GenericPage {

	//Title - Div w/ Liste de calendriers
	@FindBy (how = How.XPATH, using="//div[contains(text(),\"Liste de calendriers\")]")
	WebElement page_name;
	//Header "Nom"
	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Nom')]]")
	WebElement header_nom;
	//Header "Hérité de la date"
	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Hérité de la date')]]")
	WebElement header_herit_date;
	//Header "Héritages à jour"
	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Héritages à jour')]]")
	WebElement header_heritage_a_jour;
	//header "Opérations"
	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Opérations')]]")
	WebElement header_operations;
	//Button "Créer" to add a new calendar
	@FindBy (how = How.XPATH, using="//span[contains(@class,'create-button')][descendant::td[contains(text(),'Créer')]][1]")
	WebElement create_button;
	//Title "Créer Calendrier"
	@FindBy (how = How.XPATH, using="//td[text()=\"Créer Calendrier\"]")
	WebElement create_calendar_title;
	//Title "Modifier Calendrier"
	@FindBy (how = How.XPATH, using="//td[contains(text(),\"Modifier Calendrier\")]")
	WebElement modif_calendar_title;
	//Title Form "Données de calendrier"
	@FindBy (how = How.XPATH, using="//span[text()='Données de calendrier']")
	WebElement modif_calendar_form_title;
	//Table w/ the form for the new calendar
	@FindBy (how = How.XPATH, using="//div[contains(@class,\"calendar-data\")]")
	WebElement calendar_table;
	//Button "Enregistrer" for the form
	@FindBy (how = How.XPATH, using="//span[@class='save-button global-action z-button']")
	WebElement save_button;
	//Button "Enregistrer et Continuer" for the form
	@FindBy (how = How.XPATH, using="//span[@class='saveandcontinue-button global-action z-button']")
	WebElement save_and_continue_button;
	//Cancel Button
	@FindBy (how = How.XPATH, using="//span[@class='cancel-button global-action z-button']")
	WebElement cancel_button;
	//Input for Name
	@FindBy (how = How.XPATH, using="//span[text()='Nom']/parent::div/parent::td/following-sibling::*[descendant::input]/descendant::input")
	WebElement calendar_name_input;
	//Checkbox "Générer le code"
	@FindBy (how = How.XPATH, using="//label[text()='Générer le code']/preceding-sibling::input[@type='checkbox']")
	WebElement cb_generate_code;
	//Get the first calendar to check in the list if created or not
	@FindBy (how = How.XPATH, using="//span[text()='Calendrier - Test 1']")
	WebElement added_cal1;
	//Button "Créer une dérive" for Calendar - Test 1
	@FindBy (how = How.XPATH, using="//span[@title='Créer une dérive'][ancestor::tr[descendant::td[descendant::span[text()='Calendrier - Test 1']]]]")
	WebElement derive_cal1_button;
	//Button "Créer une copie" for Calendar - Test 1
	@FindBy (how = How.XPATH, using="//span[@title='Créer une copie'][ancestor::tr[descendant::td[descendant::span[text()='Calendrier - Test 1']]]]")
	WebElement copy_cal1_button;
	//Button "Modifier" for Calendar - Test 1
	@FindBy (how = How.XPATH, using="//span[@title='Modifier'][ancestor::tr[descendant::td[descendant::span[text()='Calendrier - Test 1']]]]")
	WebElement modif_cal1_button;
	//Value of "Type" in the form
	@FindBy (how = How.XPATH, using="//span[text()='Type']/parent::div/parent::td/following-sibling::td[descendant::span]/descendant::span")
	WebElement calendar_type_span;
	//Error message
	@FindBy (how = How.XPATH, using="//div[contains(@class, 'message_WARNING')][descendant::span[contains(text(), 'existe déjà')]]")
	WebElement error_msg;
	//List of all the info messages
	@FindBy (how = How.XPATH, using="//div[contains(@class, 'message_INFO')]/span")
	List<WebElement> list_msg_info;
	//<tr> in the list of the derived calendar
	@FindBy(how = How.XPATH, using="//tr[descendant::span[contains(@class,'z-dottree-root-open')]][descendant::span[contains(text(),'Calendrier - Test 1')]]/following-sibling::tr[descendant::span[contains(text(),'Calendrier - Test Calendrier Dérivé')]][descendant::span[contains(@class,'z-dottree-last')]]")
	WebElement line_deriv_cal_created;
	//[-] button in the list
	@FindBy(how = How.XPATH, using="//span[text()='Calendrier - Test 1']/preceding-sibling::span[contains(@class,'z-dottree-root-open')]")
	WebElement minus_cal_created_button;
	//<tr> of the second calendar
	@FindBy(how = How.XPATH, using="//tr[descendant::span[contains(text(),'Calendrier - Test 2')]][not(contains(@class,'z-dottree-last'))]")
	WebElement line_cal2_created;
	//Select of the exception type
	@FindBy(how = How.XPATH, using="//span[text()=\"Type d'exception\"]/ancestor::td[following-sibling::td[descendant::input[@type='text']]]/following-sibling::td/descendant::input")
	WebElement select_type_exception;
	//New exception button
	@FindBy(how = How.XPATH, using="//span[contains(@class,'z-button')][descendant::*[text()='Créer une exception']]")
	WebElement new_exception_btn; 
	//Errorbox no exception selected
	@FindBy(how = How.XPATH, using="//div[@title='Allez sur le mauvais champ'][contains(@class,'z-arrow-l')][descendant::div[contains(@class,'z-errbox-close')]][descendant::div[text()=\"Merci de choisir un type d'exception\"]]")
	WebElement error_box_exception;
	//Errorbox no ending date for the exception
	@FindBy(how = How.XPATH, using="//div[@title='Allez sur le mauvais champ'][contains(@class,'z-arrow-l')][descendant::div[contains(@class,'z-errbox-close')]][descendant::div[text()=\"Merci de choisir une date de fin pour l'exception\"]]")
	WebElement error_box_date_exception;
	//Button for the exception select
	@FindBy(how = How.XPATH, using="//i[@class='z-combobox-btn']")
	WebElement combobox_exception_btn;
	//Input for the starting date exception
	@FindBy(how = How.XPATH, using="//span[text()='Période']/ancestor::tr[1]/descendant::input[@class='z-datebox-inp'][1]")
	WebElement input_start_date_excep;
	//Input for the ending date exception
	@FindBy(how = How.XPATH, using="//span[text()='Période']/ancestor::tr[1]/descendant::input[@class='z-datebox-inp'][2]")
	WebElement input_end_date_excep;
	//Button for the ending date exception to trigger the div
	@FindBy(how = How.XPATH, using="//span[text()='Période']/ancestor::tr[1]/descendant::i[@class='z-datebox-btn'][2]")
	WebElement btn_end_date_excep;
	//List of all the td from the exception table
	@FindBy(how = How.XPATH, using="//fieldset[descendant::span[text()='Liste des exceptions']]/descendant::div[@class='z-listbox-body']/descendant::tr[contains(@class,'z-listitem')]/td")
	List<WebElement> list_td_exception_table;
	//List of all the values of the day properties (below the calendar on the left)
	@FindBy (how = How.XPATH, using="//div[@class='day-details z-grid']/div[@class='z-grid-body']/descendant::tr[contains(@class,'day-details')]/td[2]")
	List<WebElement> list_value_td_day_properties;
	//List of all the normal effort input
	@FindBy (how = How.XPATH, using="//span[text()='Effort normal:']/ancestor::td[1]/following-sibling::td[descendant::table][1]/descendant::input")
	List<WebElement> list_norm_eff_inp;
	//List of all the additional effort input
	@FindBy (how = How.XPATH, using="//span[text()='Effort en heures supplémentaires:']/ancestor::td[1]/following-sibling::td[descendant::table][1]/descendant::input[@type='text']")
	List<WebElement> list_sup_eff_inp;
	//Button to update the exception
	@FindBy (how = How.XPATH, using="//td[contains(text(),\"Mettre à jour l'exception\")]/ancestor::span")
	WebElement update_excep_btn;
	//Input to get the generated code
	@FindBy (how = How.XPATH, using="//span[text()='Code']/ancestor::td/following-sibling::td/descendant::input[@type='text'][@disabled]")
	WebElement generated_code_inp;
	
	//Fill the form for the normal and supp effort
	//Args : - @norm1 : normal effort (hours)
	//		 - @norm2 : normal effort (minutes)
	//		 - @sup1 : Additional effort (hours)
	//		 - @sup2 : Additional effort (minutes)
	public void fillEffortForm(String norm1, String norm2, String sup1, String sup2)
	{
		list_norm_eff_inp.get(0).clear();
		list_norm_eff_inp.get(1).clear();
		list_norm_eff_inp.get(0).sendKeys(norm1);
		list_norm_eff_inp.get(1).sendKeys(norm2);
		list_sup_eff_inp.get(0).clear();
		list_sup_eff_inp.get(1).clear();
		list_sup_eff_inp.get(0).sendKeys(sup1);
		list_sup_eff_inp.get(1).sendKeys(sup2);
		update_excep_btn.click();
	}
	
	//Fill form with name + check if cb_generate_code is checked + click on sent button
	//Args : - calendar_name : String to put in the input name
	//		 - button : WebElement button to click at the end of the fct ("Enregistrer", "Enregistrer et continuer", "Annuler")
	public void fillNameForm(String calendar_name, WebElement button) {
		calendar_name_input.clear();
		calendar_name_input.sendKeys(calendar_name);
		if(!cb_generate_code.getAttribute("value").equals("on")) 
		{
			cb_generate_code.click();
		}
		button.click();
	}
	//Check if any info message equals @msg
	//return boolean
	public boolean checkMsgInfo(String msg) {
		for(WebElement elem : list_msg_info)
		{
			if(elem.getText().equals(msg)){
				return true;
			}
		}
		return false;
	}

	//Select sent exception and click the new exception button
	//Args : - exception_name : exception to select
	public void checkSelectModifAndAddException(String exception_name) {
		
		if(exception_name.equals("")||exception_name.equals("NO_EXCEPTION"))
		{
			combobox_exception_btn.click();
			driver.findElement(By.xpath("//td[@class='z-comboitem-text'][text()='NO_EXCEPTION']")).click();
		}
		else {
			combobox_exception_btn.click();
			driver.findElement(By.xpath("//td[@class='z-comboitem-text'][text()='"+exception_name+"']")).click();
			
		}
		new_exception_btn.click();
	}
	
	//Constructor
	public CalendarListPage(WebDriver d) {
		super(d);
	}	


}
