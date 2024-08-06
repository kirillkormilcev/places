package ru.aston.places.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.places.error.dto.ErrorResponse;
import ru.aston.places.error.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(
                logAndGetErrorsFromStackTrace(e),
                e.getLocalizedMessage(),
                "Значение не найдено.",
                LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    private List<String> logAndGetErrorsFromStackTrace(Exception e) {
        log.warn(e.getMessage(), e.getCause());
        return Arrays.stream(
            ExceptionUtils
                .getRootCauseStackTrace(e))
            .filter(f -> f.contains("ru.aston.places"))
                .map(string -> {
                    if (string.contains("\t")) {
                        string = string.substring(1);
                    }
                    return string;
                })
                .toList();
    }
}
