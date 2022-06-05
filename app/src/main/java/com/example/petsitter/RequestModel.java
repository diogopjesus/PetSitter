package com.example.petsitter;

public class RequestModel {
    String requestType;
    String requestStartDate;
    String requestTime;


    public RequestModel(String requestType, String requestStartDate, String requestTime) {
        this.requestType = requestType;
        this.requestStartDate = requestStartDate;
        this.requestTime = requestTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequestStartDate() {
        return requestStartDate;
    }

    public String getRequestTime() {
        return requestTime;
    }
}
