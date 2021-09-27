package Yelp.pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PaginaPrincipal extends PageObject {

    @FindBy(xpath="//*[@id='find_desc']")
    WebElementFacade findButton;

    @FindBy(xpath="//*[@data-suggest-query='Restaurants']")
    WebElementFacade RestaurantsItem;

    @FindBy(xpath = "//input[@id='search_description']")
    WebElementFacade searchBox;

    @FindBy(xpath="//form[@id='header_find_form']//button[@value='submit']")
    WebElementFacade searchButton;

    @FindBy(xpath="//p[contains(text(),'Neighborhoods')]//following::a[1]")
    WebElementFacade seeAllNeighbor;

    @FindBy(xpath="//span[contains(text(),'Search')]")
    WebElementFacade searchFilterButton;

    @FindBy(xpath="//*[contains(text(),'Phone number')]//following-sibling::p")
    WebElementFacade restaurantPhone;

    @FindBy(xpath="//*[contains(text(),'Get Directions')]//parent::p//following-sibling::p")
    WebElementFacade restaurantAddress;

    @FindBy(xpath="//span[contains(text(),'Yelp Sort')]/parent::button")
    WebElementFacade reviewSortByButton;

    @FindBy(xpath="//*[contains(text(),'Oldest First')]//ancestor::button")
    WebElementFacade oldesFirstButtn;


    static Integer count;
    static List<WebElementFacade> myListOfRestaurants;
    static List<WebElementFacade> myReviewsOfRestaurant;

    static String xpathNombreRestaurante="//*[contains(@class,'ResultsContainer')]//descendant::h4";


    public void searchRestaurant() {
        findButton.click();
        RestaurantsItem.click();
    }

    public void searchTypeOfRestaurant(String restaurant) {
        count=0;
        searchBox.clear();
        searchBox.sendKeys(restaurant);
        searchButton.click();
        myListOfRestaurants = withTimeoutOf(20, SECONDS).findAll(By.xpath(xpathNombreRestaurante));
        numSearchPerPage(myListOfRestaurants);
        String resultados="El numero de resultados en la primera pagina es: " +count;
        System.out.println(resultados);
        Serenity.recordReportData().withTitle("Total de Resultados").andContents(resultados);
    }

    public void selectFilter(String neighbordhood) {
        seeAllNeighbor.click();
        String filterxpath="//span[contains(text(),'"+neighbordhood+"')]//parent::div//parent::div//preceding::div[2]";
        WebElement filterNeighboorH = withTimeoutOf(10,SECONDS).find(By.xpath(filterxpath));
        filterNeighboorH.click();
        searchFilterButton.click();
    }

    public void selectFirstResult() {
        myListOfRestaurants = withTimeoutOf(10, SECONDS).findAll(By.xpath(xpathNombreRestaurante ));
        for(WebElementFacade inputElement : myListOfRestaurants)
        {
            if(inputElement.getText().contains(".")) {
                System.out.println("El primer resultado es: " + inputElement.getText());
                Serenity.recordReportData().withTitle("Restaurante").andContents(inputElement.getText());
                inputElement.click();
                break;}
        }
    }

    public void getDetails() {
        String phone="El telefono del restaurante es: " + restaurantPhone.getText();
        String address="La direccion del restaurante es: " + restaurantAddress.getText();
        Serenity.recordReportData().withTitle("Telefono del restaurante").andContents(restaurantPhone.getText());
        Serenity.recordReportData().withTitle("Direccion del restaurante").andContents(restaurantAddress.getText());
        reviewSortByButton.click();
        oldesFirstButtn.click();
        String xpathReviews = "//p[@class='comment__373c0__Nsutg css-n6i4z7']";
        myReviewsOfRestaurant = withTimeoutOf(10, SECONDS).findAll(By.xpath(xpathReviews));
        String firstReview="El primer review mas antiguo es: " + myReviewsOfRestaurant.get(0).getText();
        String secondReview="El segundo review mas antiguo es: " + myReviewsOfRestaurant.get(1).getText();
        String thirdReview="El tercer review mas antiguo es: " + myReviewsOfRestaurant.get(2).getText();
        String details = phone + "\n" + address + "\n" + firstReview +"\n" + secondReview +
                "\n" + thirdReview;
        Serenity.recordReportData().withTitle("Details y Reviews").andContents(details);
    }

    public void numSearchPerPage(List<WebElementFacade> lista) {
        count=0;
        for(WebElementFacade inputElement : lista)
        {
            if(inputElement.getText().contains(".")) {
                count=count+1;} }
    }

}