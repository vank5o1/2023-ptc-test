package core.api.dto.response;

import core.api.dto.response.data.GetEmployeesData;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListEmployeesResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<GetEmployeesData> dataList;

    @JsonProperty("message")
    private String message;
}
