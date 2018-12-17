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

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement actif;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement valMax;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement precision;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement type;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'55')]")
	WebElement pourcentage;

	public AdvancementTypeCreation(WebDriver d) {
		super(d);

	}

	// methods to get Strings from WebElements
	public String getTitle() {
		String titlePage = title.getText();
		return titlePage;
	}

	public String getInputName() {
		String unitNameInput = inputName.getText();
		return unitNameInput;
	}

	public String get (){
		String actifCase = actif.getText();
		return actifCase;
	}

	public AdvancementTypeCreationTest fillTheForm() {
		inputName.click();
		inputName.sendKeys("");
		return PageFactory.initElements(driver, AdvancementTypeCreationTest.class);
	}

}
