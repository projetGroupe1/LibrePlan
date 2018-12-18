package projectPlan.groupe1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancementTypeList extends GenericPage {
	
	// webElements to check and click to create
	@FindBy(how = How.XPATH, using = "//div[@class = 'z-window-embedded-header']")
	WebElement titleTab;

	@FindBy(how = How.XPATH, using = "//div[@class = 'z-column-cnt'][text() = 'Nom']")
	WebElement rowName;

	@FindBy(how = How.XPATH, using = "//div[@class = 'z-column-cnt'][text() = 'Activé']")
	WebElement rowActivated;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'z8-real')]")
	WebElement rowActivatedDisabled;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'r8-real')]")
	WebElement rowActivatedDisabledTest2;
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'z-column-cnt'][text() = 'Prédéfini']")
	WebElement rowPreset;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'8-real')]")
	WebElement rowPresetDisabled;

	@FindBy(how = How.XPATH, using = "//div[@class = 'z-column-cnt'][text() = 'Opérations']")
	WebElement rowOperations;

	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement create;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'message_INFO')]/span")
	List<WebElement> messages;
	
	
	
	//tableau to checks rows 
	@FindBy(how = How.XPATH, using = "//tr[contains(@id,'m4-bdfaker')]/ancestor::table/tbody[2]/tr/td")
	List<WebElement> table_data;
	
	
	public AdvancementTypeList(WebDriver d) {
		super(d);

	}
		
	// to get Strings
	public String getTitle() {
		String title = titleTab.getText();
		return title;
	}
	
	public String getName() {
		String name = rowName.getText();
		return name;
	}

	public String getActif() {
		String actif = rowActivated.getText();
		return actif;
	}

	public String getPreset() {
		String preset = rowPreset.getText();
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
	
	//loop to find the right message after insert
	public String getmessages(String message) {
		for (WebElement element : messages) {
			if (element.getText().equals(message)) {
				String msg = element.getText();
				return msg;
			}
		}
		return "Element not found ";
	}
	
	//WebElements checkboxs for check diasabled in test page
		public WebElement getCheckActivatedDisabled() {
		return rowActivatedDisabled;
	}
		
		public WebElement getCheckActivatedDisabledTest2() {
			return rowActivatedDisabledTest2;
		}
	
	public WebElement getCheckPredefiniDisabled() {
		return rowPresetDisabled;
	}
	

	// method to click on create
	public AdvancementTypeCreation createType() {
		create.click();
		return PageFactory.initElements(driver, AdvancementTypeCreation.class);
	}
	
	//methods to check the tab after a creation (one method for one test)
	public String findNameInTab(String test) {
		for (WebElement element : table_data ) {
			if (element.getText().equals(test)){
				String name = element.getText();
				return name;
			}
				}
		return "Element not found ";
	}
	
	public WebElement findActivateInTab(String test) {
		for (WebElement element : table_data ) {
			if (element.getText().equals(test)){
				WebElement elt =element.findElement(By.xpath("following-sibling::td"));	
				return elt;
			}
				}
		return null;
	
	}
	
	public WebElement findPresetInTab(String test) {
		for (WebElement element : table_data ) {
			if (element.getText().equals(test)){
				WebElement elt =element.findElement(By.xpath("following-sibling::td[2]"));	
				return elt;
			}
				}
		return null;
	
	}

	public WebElement findLastColInTab(String test) {
		for (WebElement element : table_data ) {
			if (element.getText().equals(test)){
				WebElement elt =element.findElement(By.xpath("following-sibling::td[3]"));	
				return elt;
			}
				}
		return null;
	
	}
	
		
	
		
	}
	

