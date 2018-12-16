package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancementTypeCreation extends GenericPage {

	@FindBy(how = How.XPATH, using = "//td[contains(@id,'t4-cnt')]")
	WebElement title;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement inputName;

	@FindBy(how = How.XPATH, using = "")
	WebElement inputMaxValue;

	@FindBy(how = How.XPATH, using = "")
	WebElement inputPrecision;

	@FindBy(how = How.XPATH, using = "")
	WebElement inputType;

	@FindBy(how = How.XPATH, using = "")
	WebElement saveButton;

	@FindBy(how = How.XPATH, using = "")
	WebElement SaveAndContinueButton;

	@FindBy(how = How.XPATH, using = "")
	WebElement cancelButton;
	
	@FindBy(how = How.XPATH, using ="")
	WebElement checkboxActif;
	
	@FindBy(how = How.XPATH, using ="")
	WebElement checkboxPourcentage;
	
	public AdvancementTypeCreation(WebDriver d) {
		super(d);

	}

	//to use checkbox in tests
	public WebElement getChecboxActif() {
		return checkboxActif;
	}
	
	public WebElement getcheckboxPourcentage() {
		return checkboxPourcentage;
	}
	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement messageTypeAdvancement2;
	
	// to check tableTitles

	public String getTitle() {
		String titlePage = title.getText();
		return titlePage;
	}
	
	public String getName() {
		String name = inputName.getText();
		return name;
	}

	public String getMaxValue() {
		String maxValue = inputMaxValue.getText();
		return maxValue;
	}

	public String getPrecision() {
		String precision = inputPrecision.getText();
		return precision;
	}

	public String getType() {
		String type = inputType.getText();
		return type;
	}

	public String getsaveButton() {
		String saveBouton = saveButton.getText();
		return saveBouton;
	}
	
	public String getsaveAndContinue() {
		String saveAndContinue = SaveAndContinueButton.getText();
		return saveAndContinue;
	}
	
	public String getCancelButton() {
		String cancel = cancelButton.getText();
		return cancel;
	}
	
	public String getMessageTypeAdvancement2() {
		String message2 = messageTypeAdvancement2.getText();
		return message2;
	}

	//toFill and send the Form for the first test
	public AdvancementTypeList fillTheForm() {
		inputName.click();
		inputName.sendKeys("Type avancement - Test 1");
		checkboxActif.isEnabled();
		inputMaxValue.clear();
		inputMaxValue.sendKeys("10,00");
		saveButton.click();
		return PageFactory.initElements(driver, AdvancementTypeList.class);
	}

	//to fill the form for the second test without sending for test and to put in fillTheForm2 to validate
	public void fillWithoutSend() {
		inputName.click();
		inputName.clear();
		inputName.sendKeys("Type avancement - Test 2");
		checkboxPourcentage.click();
	}
	//toFill and saveToContinue the Form for the second test 
	public void fillTheForm2() {
		fillWithoutSend();
		SaveAndContinueButton.click();
		
	}
	
	//to click to cancel and go to pageList
	public AdvancementTypeList Cancel() {

		cancelButton.click();
		return PageFactory.initElements(driver, AdvancementTypeList.class);
	}
	
	
	
}
