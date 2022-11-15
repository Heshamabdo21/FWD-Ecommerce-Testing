package org.example.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.homePage;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class D06_homeSlidersStepDef {

    homePage home = new homePage();

    @When("user click on first slider")
    public void clickFirstSlider() {
    	Actions Action = new Actions(Hooks.driver);
    	Action.moveToElement(home.firstSliderController()).perform();
        home.firstSliderController().click();
        home.firstSlider().click();
    }
    @When("user click on second slider")
    public void clickSecondSlider() {
    	Actions Action = new Actions(Hooks.driver);

    	Action.moveToElement(home.secondSliderController()).perform();

        home.secondSliderController().click();
        home.secondSlider().click();
    }
    @Then("user should be directed to {string}")
    public void assertURL(String url) {
        Assert.assertTrue( Hooks.driver.getCurrentUrl().contains(url) );
    }
}
