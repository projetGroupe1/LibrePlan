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
	
	
	public AdvancementTypeCreation(WebDriver d) {
		super(d);
		
	}
	
	public String getTitle() {
		String titlePage = title.getText();
		return  titlePage;
	}
	
	public AdvancementTypeCreationTest fillTheForm() {
		inputName.click();
		inputName.sendKeys(keysToSend);
		return PageFactory.initElements(driver, AdvancementTypeCreationTest.class); 
	}
	
	
	

}
