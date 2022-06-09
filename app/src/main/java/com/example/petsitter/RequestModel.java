package com.example.petsitter;

public class RequestModel {
    String requestType;
    String requestStartDate;
    String requestTime;
    String requestCandidatesAvailable;
    String moreInfo;
    boolean image;

    public RequestModel(String requestType, String requestStartDate, String requestTime, boolean image, String requestDateType) {
        this.requestType = requestType;
        this.requestStartDate = requestStartDate;
        this.requestTime = requestTime;
        this.image = image;
    }

    public RequestModel(String requestType, String requestStartDate, String requestTime, boolean image) {
        this.requestType = requestType;
        this.requestStartDate = requestStartDate;
        this.requestTime = requestTime;
        this.image = image;
    }

    public RequestModel(String requestType, String requestStartDate, String requestTime) {
        this.requestType = requestType;
        this.requestStartDate = requestStartDate;
        this.requestTime = requestTime;
        this.image = false;
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

    public String getRequestCandidatesAvailable() { return requestCandidatesAvailable; }

    public String getMoreInfo() { return moreInfo; }

    public boolean isImage() { return image; }
}
