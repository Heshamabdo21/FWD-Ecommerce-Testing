package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.homePage;
import org.example.pages.productGalleryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Random;

public class D05_hoverCategoriesStepDef {

    WebElement randomItem = null;
    String title = null;
    homePage home = new homePage();
    productGalleryPage gallery = new productGalleryPage();

    @When("user hovers over any random category")
    public void chooseRandomCategory() {

        Actions action = new Actions(Hooks.driver);

        do {
            randomItem = home.categories().get( new Random().nextInt(home.categories().size()) );
        }while(randomItem.getText() == "");
        action.moveToElement(randomItem).perform();
    }
    @And("user clicks sub-category if found")
    public void chooseRandomSubCategory() {

        Actions action = new Actions(Hooks.driver);

        if(randomItem.findElements(By.tagName("li")).size() > 0){
            randomItem = home.subCategories(randomItem).get( new Random().nextInt(home.subCategories(randomItem).size()) );
            title = randomItem.getText();
            action.moveToElement(randomItem).perform();
            randomItem.click();
        }else{
            title = randomItem.getText();
            randomItem.click();
        }
    }
    @Then("page title should contain category or sub-category name")
    public void assertSucess() {
        Assert.assertTrue( gallery.pageTitle().getText().toLowerCase().trim().contains( title.toLowerCase().trim() ), "Page title doesn't match selected category");
    }
}
