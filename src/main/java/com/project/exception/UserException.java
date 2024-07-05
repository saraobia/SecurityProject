package com.project.exception;

import com.project.model.dto.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private ErrorResponse response;

    @Override
    public String getMessage() {
        return response.getMessage();
    }
}
