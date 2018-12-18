package projectPlan.groupe1;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.csv.CsvDataSet;
import org.junit.Before;
import org.junit.Ignore;
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
	public void CAL01() throws Exception {

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
		//Click on the nav menu
		CalendarListPage cal_list_page = planner_page.accessCalendarList(driver);
		//Assert on the page title
		assertTrue("Wrong title or page", cal_list_page.page_name.isDisplayed());
		//Assert on the table headers
		assertTrue("No header \"nom\"", cal_list_page.header_nom.isDisplayed());
		assertTrue("No header \"Hérité de la date\"", cal_list_page.header_herit_date.isDisplayed());
		assertTrue("No header \"Héritage à jour\"", cal_list_page.header_heritage_a_jour.isDisplayed());
		assertTrue("No header \"Opérations\"", cal_list_page.header_operations.isDisplayed());
		//Assert on the button "Créer"		
		assertTrue("No button \"Créer\"", cal_list_page.create_button.isDisplayed());
		//STEP3
		//Click on the create button
		cal_list_page.create_button.click();
		//Assert on the title + buttons
		assertTrue("Not in the cal creation", cal_list_page.create_calendar_title.isDisplayed());
		assertTrue("No \"Enregistrer\" button", cal_list_page.save_button.isDisplayed());
		assertTrue("No \"Enregistrer et continuer\" button", cal_list_page.save_and_continue_button.isDisplayed());
		assertTrue("No \"Annuler\" button", cal_list_page.cancel_button.isDisplayed());
		//STEP4
		//Fill form + save
		cal_list_page.fillNameForm(calendar_name, cal_list_page.save_button);
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.page_name));
		//Check title + calendar_name in the list
		assertTrue("Wrong page or title", cal_list_page.page_name.isDisplayed());
		assertTrue("First calendar doesn't exist",cal_list_page.added_cal1.getText().equals(calendar_name));
		//STEP5
		//Derive from calendar1
		cal_list_page.derive_cal1_button.click();
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.calendar_type_span));
		//Assert Input name null
		assertTrue("Input non null",cal_list_page.calendar_name_input.getAttribute("value").equals(""));
		//STEP6
		//Fill form + save and continue
		cal_list_page.fillNameForm(calendar_name, cal_list_page.save_and_continue_button);
		//Check on the errorbox
		assertTrue("No errorbox or wrong msg", cal_list_page.error_msg.getText().equals(calendar_name + " existe déjà"));
		//STEP7
		//Fill form
		cal_list_page.fillNameForm(calendar_deriv_name, cal_list_page.save_and_continue_button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'message_INFO')]/span[text()='"+msg_info+"']")));
		assertTrue("No info box or wrong message", cal_list_page.checkMsgInfo(msg_info));
		assertTrue("Wrong title or wrong page", driver.findElement(By.xpath("//td[text()='Créer Calendrier: "+calendar_deriv_name+"']")).isDisplayed());
		//STEP8
		//Click on the cancel button
		cal_list_page.cancel_button.click();
		assertTrue("Derived cal not from the initial cal", cal_list_page.line_deriv_cal_created.isDisplayed());
		//STEP9
		cal_list_page.minus_cal_created_button.click();
		//Assert to check that the derived calendar is not visible
		assertTrue("Derived cal still visible", driver.findElements(By.xpath("//tr[descendant::span[contains(@class,'z-dottree-root-open')]][descendant::span[contains(text(),'Calendrier - Test 1')]]/following-sibling::tr[descendant::span[contains(text(),'Calendrier - Test Calendrier Dérivé')]][descendant::span[contains(@class,'z-dottree-last')]]")).isEmpty());
		//STEP10
		//Click on copy
		cal_list_page.copy_cal1_button.click();
		//Check that Type = Calendar source && Name = Name of the selected calendar
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.calendar_type_span));
		assertTrue("Wrong type", cal_list_page.calendar_type_span.getText().equals("Calendrier source"));
		assertTrue("Incorrect name", cal_list_page.calendar_name_input.getAttribute("value").equals(calendar_name));
		//STEP11
		//Click on the "Enregistrer et continuer" button
		cal_list_page.save_and_continue_button.click();
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.error_msg));
		assertTrue("Wrong errorbox", cal_list_page.error_msg.getText().equals(calendar_name+" existe déjà"));
		//STEP12
		//Fill form + click on save button
		cal_list_page.fillNameForm(calendar_name2, cal_list_page.save_button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Liste de calendriers')]")));
		//Assert on the title, message and check if calendar 2 is in the table
		assertTrue("Wrong title or wrong page", cal_list_page.page_name.isDisplayed());
		assertTrue("Wrong info message or doesn't exist", cal_list_page.checkMsgInfo(msg_info_test2));
		assertTrue("No cal2 or derived from cal1", cal_list_page.line_cal2_created.isDisplayed());

		DataBaseConnection db_conn = new DataBaseConnection();
		//Get ID of the derived calendar
		String getDerivIdCal = "SELECT id FROM base_calendar WHERE name LIKE ('"+calendar_deriv_name+"')";
		String idDeriv = db_conn.getData("base_calendar",getDerivIdCal,"id");
		//ID of the associated calendar_data (FK base_calendar_id from base_calendar)
		String getIDDataCalDeriv = "SELECT id FROM calendar_data WHERE base_calendar_id ="+idDeriv;
		String idDataCalDeriv = db_conn.getData("calendar_data",getIDDataCalDeriv,"id");
		//DELETE FROM capacity_per_day (FK base_calendar_id based on id FROM calendar_data)
		String deleteIDDataCalDeriv = "DELETE FROM capacity_per_day WHERE base_calendar_id = "+idDataCalDeriv;
		db_conn.deleteData("capacity_per_day", deleteIDDataCalDeriv);
		//DELETE FROM calendar_data (FK base_calendar_id based on id FROM base_calendar)
		String deleteDerivCalData = "DELETE FROM calendar_data WHERE base_calendar_id = "+idDeriv;
		db_conn.deleteData("calendar_data", deleteDerivCalData);
		//DELETE FROM base_calendar
		String delete_query = "DELETE FROM base_calendar WHERE id ="+idDeriv;
		db_conn.deleteData("base_calendar", delete_query);

		//Get ID of the first calendar
		String getIdCal = "SELECT id FROM base_calendar WHERE name LIKE ('"+calendar_name+"')";
		String idCal = db_conn.getData("base_calendar",getIdCal,"id");
		//ID of the associated calendar_data (FK base_calendar_id from base_calendar)
		String getIDDataCal = "SELECT id FROM calendar_data WHERE base_calendar_id ="+idCal;
		String idDataCal = db_conn.getData("calendar_data",getIDDataCal,"id");
		//DELETE FROM capacity_per_day (FK base_calendar_id based on id FROM calendar_data)
		String deleteIDDataCal = "DELETE FROM capacity_per_day WHERE base_calendar_id = "+idDataCal;
		db_conn.deleteData("capacity_per_day", deleteIDDataCal);
		//DELETE FROM calendar_data (FK base_calendar_id based on id FROM base_calendar)
		String deleteCalData = "DELETE FROM calendar_data WHERE base_calendar_id = "+idCal;
		db_conn.deleteData("calendar_data", deleteCalData);
		//DELETE FROM base_calendar
		String delete_query_cal = "DELETE FROM base_calendar WHERE id ="+idCal;
		db_conn.deleteData("base_calendar", delete_query_cal);

		//ID of the second calendar
		String getIdCal2 = "SELECT id FROM base_calendar WHERE name LIKE ('"+calendar_name2+"')";
		String idCal2 = db_conn.getData("base_calendar",getIdCal2,"id");
		//ID of the associated calendar_data (FK base_calendar_id from base_calendar)
		String getIDDataCal2 = "SELECT id FROM calendar_data WHERE base_calendar_id ="+idCal2;
		String idDataCal2 = db_conn.getData("calendar_data",getIDDataCal2,"id");
		//DELETE FROM capacity_per_day (FK base_calendar_id based on id FROM calendar_data)
		String deleteIDDataCal2 = "DELETE FROM capacity_per_day WHERE base_calendar_id = "+idDataCal2;
		db_conn.deleteData("capacity_per_day", deleteIDDataCal2);
		//DELETE FROM calendar_data (FK base_calendar_id based on id FROM base_calendar)
		String deleteCalData2 = "DELETE FROM calendar_data WHERE base_calendar_id = "+idCal2;
		db_conn.deleteData("calendar_data", deleteCalData2);
		//DELETE FROM base_calendar
		String delete_query_cal2 = "DELETE FROM base_calendar WHERE id ="+idCal2;
		db_conn.deleteData("base_calendar", delete_query_cal2);
		driver.quit();
	}


	@Test
	public void CAL02() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String calendar_name = "Calendrier - Test 1"; 
		String username = "admin";
		String password = "admin";
		String exception = "LEAVE";
		String norm_effort1 = "2";
		String norm_effort2 = "3";
		String sup_effort1 = "4";
		String sup_effort2 = "5";
		String exception_number ="1";
		DataBaseConnection db_conn = new DataBaseConnection();
		//INSERT in the database needed info (base_calendar, FK calendar_data, FK capacity_per_day)
		String add_query = "INSERT INTO base_calendar (id, version, code, name, code_autogenerated) VALUES (200, 5 ,'CALENDAR0115','"+calendar_name+"',TRUE)";
		db_conn.insertData("base_calendar", add_query);
		String add_query_data = "INSERT INTO calendar_data (id, version, code, base_calendar_id, position_in_calendar) VALUES (350, 1 ,'CALENDAR0115-0001','200',0)";
		db_conn.insertData("calendar_data", add_query_data);
		String add_query_capacity = "INSERT INTO capacity_per_day (base_calendar_id, standard_effort, day_id) "
				+ "VALUES (350, 28800 ,0),"
				+ "(350,28800,1),"
				+ "(350,28800,2),"
				+ "(350,28800,3),"
				+ "(350,28800,4)";
		db_conn.insertData("capacity_per_day", add_query_capacity);
		String add_query_capacity_we = "INSERT INTO capacity_per_day (base_calendar_id, standard_effort, day_id, allowed_extra_effort) "
				+ "VALUES (350, 0, 5, 0),"
				+ "(350, 0, 6, 0)";
		db_conn.insertData("capacity_per_day", add_query_capacity_we);
		
		//STEP1
		//Fill + submit login form
		LoginPage login_page = new LoginPage(driver);
		login_page.fillLoginForm(username, password);
		PlannerPage planner_page = login_page.submitLoginForm();
		//STEP2
		CalendarListPage cal_list_page = planner_page.accessCalendarList(driver);
		//Assert on the page title
		assertTrue("Wrong page or title", cal_list_page.page_name.isDisplayed());
		//Assert on the table headers
		assertTrue("No header \"nom\"", cal_list_page.header_nom.isDisplayed());
		assertTrue("No header \"Hérité de la date\"", cal_list_page.header_herit_date.isDisplayed());
		assertTrue("No header \"Héritage à jour\"", cal_list_page.header_heritage_a_jour.isDisplayed());
		assertTrue("No header \"Opérations\"", cal_list_page.header_operations.isDisplayed());
		//Assert on the button "Créer"		
		assertTrue("No create button", cal_list_page.create_button.isDisplayed());
		//STEP3
		//Click on the modif button for the calendar
		cal_list_page.modif_cal1_button.click();
		//Wait until the save button is visible
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.save_button));
		//Check save/save&continue/cancel button
		assertTrue("No button \"Enregistrer\"", cal_list_page.save_button.isDisplayed());
		assertTrue("No button \"Enregistrer et continuer\"", cal_list_page.save_and_continue_button.isDisplayed());
		assertTrue("No button \"Annuler\"", cal_list_page.cancel_button.isDisplayed());
		//Check title + form title
		assertTrue("Page title incorrect", cal_list_page.modif_calendar_title.getText().equals("Modifier Calendrier: "+calendar_name));
		assertTrue("Form title incorrect", cal_list_page.modif_calendar_form_title.isDisplayed());
		//STEP4
		//NO_EXCEPTION + click
		cal_list_page.checkSelectModifAndAddException("");
		//Check if the errorbox appears
		assertTrue("ErrorBox not displayed", cal_list_page.error_box_exception.isDisplayed());
		//STEP5
		//Exception sent, set on the select and click
		cal_list_page.checkSelectModifAndAddException(exception);
		//Check if the errorbox "no date" appears
		assertTrue("ErrorBox date not displayed", cal_list_page.error_box_date_exception.isDisplayed());
		//STEP6
		//Format on the date to compare to the date in the table
		String start_date = cal_list_page.input_start_date_excep.getAttribute("value");
		start_date = start_date.substring(0, 2);
		start_date = start_date.trim();	
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(cal.getTime());
		//Click and set the right date based on the current date
		cal_list_page.btn_end_date_excep.click();
		driver.findElement(By.xpath("//div[contains(@class,'z-datebox-pp')][not(contains(@style,'none'))]/descendant::td[not(contains(@class,'z-outside'))][contains(@class,'z-calendar-wk')][text()='"+start_date+"']")).click();	
		//New exception button
		cal_list_page.new_exception_btn.click();
		//Check all the values in the exception table
		assertTrue("Wrong date",cal_list_page.list_td_exception_table.get(0).getText().equals(today));
		assertTrue("Wrong type",cal_list_page.list_td_exception_table.get(1).getText().equals(exception));
		assertTrue("Wrong norm effort",cal_list_page.list_td_exception_table.get(2).getText().equals("0:0"));
		assertTrue("Wrong add effort",cal_list_page.list_td_exception_table.get(3).getText().equals("0:0"));
		assertTrue("Code enabled or not empty",cal_list_page.list_td_exception_table.get(4).findElement(By.xpath("descendant::input[@disabled='disabled']")).getAttribute("value").equals(""));
		assertTrue("Wrong origin",cal_list_page.list_td_exception_table.get(5).getText().equals("Direct"));
		//Check all the values in the day properties table
		assertTrue("Wrong start date in properties",cal_list_page.list_value_td_day_properties.get(0).findElement(By.xpath("descendant::input[contains(@class,'z-datebox-inp')]")).getAttribute("value").equals(cal_list_page.input_start_date_excep.getAttribute("value")));
		assertTrue("Wrong type in properties",cal_list_page.list_value_td_day_properties.get(1).getText().equals("Exception: "+exception));
		assertTrue("Wrong worked time in properties",cal_list_page.list_value_td_day_properties.get(2).getText().equals("0:0"));
		//STEP7
		//Fill the form with the effort (normal/sup)
		cal_list_page.fillEffortForm(norm_effort1,norm_effort2,sup_effort1,sup_effort2);
		//Check the modifs after the fct call
		assertTrue("Effort normal updated incorrect",cal_list_page.list_td_exception_table.get(2).getText().equals(norm_effort1+":"+norm_effort2));
		assertTrue("Effort supplémentaire updated incorrect",cal_list_page.list_td_exception_table.get(3).getText().equals(sup_effort1+":"+sup_effort2));
		assertTrue("Wrong worked time updated in properties",cal_list_page.list_value_td_day_properties.get(2).getText().equals(norm_effort1+":"+norm_effort2));
		//STEP8
		//Click on save button
		cal_list_page.save_button.click();
		//Wait until page changed
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.page_name));
		//Check on the information message
		assertTrue("Message info faux",cal_list_page.checkMsgInfo("Calendrier de base \""+calendar_name+"\" enregistré"));
		//STEP9
		//Click on the modif button
		cal_list_page.modif_cal1_button.click();
		//Wait until page changed
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.modif_calendar_title));
		//Check page + form + buttons
		assertTrue("Mauvaise page",cal_list_page.modif_calendar_title.getText().equals("Modifier Calendrier: "+calendar_name));
		assertTrue("Formulaire non present", cal_list_page.modif_calendar_form_title.isDisplayed());
		assertTrue("Bouton Enregistrer et continuer absent", cal_list_page.save_and_continue_button.isDisplayed());
		assertTrue("Btn Enregistrer abs", cal_list_page.save_button.isDisplayed());
		assertTrue("Btn Cancel abs", cal_list_page.cancel_button.isDisplayed());
		//STEP10
		//Get the code for future assert
		String code = cal_list_page.list_td_exception_table.get(4).findElement(By.xpath("descendant::input[@disabled='disabled']")).getAttribute("value");
		//Assert on the code generated in the exception table
		assertTrue("Mauvais code", code.equals(cal_list_page.generated_code_inp.getAttribute("value")+"-000"+exception_number));
		//STEP11
		//Click on the cancel button
		cal_list_page.cancel_button.click();
		//Wait until page changed
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.page_name));
		//STEP12
		//Click on derive button for Calendrier - Test 1
		cal_list_page.derive_cal1_button.click();
		//Check if correct values in the form
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.calendar_type_span));
		assertTrue("Input non null",cal_list_page.calendar_name_input.getAttribute("value").isEmpty());
		assertTrue("Type incorrect", cal_list_page.calendar_type_span.getText().equals("Dérivé du calendrier "+calendar_name));
		assertTrue("Mauvaise page", cal_list_page.create_calendar_title.isDisplayed());
		//Check all the values in the exception table
		assertTrue("Date incorrecte",cal_list_page.list_td_exception_table.get(0).getText().equals(today));
		assertTrue("Type d'exception incorrect",cal_list_page.list_td_exception_table.get(1).getText().equals(exception));
		assertTrue("Effort normal updated incorrect",cal_list_page.list_td_exception_table.get(2).getText().equals(norm_effort1+":"+norm_effort2));
		assertTrue("Effort supplémentaire updated incorrect",cal_list_page.list_td_exception_table.get(3).getText().equals(sup_effort1+":"+sup_effort2));
		assertTrue("Code incorrect",cal_list_page.list_td_exception_table.get(4).findElement(By.xpath("descendant::input[@disabled='disabled']")).getAttribute("value").equals(code));
		assertTrue("Origine incorrecte",cal_list_page.list_td_exception_table.get(5).getText().equals("Hérité"));

		//STEP13
		//Click on the cancel button
		cal_list_page.cancel_button.click();
		//Wait and check if on the right page
		wait.until(ExpectedConditions.visibilityOf(cal_list_page.page_name));
		//STEP14
		//Click on the copy button for Calendrier - Test 1
		cal_list_page.copy_cal1_button.click();
		//Wait until page changed
		WebElement create_cal_title = driver.findElement(By.xpath("//td[text()='Créer Calendrier: "+calendar_name+"']"));
		wait.until(ExpectedConditions.visibilityOf(create_cal_title));
		//Assert on the title, form value and values in the exception table
		assertTrue("Mauvais titre", create_cal_title.isDisplayed());
		assertTrue("Input non null",cal_list_page.calendar_name_input.getAttribute("value").equals(calendar_name));
		assertTrue("Type incorrect", cal_list_page.calendar_type_span.getText().equals("Calendrier source"));
		assertTrue("Date incorrecte",cal_list_page.list_td_exception_table.get(0).getText().equals(today));
		assertTrue("Type d'exception incorrect",cal_list_page.list_td_exception_table.get(1).getText().equals(exception));
		assertTrue("Effort normal updated incorrect",cal_list_page.list_td_exception_table.get(2).getText().equals(norm_effort1+":"+norm_effort2));
		assertTrue("Effort supplémentaire updated incorrect",cal_list_page.list_td_exception_table.get(3).getText().equals(sup_effort1+":"+sup_effort2));
		assertTrue("Code incorrect",cal_list_page.list_td_exception_table.get(4).findElement(By.xpath("descendant::input[@disabled='disabled']")).getAttribute("value").equals(code));
		assertTrue("Origine incorrecte",cal_list_page.list_td_exception_table.get(5).getText().equals("Direct"));

		//DELETE exception created
		String delete_exception = "DELETE FROM calendar_exception WHERE base_calendar_id=200";
		db_conn.deleteData("calendar_exception", delete_exception);
		//ID from calendar_data
		String getIDDataCal = "SELECT id FROM calendar_data WHERE base_calendar_id = 200";
		String idDataCal = db_conn.getData("calendar_data",getIDDataCal,"id");
		//DELETE from capacity_per_day (FK)
		String deleteIDDataCal = "DELETE FROM capacity_per_day WHERE base_calendar_id = "+idDataCal;
		db_conn.deleteData("capacity_per_day", deleteIDDataCal);
		//DELETE from calendar_data
		String deleteCalData = "DELETE FROM calendar_data WHERE base_calendar_id = 200";
		db_conn.deleteData("calendar_data", deleteCalData);
		//DELETE from base_calendar
		String delete_query_cal = "DELETE FROM base_calendar WHERE id = 200";
		db_conn.deleteData("base_calendar", delete_query_cal);
		
		driver.quit();
		
	}
}
