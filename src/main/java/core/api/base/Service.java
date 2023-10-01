package core.api.base;

public interface Service<R, T, A> {
    A dispatchServiceRequest(R serviceSpecification, T serviceType);

    String getServiceUrl();
}
