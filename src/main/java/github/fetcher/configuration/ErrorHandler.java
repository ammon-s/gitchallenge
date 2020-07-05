package github.fetcher.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.fetcher.model.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class ErrorHandler extends DefaultHandlerExceptionResolver {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        logger.info("Unsupported MediaType request occurred");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(StatusCode.builder()
                    .status(HttpStatus.NOT_ACCEPTABLE.value())
                    .Message("MediaType not supported. Please use application/json")
                    .build());
        } catch (JsonProcessingException e) {
            logger.fatal(e.getMessage());
            return "Internal Server Error";
        }
    }
}
