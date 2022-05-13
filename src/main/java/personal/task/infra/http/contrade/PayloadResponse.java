package personal.task.infra.http.contrade;

import personal.task.models.Validation;

public class PayloadResponse {
    private Validation validation;

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "PayloadResponse{" +
                "validation=" + validation +
                '}';
    }
}
