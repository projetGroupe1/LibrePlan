package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancementTypeCreation extends GenericPage {
	//rows
	@FindBy(how = How.XPATH, using = "//td[contains(@id,'t4-cnt')]")
	WebElement title;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'45')]")
	WebElement rowName;
	
	//inputs
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement inputName;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'85-real')]")
	WebElement checkBoxActif;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'b5')]")
	WebElement inputValMax;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'e5')]")
	WebElement inputPrecision;
	
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'h5')]")
	WebElement spanType;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'k5')]")
	WebElement checkboxPourcentage;
	
	//buttons
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'l5')]/descendant::td[5]")
	WebElement saveButtonName;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'l5')]")
	WebElement saveButtonToClick;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'m5')]/descendant::td[5]")
	WebElement SaveAndContinueButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'m5')]")
	WebElement SaveAndContinueButtonToClick;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'n5')]/descendant::td[5]")
	WebElement cancelButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'n5')]")
	WebElement cancelButtonToClick;
	
	//messages
	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement messageTypeAdvancement;
	
	@FindBy(how = How.XPATH, using = "//td[contains(@id,'t4-cnt')]")
	WebElement titlePageTest2;
	
	public AdvancementTypeCreation(WebDriver d) {
		super(d);
		
	}

	//to use WebElements in pageTest
	public WebElement getChecboxActif() {
		return checkBoxActif;
	}
	
	public WebElement getcheckboxPourcentage() {
		return checkboxPourcentage;
	}

	
	// to get strings
	public String getTitle() {
		String titlePage = title.getText();
		return titlePage;
	}

	public String getRowName() {
		String name = rowName.getText();
		return name;
	}

	public String getActif(){
		String actifCase = checkBoxActif.getText();
		return actifCase;
	}
		
	public String getInputName() {
		String name = inputName.getText();
		return name;
	}

	public String getInputMaxValue() {
		String maxValue = inputValMax.getText();
		return maxValue;
	}

	public String getPrecision() {
		String precision = inputPrecision.getText();
		return precision;
	}

	public String getType() {
		String type = spanType.getText();
		return type;
	}

	public String getsaveButton() {
		String saveBouton = saveButtonName.getText();
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
	
	//to get defaultValue
	public String getMaxValueVal() {
		String valueMax =  inputValMax.getAttribute("value");
		return valueMax;
	}
	
	public String getPrecisionVal() {
		String precisionVal =  inputPrecision.getAttribute("value");
		return precisionVal;
	}
	
	public String getTitlePageTest2() {
		String titlePage2 = titlePageTest2.getText();
		return titlePage2;
	}
	
	//to get checkbox
	public WebElement getMaxValueElement() {
		return inputValMax;
	}
	
	public String getMessageTypeAdvancement() {
		String message2 = messageTypeAdvancement.getText();
		return message2;
	}

	//toFill and send the Form for the first test
	public AdvancementTypeList fillTheForm() throws InterruptedException {
		
		inputName.sendKeys("Type avancement - Test 1");
		checkBoxActif.click();
		inputValMax.clear();
		inputValMax.sendKeys("10,00");
		saveButtonToClick.click();
		return PageFactory.initElements(driver, AdvancementTypeList.class);
	}

	//to fill the form for the second test without sending for test and to put in fillTheForm2 to validate
	public void fillWithoutSend() {
		inputName.sendKeys("Type avancement - Test 2");
		checkboxPourcentage.click();
	}
	//to saveToContinue the Form for the second test 
	public void saveTheForm2() {
		SaveAndContinueButtonToClick.click();
		
	}
	
	//to click to cancel and go to pageList
	public AdvancementTypeList cancel() {
		cancelButtonToClick.click();
		return PageFactory.initElements(driver, AdvancementTypeList.class);
	}
	
	
	
}
