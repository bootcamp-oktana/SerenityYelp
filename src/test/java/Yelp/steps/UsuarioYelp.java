package Yelp.steps;

import Yelp.pageobject.PaginaPrincipal;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;

public class UsuarioYelp extends ScenarioActor{

    String actor;

    @Steps(shared=true)
    PaginaPrincipal paginaPrincipal;

    public void navigatesTo() {
        paginaPrincipal.setDefaultBaseUrl("https://www.yelp.com/");
        paginaPrincipal.open();
    }

    public void searchFor() {
        paginaPrincipal.searchRestaurant();
    }

    public void searchForRestaurant(String restaurant) {
        paginaPrincipal.searchTypeOfRestaurant(restaurant);
    }

    public void filterBy(String filter) {
        paginaPrincipal.selectFilter(filter);
    }

    public void enterFirstResult() {
        paginaPrincipal.selectFirstResult();
    }

    public void viewDetails() {
        paginaPrincipal.getDetails();
    }

}