package projectPlan.groupe1;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalendarListTest {
	WebDriver driver = new FirefoxDriver();

	@Before
	public void setUp() {
		driver.get("http://localhost:8180/libreplan/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void CAL01() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String calendar_name = "Calendrier - Test 1"; 
		String calendar_name2 = "Calendrier - Test 2"; 
		String calendar_deriv_name = "Calendrier - Test Calendrier Dérivé";
		String msg_info = "Calendrier de base \"Calendrier - Test Calendrier Dérivé\" enregistré";
		String msg_info_test2 = "Calendrier de base \"Calendrier - Test 2\" enregistré";
		String username = "admin";
		String password = "admin";
		//STEP1
		//Fill + submit login form
		LoginPage login_page = new LoginPage(driver);
		login_page.fillLoginForm(username, password);
		PlannerPage planner_page = login_page.submitLoginForm();
		//STEP2
		CalendarListPage cal_list_page = planner_page.accessCalendarList(driver);
		//Assert on the page title
		assertTrue("Mauvaise page", cal_list_page.page_name.isDisplayed());
		//Va poser des soucis si marche pas à cause d'un potentiel elementnotfound
		//A SECURISER 
		//Assert on the table headers
		assertTrue("Entête nom non présent", cal_list_page.header_nom.getText().equals("Nom"));
		assertTrue("Entête Hérité de la date non présent", cal_list_page.header_herit_date.getText().equals("Hérité de la date"));
		assertTrue("Entête Héritage à jour non présent", cal_list_page.header_heritage_a_jour.getText().equals("Héritages à jour"));
		assertTrue("Entête Opérations non présent", cal_list_page.header_operations.getText().equals("Opérations"));
		//Assert on the button "Créer"		
		assertTrue("Bouton créer non présent", cal_list_page.create_button.getText().equals("Créer"));
		//STEP3
		cal_list_page.create_button.click();
		//Assert on the title + buttons
		assertTrue("Pas dans la création de calendrier", cal_list_page.create_calendar_title.getText().equals("Créer Calendrier"));
		assertTrue("Bouton Enregistrer existe pas", cal_list_page.save_button.getText().equals("Enregistrer"));
		assertTrue("Bouton Enregistrer et continuer existe pas", cal_list_page.save_and_continue_button.getText().equals("Enregistrer et continuer"));
		assertTrue("Bouton Annuler existe pas", cal_list_page.cancel_button.getText().equals("Annuler"));
		//STEP4
		cal_list_page.fillNameForm(calendar_name, cal_list_page.save_button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Liste de calendriers')]")));
		//Check title + calendar_name in the list
		assertTrue("Mauvaise page returned", cal_list_page.page_name.isDisplayed());
		assertTrue("Premier calendrier pas présent",cal_list_page.added_cal1.getText().equals(calendar_name));
		//STEP5
		cal_list_page.derive_cal1_button.click();
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.calendar_type_span));
		//Assert Input name null
		assertTrue(cal_list_page.calendar_name_input.getAttribute("value").equals(""));
		//STEP6
		cal_list_page.fillNameForm(calendar_name, cal_list_page.save_and_continue_button);
		assertTrue("", cal_list_page.error_msg.getText().equals(calendar_name + " existe déjà"));
		//STEP7
		cal_list_page.fillNameForm(calendar_deriv_name, cal_list_page.save_and_continue_button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'message_INFO')]/span[text()='"+msg_info+"']")));
		assertTrue("Message info faux", cal_list_page.checkMsgInfo(msg_info));
		assertTrue("Mauvais titre", driver.findElement(By.xpath("//td[text()='Créer Calendrier: "+calendar_deriv_name+"']")).isDisplayed());
		//STEP8
		cal_list_page.cancel_button.click();
		assertTrue("Cal dérivé ne vient pas du cal créé", cal_list_page.line_deriv_cal_created.isDisplayed());
		//STEP9
		cal_list_page.minus_cal_created_button.click();
		//Assert to check that the derived calendar is not visible
		assertTrue("Le calendrier dérivé est toujours présent", driver.findElements(By.xpath("//tr[descendant::span[contains(@class,'z-dottree-root-open')]][descendant::span[contains(text(),'Calendrier - Test 1')]]/following-sibling::tr[descendant::span[contains(text(),'Calendrier - Test Calendrier Dérivé')]][descendant::span[contains(@class,'z-dottree-last')]]")).isEmpty());
		//STEP10
		cal_list_page.copy_cal1_button.click();
		//Check that Type = Calendar source && Name = Name of the selected calendar
		assertTrue("Type incorrect", cal_list_page.calendar_type_span.getText().equals("Calendrier source"));
		assertTrue("Nom incorrect", cal_list_page.calendar_name_input.getAttribute("value").equals(calendar_name));
		//STEP11
		cal_list_page.save_and_continue_button.click();
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.error_msg));
		assertTrue("Message erreur faux", cal_list_page.error_msg.getText().equals(calendar_name+" existe déjà"));
		//STEP12
		cal_list_page.fillNameForm(calendar_name2, cal_list_page.save_button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Liste de calendriers')]")));
		//Assert on the title, message and check if calendar 2 is in the table
		assertTrue("Mauvaise page returned", cal_list_page.page_name.isDisplayed());
		assertTrue("Message info faux", cal_list_page.checkMsgInfo(msg_info_test2));
		assertTrue("Test 2 pas créé ou est dérivé de cal 1", cal_list_page.line_cal2_created.isDisplayed());
	
	
	
	
	}
}
