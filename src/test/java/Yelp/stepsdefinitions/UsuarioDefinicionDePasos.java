package Yelp.stepsdefinitions;

import Yelp.steps.UsuarioYelp;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class UsuarioDefinicionDePasos {

    @Steps(shared=true)
    UsuarioYelp usuario;

    @Given("^user navigates to https://www.yelp.com/$")
    public void userNavigateToYelp() {
        usuario.navigatesTo();
        Serenity.takeScreenshot();
    }

    @And("^selects find Restaurants$")
    public void userSearch() {
        usuario.searchFor();
    }

    @Given("^User search restaurant (.*)$")
    public void userSearchRestaurant(String restaurant) {
        usuario.searchForRestaurant(restaurant);
        Serenity.takeScreenshot();
    }

    @When("^filters by (.*)$")
    public void userSelectFilter(String filter){
        usuario.filterBy(filter);
        Serenity.takeScreenshot();
    }

    @And("^select the first search result$")
    public void userSelectFirstResult(){
        usuario.enterFirstResult();
        Serenity.takeScreenshot();
    }

    @Then("^User views restaurant information$")
    public void userViewDetails() {
        usuario.viewDetails();
        Serenity.takeScreenshot();
    }

}