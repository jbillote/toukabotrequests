package com.jbillote.toukabotrequests.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "jbillote-website")
public class User {
    private String username;
    private String password;

    public User() {}

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    @DynamoDBHashKey(attributeName = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBHashKey(attributeName = "password")
    public void setPassword(String password) {
        this.password = password;
    }
}
