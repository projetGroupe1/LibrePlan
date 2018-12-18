package projectPlan.groupe1;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class CriterionsPage extends GenericPage {

	
	
	@FindBy(how = How.XPATH, using="//td[contains(text(),'Créer')]")
	private WebElement creer_button;
	
	//@FindBy(how = How.XPATH, using="//td[contains(text(),'Créer Type de critère')]")
	//private WebElement welcome_text;
	
	
	@FindBy(how = How.XPATH, using="//input[@class='z-textbox'][1]")
	public WebElement criteria_name;
	
	@FindBy(how = How.XPATH, using="//input[@class='z-combobox-inp']")
	public WebElement combobox_list;
	
	@FindBy(how = How.XPATH, using="//textarea[@class='z-textbox']")
	public WebElement type_description;
	//="//td[@class='z-button-cm'][text()='Annuler']"
	@FindBy(how = How.XPATH, using="//span[contains(@class,'cancel-button')][descendant::td[text()='Annuler']]")
	public WebElement click_annuler;
	
	@FindBy(how = How.XPATH, using="//td[@class='z-button-cm'][text()='Enregistrer']")
	public WebElement click_enregistrer;
	
	@FindBy(how = How.XPATH, using="//td[@class='z-button-cm'][text()='Sauver et continuer']")
	public WebElement click_sauveretcontinuer;
	
	@FindBy(how = How.XPATH, using="//span[@title='Critère - Test bouton [Sauver et continuer]']/ancestor::tr//span[@title='Modifier']")
	public WebElement click_modifier;
	
	@FindBy(how = How.XPATH, using="//span[@title='Critère - Test bouton [Sauver et continuer]']")
	public WebElement click_testbutton_sauver_etcontinuer;
	
	@FindBy(how = How.XPATH, using="//td[contains(text(),'Modifier Type de critère:')]")
	public WebElement click_random;
	
	@FindBy(how = How.XPATH, using="//span[@title='Critère - Test bouton [Sauver et continuer]']/ancestor::tr//span[@title='Supprimer']")
	public WebElement click_supprimer;
	
	@FindBy(how = How.XPATH, using="//td[contains(text(),'OK')]")
	public WebElement click_ok;
	
	@FindBy(how = How.XPATH, using="//span[@class='z-messagebox-btn z-button']/following::td[@class='z-button-cm']")
	public WebElement click_pop_up_annuler;

	
	public CriterionsPage(WebDriver d) {
		super(d);
	}

	
	public void createCategory() {
		creer_button.click();
		
	}
	
	public void fillName(String name) {
		criteria_name.clear();
		criteria_name.sendKeys(name);
	}
	
	public void fillDescription(String text) {
		type_description.clear();
		type_description.sendKeys(text);
	}
	
  public void clickAnnuler() {
	 // System.out.println("fonction annuler");
	  click_annuler.click();
  }

  public void clickEnregistrer() {
	  click_enregistrer.click();
  }
  
  public void clickSaveAndContinue() {
	  click_sauveretcontinuer.click();
  }
	
  public void clickModifier() {
	  click_modifier.click();
  }
  
  public void clickTestBoutonSauverEtContinuer() {
	  click_testbutton_sauver_etcontinuer.click();
  }
  
  public void clickRandom() {
	  click_random.click();
  }
  
  public void clicSupprimer() {
	  click_supprimer.click();
  }

  public void clickOK() {
	  click_ok.click();
  }
  
  public void clickPopUpAnnuler() {
	  click_pop_up_annuler.click();
  }
  
	
}