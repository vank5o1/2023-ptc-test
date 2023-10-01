package stepdefinition;

import utility.RandomUtility;
import config.ApiConstant;
import core.api.dto.request.createEmployeeRequest.CreateEmployeeRequestDTO;
import core.api.dto.response.CreateEmployeeResponse;
import core.api.service.CreateEmployeeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

public class CreateEmployeeSteps {

    CreateEmployeeService createEmployeeService = new CreateEmployeeService();
    String nameResponse;
    String salaryResponse;
    String ageResponse;
    String statusResponse;
    String messageResponse;
    String randomName;
    String randomAge;
    String randomSalary;

    @Given("I create new employee")
    public void createEmployee(){
        CreateEmployeeResponse createEmployeeResponse = createEmployeeService.createEmployeeResponse(createEmployeesRequestDTO());
        messageResponse = createEmployeeResponse.getMessage();
        statusResponse = createEmployeeResponse.getStatus();
        ageResponse = createEmployeeResponse.getCreateEmployeeData().getAge();
        salaryResponse = createEmployeeResponse.getCreateEmployeeData().getSalary();
        nameResponse = createEmployeeResponse.getCreateEmployeeData().getName();
    }

    @Then("I should see response with expectStatus: {string},expectMessage: {string} and expectStatusCode: {int}")
    public void verifyEmployeesCreateUser( String expectStatus, String expectMessage, int expectStatusCode){
        SoftAssert softassert = new SoftAssert();
        softassert.assertEquals(nameResponse,randomName);
        softassert.assertEquals(salaryResponse,randomSalary);
        softassert.assertEquals(ageResponse,randomAge);
        softassert.assertEquals(statusResponse,expectStatus);
        softassert.assertEquals(messageResponse,expectMessage);
        softassert.assertEquals(ApiConstant.STATUS_CODE,expectStatusCode);
        softassert.assertAll();
    }

    private CreateEmployeeRequestDTO createEmployeesRequestDTO(){
        CreateEmployeeRequestDTO request = new CreateEmployeeRequestDTO();
        randomName = RandomUtility.getRandomName();
        randomAge = RandomUtility.getRandomAge();
        randomSalary = RandomUtility.getRandomSalary();
        request.setName(randomName);
        request.setSalary(randomSalary);
        request.setAge(randomAge);
        return request;
    }


}
