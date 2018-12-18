package projectPlan.groupe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public abstract class GenericPage {

	public final static String URL ="http://localhost:8180/libreplan/";
	protected WebDriver driver;

	@FindBy (how = How.XPATH, using="//button[contains(text(),'Ressources')]")
	WebElement resources_tab;

	@FindBy (how = How.XPATH, using="//a[contains(@href,'libreplan/resources/criterions/criterions.zul')]")
	WebElement criterions_link;

	@FindBy (how = How.XPATH, using="//a[contains(@href,'/libreplan/advance/advanceTypes.zul')]")
	WebElement advancement_types_link;

	@FindBy (how = How.XPATH, using="//a[contains(@href,'libreplan/calendars/calendars.zul')]")
	WebElement calendar_list_link;
	
	//Constructor
	public GenericPage(WebDriver d)
	{
		super();
		this.driver = d;
	}

	//Mouseover on "Ressources" then click on "critères"
	public CriterionsPage accessCriterions(WebDriver driver) {
		Actions actions = new Actions (driver);
		actions.moveToElement(resources_tab).pause(1000).moveToElement(criterions_link).click().build().perform();
		return PageFactory.initElements(driver, CriterionsPage.class);
	}

//	//Mouseover on "Ressources" then click on "advancement_types_link"
//	public AdvancementTypesPage accessAdvancementTypes(WebDriver driver) {
//		Actions actions = new Actions (driver);
//		actions.moveToElement(resources_tab).pause(1000).moveToElement(advancement_types_link).click().build().perform();
//		return PageFactory.initElements(driver, AdvancementTypesPage.class);
//	}

	//Mouseover on "Ressources" then click on "advancement_types_link"
	public CalendarListPage accessCalendarList(WebDriver driver) {
		Actions actions = new Actions (driver);
		actions.moveToElement(resources_tab).pause(1000).moveToElement(calendar_list_link).click().build().perform();
		return PageFactory.initElements(driver, CalendarListPage.class);
	}
}
