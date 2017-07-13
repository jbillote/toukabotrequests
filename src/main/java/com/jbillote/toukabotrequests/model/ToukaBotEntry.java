package com.jbillote.toukabotrequests.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "ToukaBot")
public class ToukaBotEntry {
    private String command;
    private List<String> images;

    public ToukaBotEntry() {}

    @DynamoDBHashKey(attributeName = "command")
    public String getCommand() {
        return command;
    }

    @DynamoDBAttribute(attributeName = "images")
    public List<String> getImages() {
        return images;
    }

    @DynamoDBHashKey(attributeName = "command")
    public void setCommand(String command) {
        this.command = command;
    }


    @DynamoDBAttribute(attributeName = "images")
    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String url) {
        this.images.add(url);
    }
}
