package projectPlan.groupe1;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancementTypeTest {

	WebDriver driver;
	LoginPage loginPage;
	AdvancementTypeList typeList;
	DataBaseConnection db;

	private String profil = "admin";
	private String test1 = "Type avancement - Test 1";
	private String test2 = "Type avancement - Test 2";

	// variables messages
	private String messageCheckboxActivé = "la checkbox n'est pas inactive";
	private String messageButton = "bouton inexistant : ";
	private String messageNomCol = "Il n'a pas la colonne : ";
	private String messageTest1saved = "Type d'avancement \"Type avancement - Test 1\" enregistré";
	private String messageTest2saved = "Type d'avancement \"Type avancement - Test 2\" enregistré";
	private String titleMessageTest2 = "Modifier Type d'avancement: Type avancement - Test 2";

	// variables noms de page web
	private String varPageTypeAvancementList = "Types d'avancement Liste";
	private String varPageCreateType = "Créer Type d'avancement";

	@Before
	public void preTest() {
		//instanciation  drivers and loginPage(accueil)
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		loginPage = new LoginPage(driver);

	}

	@Test
	public void CreationTypeTest() throws Exception {

		/****************
		 * Pas de test 1 "Connexion à l'applictaion - Profil Admin"
		 ****************/

		driver.get(loginPage.URL);
		//call  method to fill the connexionPage 
		loginPage.fillLoginForm(profil, profil);

		// to submit and go to plannePage 
		PlannerPage plannerPage = loginPage.submitLoginForm();

		/****************
		 * pas de test 2 "Accéder à la page de gestion des types d'avancement"
		 **************/

		// access from menu bar
		AdvancementTypeList typeList = plannerPage.accessAdvancementTypes(driver);

		// to check Table and rows Titles
		assertEquals("Vous n'êtes pas sur la page" + varPageTypeAvancementList, "Types d'avancement Liste",
				typeList.getTitle());
		assertEquals(messageNomCol + "nom", "Nom", typeList.getName());
		assertEquals(messageNomCol + "activé", "Activé", typeList.getActif());
		assertEquals(messageNomCol + "prédéfini", "Prédéfini", typeList.getPreset());
		assertEquals(messageNomCol + "opérations", "Opérations", typeList.getOperations());
		assertEquals(messageButton +"créer", "Créer", typeList.getCreateButton());

		/*************
		 * Pas de test 3 "Créer un type d'avancement - Accès auformulaire de création"
		 ************/

		// click on createButton
		AdvancementTypeCreation typeCreation = typeList.createType();

		// to check title
		assertEquals("Vous n'êtes pas sur la page création", varPageCreateType, typeCreation.getTitle());

		// to check inputs
		assertEquals(messageNomCol + "nom d'unité", "Nom d'unité", typeCreation.getRowName());
		assertEquals("la valeur max par defaut est fausse", "100,00", typeCreation.getMaxValueVal());
		assertEquals("la précision par défaut est fausse", "0,1000", typeCreation.getPrecisionVal());
		assertEquals("pas trouvé par défaut User", "User", typeCreation.getType());

		// to check checkboxes
		assertTrue("La checkbox n'est pas n'est pas cochée par défaut", typeCreation.getChecboxActif().isSelected());
		assertFalse("La checkbox est  cochée par défaut", typeCreation.getcheckboxPourcentage().isSelected());

		// to check buttons
		assertTrue(messageButton, typeCreation.getsaveButton() != null);
		assertTrue(messageButton, typeCreation.getsaveAndContinue() != null);
		assertTrue(messageButton, typeCreation.getCancelButton() != null);

		/**************
		 * Pas de test 4 "Créer un type d'avancement - sans pourcentage"
		 *************/

		// to fill the form
		typeList = typeCreation.fillTheForm();

		// //to check if we are in TypeAdvancementList
		assertEquals(varPageTypeAvancementList, typeList.getTitle());

		// to check the message
		assertEquals(messageTest1saved, typeList.getmessages(messageTest1saved));

		// to check the tab
		assertEquals("nomType non trouvé", test1, typeList.findNameInTab(test1));
		assertFalse("La checkbox Activité est cochée", typeList.findActivateInTab(test1).isSelected());
		assertTrue(messageCheckboxActivé,
				typeList.getCheckActivatedDisabled().getAttribute("disabled") != null);
		assertFalse("La checkbox Activité est cochée", typeList.findActivateInTab(test1).isSelected());
		assertTrue(messageCheckboxActivé,
				typeList.getCheckPredefiniDisabled().getAttribute("disabled") != null);
		assertTrue("bouton modifié non trouvé", typeList.findLastColInTab(test1)
				.findElement(By.xpath("descendant::span[@title='Modifier']")).isDisplayed());
		assertTrue("bouton supprimé non trouvé", typeList.findLastColInTab(test1)
				.findElement(By.xpath("descendant::span[@title='Supprimer']")).isDisplayed());

		/**************
		 * Pas de test 5 "Créer un type d'avancement - Accès formulaire "
		 *************/

		// click on createButton
		typeCreation = typeList.createType();

		// to check title
		assertEquals("Vous n'êtes pas sur la page création", "Créer Type d'avancement", typeCreation.getTitle());

		/*****************
		 * Pas de test 6 "Créer un type d'avancement - sanspourcentage 1/2 "
		 *****************/

		// to fill before verify "champ valeur maximum"
		typeCreation.fillWithoutSend();
		assertTrue("la case Valeur max n'est pas désactivé",
				typeCreation.getMaxValueElement().getAttribute("disabled") != null);

		/****************
		 * pas de test 7 "Créer un type d'avancement - sans pourcentage 2/2 "
		 ***************/

		// to validate and continue
		typeCreation.saveTheForm2();

		// to check the message
		assertEquals("Message de confirmation enregistré non trouvé", messageTest2saved,
				typeList.getmessages(messageTest2saved));
		assertEquals("Titre Modifier non trouvé", titleMessageTest2, typeCreation.getTitlePageTest2());

		/****************
		 * pas de test 8 "Retour à la page de gestion des types d'avancement "
		 ****************/
		// to click on cancel
		typeList = typeCreation.cancel();

		// to check if we are in pageList
		assertEquals("Vous n'êtes pas sur la page type d'avancement liste", "Types d'avancement Liste",
				typeList.getTitle());

		/****************
		 * pas de test 9 "Conformité du type d'avancement ajouté "
		 ****************/
		// to check the tab
		assertEquals("nomType non trouvé", test2, typeList.findNameInTab(test2));
		assertFalse("La checkbox Activité n'est pas cochée", typeList.findActivateInTab(test2)
				.findElement(By.xpath("following::input")).getAttribute("disabled") == null);
		assertTrue("la checkbox Activité n'est pas inaccessible", typeList.findActivateInTab(test2)
				.findElement(By.xpath("following::input")).getAttribute("disabled") != null);
		assertFalse("la checkbox Prédéfini est coché", typeList.findPresetInTab(test2).isSelected());
		assertTrue("la checkbox Prédéfini n'est pas inaccessible",
				typeList.getCheckPredefiniDisabled().getAttribute("disabled") != null);
		assertTrue("bouton modifié non trouvé", typeList.findLastColInTab(test2)
				.findElement(By.xpath("descendant::span[@title='Modifier']")).isDisplayed());
		assertTrue("bouton supprimé non trouvé", typeList.findLastColInTab(test2)
				.findElement(By.xpath("descendant::span[@title='Supprimer']")).isDisplayed());

		// String request = "DELETE FROM advance_type WHERE unit_name = " + test1;
		// String request2 = "DELETE FROM advance_type WHERE unit_name = " + test1;
		// PreparedStatement state = (PreparedStatement)
		// db.getInstance().prepareStatement(request);
		// state.executeQuery();

	}

}
