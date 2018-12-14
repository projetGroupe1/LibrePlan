package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends GenericPage{
	//Username input
	@FindBy (how = How.XPATH, using="//input[@name='j_username']")
	WebElement username_input;
	//Pwd input
	@FindBy (how = How.XPATH, using="//input[@name='j_password']")
	WebElement password_input;
	//Submit button
	@FindBy (how = How.XPATH, using="//input[@name='button'][@type='submit']")
	WebElement submit_login;
	
	//Constructor
	public LoginPage(WebDriver d) {
		super(d);
		PageFactory.initElements(d, this);
	}
	
	//Fill the login page with "username" and "password"
	public void fillLoginForm(String username, String password) {
		username_input.clear();
		username_input.sendKeys(username);
		password_input.clear();
		password_input.sendKeys(password);
	}
	
	//Click on the submit button to log in
	public PlannerPage submitLoginForm() {
		submit_login.click();
		return PageFactory.initElements(driver, PlannerPage.class);
	}
}
