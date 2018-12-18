package projectPlan.groupe1;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateProjectTest {
	WebDriver driver;
	//DataBaseConnection con = new DataBaseConnection();
	Actions actions;
	LoginPage lp; 
	private String profil = "admin";
	
	//not finished
	@Ignore
	public void preTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions = new Actions(driver);
		lp= new LoginPage(driver);
		
		
	
	}
	@Ignore
	public void test() throws InterruptedException {
		//****************pas de test 1 "Connexion à l'applictaion - Profil Admin" **************** 
				// connexion
				driver.get(lp.URL);
				
				lp.fillLoginForm(profil, profil);
				PlannerPage pp = lp.submitLoginForm();
				
				//****************pas de test 2 "Accéder au formulaire de création d'un projet" **************** 
			
				AdvancementTypeList typeList = pp.accessAdvancementTypes(driver);
				Thread.sleep(3000);
				
				
	}

}
