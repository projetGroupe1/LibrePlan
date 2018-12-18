package librePlan.group1;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class PageObject {

	WebDriver driver;
	
	@Test // 
	public void monPremierTestEnPageObject() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\formation\\Desktop\\SUT\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);

		driver.get("http://localhost:8180/libreplan");
		
		LoginPage l_page = new LoginPage(driver);
		
		// username and password
		
		 l_page.fillLoginForm("admin","admin");
		 
		 PlannerPage p_Page = l_page.submitLoginForm();
		 
 //1. Connexion à l'application - Profil Admin : 
		 Thread.sleep(2000);
		 assertTrue("pas de correct page",driver.findElement(By.xpath("//button[contains(text(),'Calendrier')]")).getText().equals("Calendrier "));
		 
		 assertTrue("pas de correct page",driver.findElement(By.xpath("//td[contains(text(),'utilisateur: admin')]")).getText().equals("utilisateur: admin"));
         
         
//2. Accéder à la page d'administration des critères :
		 
		 CriterionsPage c_Page = p_Page.accessCriterions(driver);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
		 

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-column-cnt'][contains(text(),'Nom')]")).getText().equals("Nom"));
		 

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-column-cnt'][contains(text(),'Code')]")).getText().equals("Code"));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-column-cnt'][contains(text(),'Type')]")).getText().equals("Type"));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-column-cnt'][contains(text(),'Activé')]")).getText().equals("Activé"));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-column-cnt'][contains(text(),'Opérations')]")).getText().equals("Opérations"));

		 assertTrue("pas de btn ",driver.findElement(By.xpath("//td[contains(text(),'Créer')]")).getText().equals("Créer"));

	
//3. Créer un critère - Accès au formulaire de création :
		 
		 
		 // redirects to the page Créer Type de critère
		 
		 c_Page.createCategory();

		 // checks the Créer Type de critère" contenant :
		 
		 assertTrue("pas correct page",driver.findElement(By.xpath("//td[contains(text(),'Créer Type de critère')]")).getText().equals("Créer Type de critère"));

		 // checks the tab modifier 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Modifier')]")));
		 assertTrue("pas de correct page",driver.findElement(By.xpath("//span[contains(text(),'Modifier')]")).getText().equals("Modifier"));
 
		 // checks the buttons save, save and continue and close are present.
		 
		 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Enregistrer']")).getText().equals("Enregistrer"));
 
		 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Sauver et continuer']")).getText().equals("Sauver et continuer"));

		 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Annuler']")).getText().equals("Annuler"));

	
//4. Créer un critère - bouton [Annuler] :
		 
		 // enters the name "Créer un critère - bouton [Annuler]" on the name criteria field
		 
		 c_Page.fillName("Critère - Test bouton [Annuler]");
		 
		 // checks the checkbox is clicked if not it will click
		 
		 assertEquals("PARTICIPANT", c_Page.combobox_list.getAttribute("value"));
		 
		 if ( !driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).isSelected() )
		 {
		      driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).click();
		 }
		 
		 if ( !driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).isSelected() )
		 {
		      driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).click();
		 }
	
		 if ( !driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).isSelected() )
		 {
		      driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).click();
		 }
	 
 
		// Enters the name "Créer un critère - bouton [Annuler]" on the description field
		 

		 c_Page.fillDescription("Critère - Test bouton [Annuler]");
		 
		 
		// clicks Annuler on order not to save the entered details
		 
	     c_Page.clickAnnuler();
	     
        // checks the text "Types de critères Liste" is present
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

		 assertTrue("pas correct page",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
		
		 // checks the table that "Critère - Test bouton [Annuler]" is not present;
		
		  assertTrue("Pop up not present", driver.findElements(By.xpath("//span[@title='Critère - Test bouton [Annuler]']")).isEmpty());

		  
		  
		  
//5.Créer un critère - bouton [Enregistrer] :
		  
		  // clicks the button create
		  
		  c_Page.createCategory();
		  
		  // Enters the name"Critère - Test bouton [Enregistrer]" on the name of the category field
		  
		  c_Page.fillName("Critère - Test bouton [Enregistrer]");
			
			 // checks the check boxes clicked or not
		 	
			 assertEquals("PARTICIPANT", c_Page.combobox_list.getAttribute("value"));
			 
			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).click();
			 }
			 
			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).click();
			 }

			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).click();
			 }
		 

			 // Enters the name in description
			 
			 c_Page.fillDescription("Critère - Test bouton [Enregistrer]");
			 
			 // clicks the button "Enregister"
			 
			 c_Page.clickEnregistrer();
			 
			 
			// checks the text "Types de critères Liste" is present
			 
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

			 assertTrue("Text not present",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
			
		  
			 assertTrue("Text not present",driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Enregistrer]']")).getText().equals("Critère - Test bouton [Enregistrer]"));

			 
//6. Créer un critère - Accès au formulaire de création :		 
			 
			 
			 // redirects to the page Créer Type de critère
			 
			 c_Page.createCategory();

			 // checks the Créer Type de critère" contenant :
			 
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Créer Type de critère')]")));

			 assertTrue("pas correct page",driver.findElement(By.xpath("//td[contains(text(),'Créer Type de critère')]")).getText().equals("Créer Type de critère"));

			 // checks the tab modifier 
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Modifier')]")));
			 assertTrue("pas de correct page",driver.findElement(By.xpath("//span[contains(text(),'Modifier')]")).getText().equals("Modifier"));
	 
			 // checks the buttons save, save and continue and close are present.
			 
			 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Enregistrer']")).getText().equals("Enregistrer"));
	 
			 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Sauver et continuer']")).getText().equals("Sauver et continuer"));

			 assertTrue("button  not present",driver.findElement(By.xpath("//td[@class='z-button-cm'][text()='Annuler']")).getText().equals("Annuler"));

			 
//7.Créer un critère - bouton [Sauver et continuer] :
			 
			 // Enters the name on the field
			 
			 c_Page.fillName("Critère - Test bouton [Sauver et continuer]");
			 
			 // checks the checboxes are clicked
			 	
			 assertEquals("PARTICIPANT", c_Page.combobox_list.getAttribute("value"));
			 
			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Valeurs multiples par ressource')]/following::input[@type='checkbox'][1]")).click();
			 }
			 
			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Hiérarchie')]/following::input[@type='checkbox'][1]")).click();
			 }
		
			 if ( !driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).isSelected() )
			 {
			      driver.findElement(By.xpath("//span[contains(text(),'Activé')]/following::input[@type='checkbox'][1]")).click();
			 }
		 

		 // Enters the name on description
		 c_Page.fillDescription("Critère - Test bouton [Sauver et continuer]");
		 
		 // clicks the button save and continue
		 
		 c_Page.clickSaveAndContinue();
			 
		 // checks the message "Type de critère "Critère - Test bouton [Sauver et continuer]" enregistré" is present
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Test bouton [Sauver et continuer]')]")));

		 assertTrue("table not present",driver.findElement(By.xpath("//span[contains(text(),'Test bouton [Sauver et continuer]')]")).getText().equals("Type de critère \"Critère - Test bouton [Sauver et continuer]\" enregistré"));

		 c_Page.clickRandom();
		 
		 // Checks the title "Modifier Type de critère: Critère - Test bouton [Sauver et continuer]" has changed
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")));
		 assertTrue("Title not present",driver.findElement(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")).getText().equals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer]"));
			 
		
		 
// 8. 	Retour page d'administration des critères :
		 
		 c_Page.clickAnnuler();
		 
		 // checks the text "Types de critères Liste"
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
	
		 
		 // Checks the text "Critère - Test bouton [Sauver et continuer]" in table
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")));

		 //Thread.sleep(1000);
		 assertTrue("Text not present in table",driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")).getText().equals("Critère - Test bouton [Sauver et continuer]"));

		 
		 
//9.Modifier un critère - accès formulaire de modification - Colonne "Opération" :
		 
		 c_Page.clickModifier();
		 
		 
		 //verify and Displays the page with the title text "Modifier Type de critère: Critère - Test bouton [Sauver et continuer]"
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")));
		 assertTrue("Text not present",driver.findElement(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")).getText().equals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer]"));

//10. Modifier un critère -  Bouton [Annuler] :
		 
		 // changes the name in the name field
		 
		 c_Page.fillName("Critère - Test bouton [Sauver et continuer] 2");
		 
		 // click the button Annuler
		 c_Page.clickAnnuler();
		 
		 // returns to the administrative page
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
		 
		 
	
		
//11.	Modifier un critère - accès formulaire de modification - Colonne "Nom" :	
	
		 // clicks the modify icon in the table
	    c_Page.clickTestBoutonSauverEtContinuer();
	    
	    // displays the page with text "Modifier Type de critère: Critère - Test bouton [Sauver et continuer]"
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")));
	    assertTrue("Text not present",driver.findElement(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer]')]")).getText().equals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer]"));
		
	
//12.. Modifier un critère - modification du nom :
		 
	     // Enters the new name 
		 c_Page.fillName("Critère - Test bouton [Sauver et continuer] 2");
		 
		 // clicks randomly somewhere 
		 c_Page.clickRandom();
		 
		 //Displays the page with the text "Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2"
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2')]")));
		 assertTrue("Text not present",driver.findElement(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2')]")).getText().equals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2"));
			
	

	
	
// 13.Modifier un critère - bouton [Sauver et continuer] :
		 
		 // click save and continue
		 c_Page.clickSaveAndContinue();
		 
		 //Verify the text message " "Type de critère "Critère - Test bouton [Sauver et continuer] 2" enregistré".3
		 assertTrue("button  not present",driver.findElement(By.xpath("//span[contains(text(),'Critère - Test bouton [Sauver et continuer] 2')]")).getText().equals("Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" enregistré"));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2')]")));
		
		 // verify the text message ""Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2"
		 assertTrue("Text not present",driver.findElement(By.xpath("//td[contains(text(),'Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2')]")).getText().equals("Modifier Type de critère: Critère - Test bouton [Sauver et continuer] 2"));
			
//14. Retour page d'administration des critères :
		 
		 // clicks the button Annuler
		 c_Page.clickAnnuler();
		 
		 //Returns to administrative page and verify the text ""Types de critères Liste".

		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Types de critères Liste')]")));

		 assertTrue("Text not present",driver.findElement(By.xpath("//div[contains(text(),'Types de critères Liste')]")).getText().equals("Types de critères Liste"));
			
		 assertTrue("Text not present",driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")).getText().equals("Critère - Test bouton [Sauver et continuer] 2"));

//15.Supprimer un critère - Pop-up de confirmation :


		 
		 
		 // Clicks teh pubble icon to delete the criteria
		 c_Page.clicSupprimer();
		 
		 // verify the pop-up 
		 
		 assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-window-modal-header z-window-modal-header-move']")).getText().equals("Confirmer"));
		 

		
		 // verify the text in pop-up
		 assertTrue("Text not present",driver.findElement(By.xpath("//span[contains(text(),'Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?')]")).getText().equals("Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?"));
		 
		 // verify the button OK and Annuler in popup
		 assertTrue("Button not present",driver.findElement(By.xpath("//td[contains(text(),'OK')]")).getText().equals("OK"));
		  

		  assertTrue("Button not present",driver.findElement(By.xpath("//span[@class='z-messagebox-btn z-button']/following::td[@class='z-button-cm']")).getText().equals("Annuler"));

//16. Supprimer un critère - Bouton [Annuler] :
		  
		  	
		  	// clicks Annuler button on the pop-up
		  	c_Page.clickPopUpAnnuler();
			 
			
			 
			 //boolean isEmpty = driver.findElements(By.xpath("//div[@class='z-window-modal-header z-window-modal-header-move']")).isEmpty();
			 
			 
			 Thread.sleep(100);

			 assertTrue("Pop up not present", driver.findElements(By.xpath("//div[@class='z-window-modal-header z-window-modal-header-move']")).isEmpty());
			 
			 assertTrue("Text not present",driver.findElement(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")).getText().equals("Critère - Test bouton [Sauver et continuer] 2"));

//17.Supprimer un critère - Pop-up de confirmation :
				
			 //click the icon pubble on the administrative page
			  c_Page.clicSupprimer();
				 
				 
			  assertTrue("Text not present",driver.findElement(By.xpath("//div[@class='z-window-modal-header z-window-modal-header-move']")).getText().equals("Confirmer"));
			
			  assertTrue("Text not present",driver.findElement(By.xpath("//span[contains(text(),'Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?')]")).getText().equals("Supprimer Type de critère \"Critère - Test bouton [Sauver et continuer] 2\". Êtes-vous sûr ?"));

			  assertTrue("Button not present",driver.findElement(By.xpath("//td[contains(text(),'OK')]")).getText().equals("OK"));
			  
			 // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='z-messagebox-btn z-button']/following::td[@class='z-button-cm']")));

			  assertTrue("Button not present",driver.findElement(By.xpath("//span[@class='z-messagebox-btn z-button']/following::td[@class='z-button-cm']")).getText().equals("Annuler"));


//18.Dans la pop-up "Confirmer", cliquer sur le bouton [OK].
			  
			  // click OK to confirm the delete

			  c_Page.clickOK();
			  
			  Thread.sleep(100);
			  
			  assertTrue("button  not present",driver.findElement(By.xpath("//span[contains(text(),'Critère - Test bouton [Sauver et continuer] 2')]")).getText().equals("Type de critère \"Critère - Test bouton [Sauver et continuer] 2\" supprimé"));

			  assertTrue("Pop up not present", driver.findElements(By.xpath("//span[@title='Critère - Test bouton [Sauver et continuer]']")).isEmpty());

			  
		 
	}
}	