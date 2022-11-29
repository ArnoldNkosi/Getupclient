package za.co.metropolitan.getup.client.errors;


import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

    @SuppressWarnings({"unchecked","rawtypes"})
    @ControllerAdvice
    public class CustomExceptionHandler extends ResponseEntityExceptionHandler
    {
        @ExceptionHandler(Exception.class)
        public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Server Error", details);
            ex.printStackTrace();
            return new ResponseEntity(new JSONObject(error.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(RecordNotFoundException.class)
        public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Record Not Found", details);
            ex.printStackTrace();
            return new ResponseEntity(new JSONObject(error.toString()), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidRequestException.class)
        public final ResponseEntity<Object> handleInvalidRequest(InvalidRequestException ex, WebRequest request) {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Invalid Request", details);
            ex.printStackTrace();
            return new ResponseEntity(new JSONObject(error.toString()), HttpStatus.BAD_REQUEST);
        }

    }
}
