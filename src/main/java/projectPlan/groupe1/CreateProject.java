package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateProject extends GenericPage {
	
	//pas eu le temps de faire ce cas de test

	@FindBy(how = How.XPATH, using = "//div[@class = 'z-window-embedded-header']")
	WebElement titleTab;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowName;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowModel;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowCode;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowStartDate;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowEcheance;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowClient;

	@FindBy(how = How.XPATH, using = "")
	WebElement rowCalendar;

	@FindBy(how = How.XPATH, using = "")
	WebElement agreeButton;

	@FindBy(how = How.XPATH, using = "")
	WebElement cancelButton;

	public CreateProject(WebDriver d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		String title = titleTab.getText();
		return title;
	}

	public String getName() {
		String name = rowName.getText();
		return name;
	}

	public String getModel() {

		String title = rowModel.getText();
		return title;
	}

	Select select = new Select(rowModel);

	public String getCode() {
		String code = rowCode.getText();
		return code;
	}

	public String getDateStart() {
		String dateStart = rowStartDate.getText();
		return dateStart;
	}

	public String getEcheance() {
		String echeance = rowEcheance.getText();
		return echeance;
	}

	public String client() {
		String client = rowClient.getText();
		return client;
	}
	
	public String calendar() {
		String calendar = rowCalendar.getText();
		return calendar;
	}
	
	public String validateButton() {
		String validate = agreeButton.getText();
		return validate;
	}
	
	public String cancelButton () {
		String cancel = cancelButton.getText();
		return cancel;
	}
	
	public CalendarListPage fillForm() {
		rowName.clear();
		rowName.sendKeys("PROJET_TEST1");
		rowCode.click();
		
		 return PageFactory.initElements(driver,CalendarListPage.class);
	}

}
