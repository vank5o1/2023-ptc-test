package core.api.service;

import config.ApiConstant;
import core.api.base.BaseRestApiService;
import core.api.dto.request.ObjectMapperUtils;
import core.api.dto.response.GetListEmployeesResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetListEmployeesService extends BaseRestApiService {

    public GetListEmployeesService() {
        super();
        this.setHost(ApiConstant.DOMAIN);
    }

    public GetListEmployeesResponse getListEmployeesResponse() {
        RequestSpecification spec =
                this.getDefaultRequestBuilder(ApiConstant.GET_EMPLOYEES_PATH)
                        .contentType(ContentType.JSON);
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        ApiConstant.STATUS_CODE = response.statusCode();
        System.out.println(response);
        return ObjectMapperUtils.convertJSONStringToDTOClassByObjectMapper(
                response.body().asString(), GetListEmployeesResponse.class);
    }
}
