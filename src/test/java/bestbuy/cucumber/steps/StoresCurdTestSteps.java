package bestbuy.cucumber.steps;

import bestbuy.bestbuySteps.StoreInfo.StoreSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class StoresCurdTestSteps {

    static int storeId;
    static ValidatableResponse response;

    @Steps
    StoreSteps storeSteps;

    @When("^I sends the Post Request for create the Stores Data$")
    public void iSendsThePostRequestForCreateTheStoresData() {
    }

    @And("^I insert the name \"([^\"]*)\",type \"([^\"]*)\",address \"([^\"]*)\",addresss \"([^\"]*)\",city \"([^\"]*)\",state \"([^\"]*)\",zip \"([^\"]*)\",lat = (\\d+),lng = (\\d+),hours = \"([^\"]*)\"$")
    public void iInsertTheNameTypeAddressAddresssCityStateZipLatLngHours(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours) {
        response = storeSteps.CreateStoreRecord(name,type,address,address2,city,state,zip,lat,lng,hours);
    }

    @Then("^I verify the status code (\\d+) for Stores data$")
    public void iVerifyTheStatusCodeForStoresData(int statuscode) {
        response.log().all().
                statusCode(statuscode);
    }

    @And("^I get the id of new created Stores data$")
    public void iGetTheIdOfNewCreatedStoresData() {
        storeId= response.extract().path("id");
    }

    @When("^I sends a GET request for fatching Stores data by given ID$")
    public void iSendsAGETRequestForFatchingStoresDataByGivenID() {
        response = storeSteps.verfiyStoreList(storeId);
    }

    @Then("^I verify the name is for created record \"([^\"]*)\" for stores$")
    public void iVerifyTheNameIsForCreatedRecordForStores(String name) {
        response.body("name",equalTo(name));

    }

    @When("^I send the Put Request for updating the Stores data$")
    public void iSendThePutRequestForUpdatingTheStoresData() {
    }

    @And("^I update the name \"([^\"]*)\",type \"([^\"]*)\",address \"([^\"]*)\" for stores$")
    public void iUpdateTheNameTypeAddressForStores(String name, String type, String address) {
        response = storeSteps.updateStoreRecord(storeId,name,type,address);
    }

    @Then("^I verify the status code (\\d+) for update Stores data$")
    public void iVerifyTheStatusCodeForUpdateStoresData(int statusCode) {
        response.log().all()
                .statusCode(statusCode);

    }

    @And("^I verify the name is \"([^\"]*)\" for updating the Stores data$")
    public void iVerifyTheNameIsForUpdatingTheStoresData(String name) {
        response = storeSteps.verfiyStoreList(storeId);
        response.body("name",equalTo(name));
    }

    @When("^I sends the Delete Request for deleting the Stores  given ID$")
    public void iSendsTheDeleteRequestForDeletingTheStoresGivenID() {
        response = storeSteps.deleteStoreRecord(storeId);




    }

    @Then("^I verify the status code (\\d+) for delete Stores data$")
    public void iVerifyTheStatusCodeForDeleteStoresData(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }

    @And("^I get the data for deleted record ID for Stores data$")
    public void iGetTheDataForDeletedRecordIDForStoresData() {
        response = storeSteps.verfiyStoreList(storeId);
    }

    @And("^I verify the status code (\\d+) for verifing the delete record for stores$")
    public void iVerifyTheStatusCodeForVerifingTheDeleteRecordForStores(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }
}
