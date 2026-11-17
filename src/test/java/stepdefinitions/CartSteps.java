package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutPage;

/** Steps for the small cart window (opened with the bag icon). */
public class CartSteps {

    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @When("the user opens the cart")
    public void the_user_opens_the_cart() {
        cartPage.openCart();
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String productName) {
        assertTrue("'" + productName + "' not found in the cart. Cart shows: " + cartPage.getCartItemTexts(),
                cartPage.isProductInCart(productName));
    }

    @When("the user removes the product from the cart")
    public void the_user_removes_the_product_from_the_cart() {
        cartPage.removeProduct();
    }

    @Then("the cart should display the message {string}")
    public void the_cart_should_display_the_message(String expectedMessage) {
        assertEquals("Wrong empty-cart message", expectedMessage, cartPage.getEmptyCartMessage());
    }

    @When("the user clicks proceed to checkout")
    public void the_user_clicks_proceed_to_checkout() {
        cartPage.clickProceedToCheckout();
    }

    @Then("the checkout page should list {string}")
    public void the_checkout_page_should_list(String productName) {
        String productsText = checkoutPage.getProductsText();
        assertTrue("'" + productName + "' not found on the checkout page. Page shows: " + productsText,
                productsText.contains(productName));
    }
}
