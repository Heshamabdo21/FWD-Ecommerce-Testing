package org.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.homePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class D03_currenciesStepDef {
    homePage home = new homePage();

    @Given("user go to home page")
    public void goHome() {
      /*  SoftAssert soft = new SoftAssert();
        soft.assertTrue( Hooks.driver.getCurrentUrl().contains("https://demo.nopcommerce.com/") );
        soft.assertTrue( home.accountTab().isDisplayed() );
        soft.assertAll();*/
        
        home.homeLink().click();
    }
    @When("select Euro currency from the dropdown")
    public void changeCurrency() {
        home.currencyMenu().sendKeys("Euro");;
        home.currencyMenu().sendKeys(Keys.ENTER);
    }
    @Then("Euro Symbol is shown on the 4 products displayed in Home page")
    public void assertCurrencyChange() {
        List<WebElement> products = home.productCard();
        SoftAssert soft = new SoftAssert();
        for(WebElement product : products) {
        	home.MoveToProdcutPrice(product);
            soft.assertTrue( home.productPrice(product).getText().contains("€"));
        }
        soft.assertAll();
    }
}
