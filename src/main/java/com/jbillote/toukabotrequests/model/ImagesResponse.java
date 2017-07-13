package com.jbillote.toukabotrequests.model;

import java.util.List;

public class ImagesResponse {
    private String command;
    private List<String> images;

    public ImagesResponse() {}

    public ImagesResponse(String command, List<String> images) {
        this.command = command;
        this.images = images;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
