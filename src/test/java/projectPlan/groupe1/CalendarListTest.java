package projectPlan.groupe1;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CalendarListTest {
	WebDriver driver = new FirefoxDriver();

	@Before
	public void setUp() {
		driver.get("http://localhost:8180/libreplan/");
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	@Test
	public void CAL01() {
		String calendar_name = "Calendrier - Test 1"; 
		String username = "admin";
		String password = "admin";
		LoginPage login_page = new LoginPage(driver);
		login_page.fillLoginForm(username, password);
		PlannerPage planner_page = login_page.submitLoginForm();
		CalendarListPage cal_list_page = planner_page.accessCalendarList(driver);
		//Assert on the page title
		assertTrue("Mauvaise page", cal_list_page.page_name.getText().equals("Liste de calendriers"));
		//Va poser des soucis si marche pas à cause d'un potentiel elementnotfound
		//A SECURISER 
		//Assert on the table headers
		assertTrue("Entête nom non présent", cal_list_page.header_nom.getText().equals("Nom"));
		assertTrue("Entête Hérité de la date non présent", cal_list_page.header_herit_date.getText().equals("Hérité de la date"));
		assertTrue("Entête Héritage à jour non présent", cal_list_page.header_heritage_a_jour.getText().equals("Héritages à jour"));
		assertTrue("Entête Opérations non présent", cal_list_page.header_operations.getText().equals("Opérations"));
		//Assert on the button "Créer"		
		assertTrue("Bouton créer non présent", cal_list_page.create_button.getText().equals("Créer"));
		cal_list_page.create_button.click();
		assertTrue("Pas dans la création de calendrier", cal_list_page.create_calendar_title.getText().equals("Créer Calendrier"));
		assertTrue("Bouton Enregistrer existe pas", cal_list_page.save_button.getText().equals("Enregistrer"));
		assertTrue("Bouton Enregistrer et continuer existe pas", cal_list_page.save_and_continue_button.getText().equals("Enregistrer et continuer"));
		assertTrue("Bouton Annuler existe pas", cal_list_page.cancel_button.getText().equals("Annuler"));
		cal_list_page.fillNameForm(calendar_name);
	}
}
