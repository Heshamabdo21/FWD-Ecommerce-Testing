package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.homePage;
import org.example.pages.loginPage;
import org.example.pages.productPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.example.pages.productGalleryPage;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class D04_searchStepDef {

    loginPage login = new loginPage();
    homePage  home  = new homePage();
    productGalleryPage gallery = new productGalleryPage();
    productPage product = new productPage();

    @Given("user has logged in")
    public void login() {
    	home.homeLink().click();
    	WebDriverWait wait = new WebDriverWait (Hooks.driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-login")));
        login.loginLink().click();
        login.login("test@example.com","P@ssw0rd");
    }
    @And("user is at home page")
    public void toHome() {
        home.homeLink().click();
    	SoftAssert soft = new SoftAssert();
        soft.assertTrue( Hooks.driver.getCurrentUrl().contains("https://demo.nopcommerce.com/") );
        soft.assertTrue( home.accountTab().isDisplayed() );
        soft.assertAll();
    }
    @When("^user enter product name as \"(.*)\"$")
    public void typeProductName(String item) {
        home.searchTextbox().sendKeys(item);
    }
    @And("user press enter")
    public void pressEnter() {
        home.searchTextbox().sendKeys(Keys.ENTER);
    }
    @Then("^search results should appear and contain \"(.*)\"$")
    public void assertSucess(String item) throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue( Hooks.driver.getCurrentUrl().contains("https://demo.nopcommerce.com/search?q="+item));
    	WebDriverWait wait = new WebDriverWait (Hooks.driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class=\"product-title\"] > a")));
        List<WebElement> products = gallery.products();

 		// Call moveToElement() method of actions class to move mouse cursor to a WebElement A. 
        for (WebElement product : products) {
        	Thread.sleep(1000);
      	   Actions actions = new Actions(Hooks.driver); 
  		   actions.moveToElement(gallery.productTitle(product)).perform(); 
            soft.assertTrue( gallery.productTitle(product).getText().toLowerCase().trim().contains(item) );
        }
        soft.assertAll();
    }
    @When("^user enter product sku as \"(.*)\"$")
    public void typeProductSKU(String sku) {
        home.searchTextbox().sendKeys(sku);
    }

    @Then("search result should contain {string}")
    public void searchResultShouldContain(String item) {
        List<WebElement> products = gallery.products();
  	   Actions actions = new Actions(Hooks.driver); 

        for (WebElement productitem : products) {
        	productitem.click();
   		   actions.moveToElement(product.getSKU()).perform(); 

            System.out.println(this.product.getSKU().getText());
            System.out.println(item);
            Assert.assertTrue( this.product.getSKU().getText().toLowerCase().trim().contains(item.toLowerCase()) );
        }
    }
}
