package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.example.pages.homePage;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;

public class D07_followUsStepDef {

    homePage home = new homePage();
Actions action = new Actions(Hooks.driver);
    @When("clicks on facebook link")
    public void openFacebook() {
    	action.moveToElement(home.facebookIcon()).perform();
        home.facebookIcon().click();
    }
    @And("user switches to new tab")
    public void switchTab() {
        ArrayList<String> tabs = new ArrayList<String> (Hooks.driver.getWindowHandles());
        if(tabs.size() > 1)
            Hooks.driver.switchTo().window(tabs.get(1));
    }
    @Then("page url should be {string}")
    public void assertURL(String url) {
        Assert.assertTrue( Hooks.driver.getCurrentUrl().contains(url) );
    }
    @When("clicks on twitter link")
    public void openTwitter() {
    	action.moveToElement(home.twitterIcon()).perform();

        home.twitterIcon().click();
    }
    @When("clicks on rss link")
    public void openRSS() {
    	action.moveToElement(home.rssIcon()).perform();

        home.rssIcon().click();
    }
    @When("clicks on youtube link")
    public void openYoutube() {
    	action.moveToElement(home.youtubeIcon()).perform();

        home.youtubeIcon().click();
    }
}
