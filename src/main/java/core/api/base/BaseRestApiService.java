package core.api.base;
import config.ENVConstant;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;
import io.restassured.response.Response;
import com.google.api.client.util.ArrayMap;
import java.util.InputMismatchException;
import java.util.Map;

public class BaseRestApiService implements Service<RequestSpecification, Method, Response>{

    private String host;
    private String protocol = ENVConstant.HTTPS_PROTOCOL;

    private Map<String, String> cookies = new ArrayMap<>();
    private Map<String, String> headers = new ArrayMap<>();

    protected BaseRestApiService(String protocol, String host) {
        this(host);
        this.protocol = protocol;
    }

    protected BaseRestApiService(String host) {
        this.host = host;
    }
    protected BaseRestApiService() {
    }

    protected void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    protected void setHost(String host) {
        this.host = host;
    }


    protected RequestSpecification getDefaultRequestBuilder(String apiPath) {
        RequestSpecification requestSpecification = getDefaultRequestBuilderWithoutProxy(apiPath);
        return requestSpecification.config(createHttpClientConfig()).relaxedHTTPSValidation();
    }

    protected RequestSpecification getDefaultRequestBuilderWithoutProxy(String apiPath) {
        return RestAssured.given()
                .contentType(ContentType.URLENC)
                .baseUri(this.getServiceUrl())
                .basePath(apiPath)
                .relaxedHTTPSValidation()
                .cookies(cookies)
                .headers(headers)
                .config(createHttpClientConfig())
                .relaxedHTTPSValidation();

    }

    @Override
    public Response dispatchServiceRequest(RequestSpecification request, Method method) {
        switch (method) {
            case POST:
                return request.post().thenReturn();
            case PUT:
                return request.put().thenReturn();
            case GET:
                return request.get().thenReturn();
            case DELETE:
                return request.delete().thenReturn();
            case HEAD:
                return request.head().thenReturn();
            case OPTIONS:
                return request.options().thenReturn();
            case PATCH:
                return request.patch().thenReturn();
            default:
                throw new InputMismatchException("Not Support Request Method");
        }
    }

    public String getServiceUrl() {
        return String.format("%s://%s", this.protocol, this.host);
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    private RestAssuredConfig createHttpClientConfig() {
        return RestAssuredConfig.config()
                .httpClient(
                        HttpClientConfig.httpClientConfig()
                                .setParam(
                                        "http.connection.timeout",60000
                                        )
                                .setParam(
                                        "http.socket.timeout",60000
                                        ));
    }

}
