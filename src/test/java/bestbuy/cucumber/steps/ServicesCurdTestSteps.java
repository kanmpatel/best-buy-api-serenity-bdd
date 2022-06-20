package bestbuy.cucumber.steps;

import bestbuy.bestbuySteps.servicesInfo.ServiecesSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class ServicesCurdTestSteps {

    static int servicesId;
    static ValidatableResponse response;

    @Steps
    ServiecesSteps serviecesSteps;

    @When("^I sends the Post Request for create the services Data$")
    public void iSendsThePostRequestForCreateTheServicesData() {
    }

    @And("^I insert the name \"([^\"]*)\"$")
    public void iInsertTheName(String name) {
        response = serviecesSteps.CreateServicesRecord(name);
    }

    @Then("^I verify the status code (\\d+) for services data$")
    public void iVerifyTheStatusCodeForServicesData(int statusCode) {
        response.log().all().
                statusCode(statusCode);
    }

    @And("^I get the id of new created services data$")
    public void iGetTheIdOfNewCreatedServicesData() {
        servicesId= response.extract().path("id");
    }

    @When("^I sends a GET request for fatching services data by given ID$")
    public void iSendsAGETRequestForFatchingServicesDataByGivenID() {
        response = serviecesSteps.verfiyServicesList(servicesId);

    }

    @Then("^I verify the name is for created record \"([^\"]*)\" for services$")
    public void iVerifyTheNameIsForCreatedRecordForServices(String name) {
        response.body("name",equalTo(name));
    }

    @When("^I send the Put Request for updating the services data$")
    public void iSendThePutRequestForUpdatingTheServicesData() {
    }

    @And("^I update the name \"([^\"]*)\" for services$")
    public void iUpdateTheNameForServices(String name) {
        response = serviecesSteps.updateServicesRecord(servicesId,name);
    }

    @Then("^I verify the status code (\\d+) for update services data$")
    public void iVerifyTheStatusCodeForUpdateServicesData(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }

    @And("^I verify the name is \"([^\"]*)\" for updating the services data$")
    public void iVerifyTheNameIsForUpdatingTheServicesData(String name) {
        response = serviecesSteps.verfiyServicesList(servicesId);
        response.body("name",equalTo(name));

    }

    @When("^I sends the Delete Request for deleting the services  given ID$")
    public void iSendsTheDeleteRequestForDeletingTheServicesGivenID() {
        response = serviecesSteps.deleteServicesRecord(servicesId);
    }

    @Then("^I verify the status code (\\d+) for delete services data$")
    public void iVerifyTheStatusCodeForDeleteServicesData(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }

    @And("^I get the data for deleted record ID for services data$")
    public void iGetTheDataForDeletedRecordIDForServicesData() {
        response = serviecesSteps.verfiyServicesList(servicesId);
    }

    @And("^I verify the status code (\\d+) for verifing the delete record for services$")
    public void iVerifyTheStatusCodeForVerifingTheDeleteRecordForServices(int statusCode) {
        response.log().all()
                .statusCode(statusCode);
    }
}
