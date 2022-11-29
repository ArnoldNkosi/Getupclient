package za.co.metropolitan.getup.client.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends RuntimeException{

    public DuplicateRecordException(String exception) {
        super(exception);
    }
}
