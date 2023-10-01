package core.api.dto.request;

import java.util.Map;

public interface Request {
    default String convertDTOObjectToJSONString() {
        return RequestHelper.convertDTOObjectToJSONString(this);
    }

    default Map<String, Object> getDefaultRequestParams() {
        return RequestHelper.getDefaultRequestParams(this);
    }
}
