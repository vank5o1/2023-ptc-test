package core.api.dto.response.data;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmployeeData {
    @JsonProperty("name")
    private String name;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("age")
    private String age;

    @JsonProperty("id")
    private int id;
}
