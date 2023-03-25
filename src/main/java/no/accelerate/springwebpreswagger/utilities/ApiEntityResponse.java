package no.accelerate.springwebpreswagger.utilities;

public class ApiEntityResponse {
    private String message;

    public ApiEntityResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
