package bestbuy.cucumber.steps;

import bestbuy.bestbuySteps.ProductInfo.ProductSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class ProductCurdTestSteps {

    static ValidatableResponse response;
    static int productId;

    @Steps
    ProductSteps productSteps;

    @When("^I sends the Post Request for create the Product Data$")
    public void iSendsThePostRequestForCreateTheProductData() {
    }

    @And("^I insert the name \"([^\"]*)\",type \"([^\"]*)\",price (\\d+),shipping (\\d+),upc \"([^\"]*)\",description \"([^\"]*)\",manufacturer \"([^\"]*)\",model \"([^\"]*)\",url \"([^\"]*)\",image \"([^\"]*)\"$")
    public void iInsertTheNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        response = productSteps.CreateProductRecord(name,type,price,shipping,upc,description,manufacturer,model,url,image);

    }

    @Then("^I verify the status code (\\d+)$")
    public void iVerifyTheStatusCode(int statusCode) {
        response.log().all().
                statusCode(statusCode);
    }

    @And("^I get the id of new created product data$")
    public void iGetTheIdOfNewCreatedProductData() {
        productId = response.extract().path("id");
    }

    @When("^I sends a GET request for fatching product data by given ID$")
    public void iSendsAGETRequestForFatchingProductDataByGivenID() {
        HashMap<String,?> productList= productSteps.verfiyProductList(productId);

    }

    @Then("^I verify the name is \"([^\"]*)\"$")
    public void iVerifyTheNameIs(String name) {
        response.body("name",equalTo(name));
    }

    @When("^I send the Put Request for updating the Product data$")
    public void iSendThePutRequestForUpdatingTheProductData() {
    }

    @And("^I update the name \"([^\"]*)\",type \"([^\"]*)\",price (\\d+),shipping (\\d+)$")
    public void iUpdateTheNameTypePriceShipping(String name, String type, int price, int shipping) {
        response = productSteps.updateProductRecord(productId,name,type,price,shipping);
    }

    @Then("^I verify the status code for update (\\d+)$")
    public void iVerifyTheStatusCodeForUpdate(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }

    @And("^I verify the name is \"([^\"]*)\" for updating the record$")
    public void iVerifyTheNameIsForUpdatingTheRecord(String name) {
        response.body("name",equalTo(name));
    }

    @When("^I sends the Delete Request for deleting the product  given ID$")
    public void iSendsTheDeleteRequestForDeletingTheProductGivenID() {
        response = productSteps.deleteProductRecord(productId);
    }

    @Then("^I verify the status code for delete (\\d+)$")
    public void iVerifyTheStatusCodeForDelete(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }

    @And("^I get the data for deleted record ID$")
    public void iGetTheDataForDeletedRecordID() {
        response = productSteps.getProductRecordById(productId);
    }

    @And("^I verify the status code for delete verify (\\d+)$")
    public void iVerifyTheStatusCodeForDeleteVerify(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }


}
