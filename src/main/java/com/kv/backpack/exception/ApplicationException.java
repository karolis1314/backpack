package com.kv.backpack.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus code;

        public ApplicationException(HttpStatus code, String message) {
            super(message);
            this.code = code;
        }

}
