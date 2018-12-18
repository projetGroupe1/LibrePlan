package projectPlan.groupe1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
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
	
	
	private String profil = "admin";
	
	//variables messages
	private String messageCheckboxActivé = "la checkbox n'est pas inactive";
	private String messageButton = "bouton inexistant";
	
	//variables noms de page
	private String pageTypeAvancementList = "Types d'avancement Liste";
	private String pageCreateType = "Créer Type d'avancement";

	@Before
	public void preTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions = new Actions(driver);
		lp= new LoginPage(driver);
<<<<<<< HEAD
=======
		
		
	
>>>>>>> 6530106be531449b47dccedfc2592508f6621142
	}

	@Test
	public void CreationTypeTest ()throws Exception {
		
		//****************pas de test 1 "Connexion à l'applictaion - Profil Admin" **************** 
		// connexion
		driver.get(lp.URL);
		
		lp.fillLoginForm(profil, profil);
		PlannerPage pp = lp.submitLoginForm();
	
		
		//****************pas de test 2  "Accéder à la page de gestion des types d'avancement""****************
		// access from menu bar 
		AdvancementTypeList typeList = pp.accessAdvancementTypes(driver);
		Thread.sleep(3000);
		
		//to check Table and rows Titles
		assertEquals(pageTypeAvancementList, typeList.getTItle());
		assertTrue(typeList.getName()!= null);
		assertTrue(typeList.getActif() !=null)
		assertTrue (typeList.getPreset() != null);
		assertTrue (typeList.getOperations() != null);
		assertTrue (typeList.getCreateButton() != null);
		
		
		//****************pas de test 3 "Créer un type d'avancement - Accès au formulaire de création" ****************
		
		//click on createButton
		AdvancementTypeCreation typeCreation = typeList.createType();
		
		
		// to check title
		assertEquals(pageCreateType, typeCreation.getTitle());
		//to check inputs
		assertTrue("L'input n'est pas vide",  typeCreation.getName() == null);
		assertEquals("100,00", typeCreation.getMaxValue());
		assertEquals("0,1000", typeCreation.getPrecision());
		assertEquals("User", typeCreation.getType());
		
		
		//to check checkboxes
		assertTrue ("La checkbox n'est pas n'est pas cochée par défaut", typeCreation.getChecboxActif().isSelected());
		assertFalse ("La checkbox est  cochée par défaut", typeCreation.getcheckboxPourcentage().isSelected() );
		
		//to check buttons
		assertTrue(messageButton, typeCreation.getsaveButton() != null);
		assertTrue(messageButton, typeCreation.getsaveAndContinue() != null);
		assertTrue(messageButton, typeCreation.getCancelButton() !=null);
		
		
		//****************pas de test 4 "Créer un type d'avancement - sans pourcentage "**************** 
		//to fill the form
		typeList = typeCreation.fillTheForm();
		
		//to check if we are in TypeAdvancementList
		assertEquals( pageTypeAvancementList, typeList.getTItle());
		
		//to check the message
		String messageAvancement = "Type d'avancement 'Type avancement - Test1' est enregistré";
		assertEquals(messageAvancement, typeList.getmessageTypeAdvancement());
		
		//type d'avancement créé test présent dans tableau faire boucle sur tableau
		
		
		//****************pas de test 5 "Créer un type d'avancement - Accès au formulaire "**************** 
		//click on createButton
		typeCreation = typeList.createType();
		
		
		// to check title
		assertEquals (pageCreateType, typeCreation.getTitle());
		
		//****************pas de test 6 "Créer un type d'avancement - sans pourcentage 1/2 "**************** 
		typeCreation.fillWithoutSend();
		assertTrue(messageCheckboxActivé, typeCreation.checkboxPourcentage.isEnabled());
		
		//****************pas de test 7 "Créer un type d'avancement - sans pourcentage 2/2 "****************
		
		typeCreation.fillTheForm2();
		String messageAvancement2 = "Type d'avancement 'Type avancement - Test2' est enregistré";
		assertEquals(messageAvancement2, typeCreation.getMessageTypeAdvancement2());
		String messageTitle = "Modifier Type d'avancement: Type d'avancement - Test 2";
		assertEquals(messageTitle, typeCreation.getTitle());
		
		//****************pas de test 8 "Retour à la page de gestion des types d'avancement "****************
		typeList = typeCreation.Cancel();
		assertEquals( pageTypeAvancementList, typeList.getTItle());
		//****************pas de test 9 "Conformité du type d'avancement ajouté  "****************
		//parcourir tableau
	}

}
