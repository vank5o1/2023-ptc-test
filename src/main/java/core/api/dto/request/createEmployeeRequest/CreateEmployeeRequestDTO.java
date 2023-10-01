package core.api.dto.request.createEmployeeRequest;

import com.google.gson.annotations.SerializedName;
import core.api.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEmployeeRequestDTO implements Request {

    @SerializedName("name")
    private String name;

    @SerializedName("salary")
    private String salary;

    @SerializedName("age")
    private String age;
}
