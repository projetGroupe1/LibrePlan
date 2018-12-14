package projectPlan.groupe1;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class AdvancementTypeCreationTest {

	WebDriver driver;
	//DataBaseConnection con = new DataBaseConnection();
	Actions actions;
	LoginPage lp; 
	AdvancementTypeList typeList;
	private String profile1 = "admin";

	@Before
	public void preTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions = new Actions(driver);
		lp= new LoginPage(driver);
		
	
	}

	@Test
	public void CreationTypeTest ()throws Exception {
		//"pas de test" 1 - connexion
		driver.get(lp.URL);
		
		lp.fillLoginForm(profile1, profile1);
		PlannerPage pp = lp.submitLoginForm();
	
		//"pas de test" 2 - access from menu bar 
		AdvancementTypeList typeList = pp.accessAdvancementTypes(driver);
			Thread.sleep(3000);
		
		//"pas de test" 2 - to check rowsTitles
		WebElement titleTab = driver.findElement(By.xpath("//div[@class = 'z-window-embedded-header']"));
		assertTrue(titleTab.getText() != null);
		
		WebElement rowName = driver.findElement(By.xpath("//div[@class = 'z-column-cnt'][text() = 'Nom']"));
		assertTrue(rowName.getText() != null);
	
		WebElement rowActivated = driver.findElement(By.xpath("//div[@class = 'z-column-cnt'][text() = 'Activé']"));
		assertTrue (rowActivated.getText() != null);
		
		WebElement rowPreset = driver.findElement(By.xpath("//div[@class = 'z-column-cnt'][text() = 'Prédéfini']"));
		assertTrue (rowPreset.getText() != null);
		
		WebElement rowOperations = driver.findElement(By.xpath("//div[@class = 'z-column-cnt'][text() = 'Opérations']"));
		assertTrue (rowOperations.getText() != null);
		
		//"pas de test" 3 -clic createButton
		AdvancementTypeCreation typeCreation = typeList.createType();
		
		//"pas de test" 3 -clic createButton
		System.out.println(typeCreation.getTitle());
	}

}
