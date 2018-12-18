package projectPlan.groupe1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancementTypeList extends GenericPage {
	//webElements to check and click to create
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'z-window-embedded-header']")
	WebElement titleTab;
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'z-column-cnt'][text() = 'Nom']")
	WebElement rowName;
	
	@FindBy(how = How.XPATH, using ="//div[@class = 'z-column-cnt'][text() = 'Activé']")
	WebElement rowActivated;
	
	@FindBy(how = How.XPATH, using ="//div[@class = 'z-column-cnt'][text() = 'Prédéfini']")
	WebElement rowPreset; 

	@FindBy(how = How.XPATH, using ="//div[@class = 'z-column-cnt'][text() = 'Opérations']")
	WebElement rowOperations;
	
	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement create;
	
	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement messageTypeAdvancement;
	
	public AdvancementTypeList(WebDriver d) {
		super(d);

	}
	
	//to get Strings
	
	public String getTItle() {
		String title = 	titleTab.getText();
		return title;
	}

	public String getName() {
		String name = 	rowName.getText();
		return name;
	}

	public String getActif() {
		String actif = 	rowActivated.getText();
		return actif;
	}
	public String getPreset() {
		String preset = 	rowPreset.getText();
		return preset;
	}
	public String getOperations() {
		String operations = rowOperations.getText();
		return operations;
	}
	
	public String getCreateButton() {
		String buttonCreate = create.getText();
		return buttonCreate;
	}
	
	public String getmessageTypeAdvancement() {
		String message = messageTypeAdvancement.getText();
		return message;
	}

	// To click on create
	public AdvancementTypeCreation createType() {
		create.click();
	return PageFactory.initElements(driver, AdvancementTypeCreation.class);
	}
	

}
