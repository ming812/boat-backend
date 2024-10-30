package com.example.demo.exception;

import com.example.demo.entity.GeneralResponse;

public class GeneralResponseException extends RuntimeException {
    private GeneralResponse generalResponse;

    public GeneralResponseException(GeneralResponse generalResponse) {
        this.generalResponse = generalResponse;
    }

    public GeneralResponse getGeneralResponse() {
        return generalResponse;
    }
}
