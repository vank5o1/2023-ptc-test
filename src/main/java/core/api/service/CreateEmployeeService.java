package core.api.service;

import config.ApiConstant;
import core.api.base.BaseRestApiService;
import core.api.dto.request.ObjectMapperUtils;
import core.api.dto.request.createEmployeeRequest.CreateEmployeeRequestDTO;
import core.api.dto.response.CreateEmployeeResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateEmployeeService extends BaseRestApiService {

    public CreateEmployeeService() {
        super();
        this.setHost(ApiConstant.DOMAIN);
    }

    public CreateEmployeeResponse createEmployeeResponse(CreateEmployeeRequestDTO createUserRequestDTO) {
        RequestSpecification spec =
                this.getDefaultRequestBuilder(ApiConstant.CREATE_USER_PATH)
                        .contentType(ContentType.JSON)
                        .body(createUserRequestDTO.convertDTOObjectToJSONString());
        Response response = this.dispatchServiceRequest(spec, Method.POST);
        ApiConstant.STATUS_CODE = response.statusCode();
        System.out.println(response);
        return ObjectMapperUtils.convertJSONStringToDTOClassByObjectMapper(
                response.body().asString(), CreateEmployeeResponse.class);
    }
}
