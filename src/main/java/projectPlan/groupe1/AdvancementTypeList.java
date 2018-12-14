package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancementTypeList extends GenericPage {
	@FindBy(how = How.XPATH, using = "//span[@class = 'create-button global-action z-button']")
	WebElement create;
	
	
	public AdvancementTypeList(WebDriver d) {
		super(d);

	}

	// To click on create
	public AdvancementTypeCreation createType() {
		create.click();
	return PageFactory.initElements(driver, AdvancementTypeCreation.class);
	}
	

}
