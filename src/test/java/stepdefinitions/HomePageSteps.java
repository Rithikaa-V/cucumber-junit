package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

/**
 * Steps for the home page. Each step only calls a method of the page class
 * and checks the result. Locators and clicking details live in HomePage.
 *
 * JUnit 4 note: assertEquals(message, EXPECTED, ACTUAL) - the message comes first.
 */
public class HomePageSteps {

    private final HomePage homePage = new HomePage();

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        homePage.open();
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedText) {
        String actualTitle = homePage.getPageTitle();
        assertTrue("Title was '" + actualTitle + "' but should contain '" + expectedText + "'",
                actualTitle.contains(expectedText));
    }

    @Then("the logo should be displayed")
    public void the_logo_should_be_displayed() {
        assertTrue("Logo is not displayed", homePage.isLogoDisplayed());
    }

    @Then("the search box should be displayed")
    public void the_search_box_should_be_displayed() {
        assertTrue("Search box is not displayed", homePage.isSearchBarDisplayed());
    }

    @Then("the Top Deals link should be displayed")
    public void the_top_deals_link_should_be_displayed() {
        assertTrue("Top Deals link is not displayed", homePage.isTopDealsLinkDisplayed());
    }

    @Then("the cart icon should be displayed")
    public void the_cart_icon_should_be_displayed() {
        assertTrue("Cart icon is not displayed", homePage.isCartIconDisplayed());
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String productName) {
        homePage.searchFor(productName);
    }

    @Then("only the product {string} should be listed")
    public void only_the_product_should_be_listed(String expectedProduct) {
        assertEquals("Wrong product listed after the search", expectedProduct, homePage.waitForSingleProduct());
    }

    @When("the user clicks the increment button")
    public void the_user_clicks_the_increment_button() {
        homePage.clickIncrement();
    }

    @When("the user clicks the add to cart button")
    public void the_user_clicks_the_add_to_cart_button() {
        homePage.clickAddToCart();
    }

    /** A shortcut step used in the Background of other features: search, then add one product. */
    @Given("the user has added {string} to the cart")
    public void the_user_has_added_to_the_cart(String productName) {
        homePage.searchFor(productName);
        homePage.waitForSingleProduct();
        homePage.clickAddToCart();
        homePage.waitForCartItemCount(1);
    }

    @Then("the cart should show {int} item(s)")
    public void the_cart_should_show_items(int expectedCount) {
        homePage.waitForCartItemCount(expectedCount);
        assertEquals("Wrong number of items in the cart", expectedCount, homePage.getCartItemCount());
    }
}
