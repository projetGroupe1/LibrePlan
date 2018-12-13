package projectPlan.groupe1;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CalendarListPage extends GenericPage {

	@FindBy (how = How.XPATH, using="//div[contains(text(),\"Liste de calendriers\")]")
	WebElement page_name;

	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Nom')]]")
	WebElement header_nom;

	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Hérité de la date')]]")
	WebElement header_herit_date;

	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Héritages à jour')]]")
	WebElement header_heritage_a_jour;

	@FindBy (how = How.XPATH, using="//th[descendant::*[contains(text(),'Opérations')]]")
	WebElement header_operations;

	@FindBy (how = How.XPATH, using="//span[contains(@class,'create-button')][descendant::td[contains(text(),'Créer')]][1]")
	WebElement create_button;

	@FindBy (how = How.XPATH, using="//td[text()=\"Créer Calendrier\"]")
	WebElement create_calendar_title;

	@FindBy (how = How.XPATH, using="//div[contains(@class,\"calendar-data\")]")
	WebElement calendar_table;

	@FindBy (how = How.XPATH, using="//span[@class='save-button global-action z-button']")
	WebElement save_button;

	@FindBy (how = How.XPATH, using="//span[@class='saveandcontinue-button global-action z-button']")
	WebElement save_and_continue_button;

	@FindBy (how = How.XPATH, using="//span[@class='cancel-button global-action z-button']")
	WebElement cancel_button;
	//OK car première lettre toujours en MAJ
	@FindBy (how = How.XPATH, using="//input[@type='text'][contains(@id,'Q45')]")
	WebElement calendar_name_input;

	@FindBy (how = How.XPATH, using="//input[@type='checkbox'][contains(@id,'d5-real')]")
	WebElement cb_generate_code;

	//Rempli le nom dans le champ et vérifie que la cb soit cochée
	public void fillNameForm(String calendar_name) {
		calendar_name_input.clear();
		calendar_name_input.sendKeys(calendar_name);
		if(!cb_generate_code.getAttribute("value").equals("on")) {
			cb_generate_code.click();
		}
	}
	
	//Constructor
	public CalendarListPage(WebDriver d) {
		super(d);
	}	


}
