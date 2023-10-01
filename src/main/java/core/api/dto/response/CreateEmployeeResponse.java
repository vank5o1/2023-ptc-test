package core.api.dto.response;

import core.api.dto.response.data.CreateEmployeeData;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmployeeResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private CreateEmployeeData createEmployeeData;

    @JsonProperty("message")
    private String message;


}
