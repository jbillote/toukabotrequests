package com.jbillote.toukabotrequests.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "touka-bot-requests")
public class Request {
    private String command;
    private String url;

    public Request() {}

    @DynamoDBHashKey(attributeName = "Command")
    public String getCommand() {
        return this.command;
    }

    @DynamoDBRangeKey(attributeName = "URL")
    public String getUrl() {
        return this.url;
    }

    @DynamoDBHashKey(attributeName = "Command")
    public void setCommand(String command) {
        this.command = command;
    }

    @DynamoDBRangeKey(attributeName = "URL")
    public void setUrl(String url) {
        this.url = url;
    }
}
