package stepdefinition;

import core.api.dto.response.GetListEmployeesResponse;
import core.api.dto.response.data.GetEmployeesData;
import core.api.service.GetListEmployeesService;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.stream.Collectors;

public class GetListEmployeesSteps {

    GetListEmployeesService getListEmployeesService = new GetListEmployeesService();
    String statusResponse;
    String messageResponse;


    @Given("I get all employees with minAge is {int} and minSalary is {long}")
    public void getEmployees(int minAge, long minSalary){
        GetListEmployeesResponse  getListEmployeesResponse = getListEmployeesService.getListEmployeesResponse();
        messageResponse = getListEmployeesResponse.getMessage();
        statusResponse = getListEmployeesResponse.getStatus();
        System.out.println(messageResponse);
        try {

            List<GetEmployeesData> filteredEmployees = getListEmployeesResponse.getDataList().stream()
                    .filter(employee -> employee.getEmployeeAge() > minAge && employee.getEmployeeSalary() > minSalary)
                    .collect(Collectors.toList());

            //Print list of employees which match the search criteria
            for (GetEmployeesData employee : filteredEmployees) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getEmployeeName());
                System.out.println("Salary: " + employee.getEmployeeSalary());
                System.out.println("Age: " + employee.getEmployeeAge());
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
