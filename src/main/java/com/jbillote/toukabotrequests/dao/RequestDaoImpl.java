package com.jbillote.toukabotrequests.dao;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.jbillote.toukabotrequests.model.Request;
import com.jbillote.toukabotrequests.model.ToukaBotEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDaoImpl implements RequestDao {
    private final String accessKey = System.getenv("AWS_ACCESS_KEY");
    private final String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
    private final String endpoint = "https://dynamodb.us-west-1.amazonaws.com";

    private DynamoDBMapper mapper;

    public RequestDaoImpl() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonDynamoDB dynamoDB = new AmazonDynamoDBClient(credentials);

        dynamoDB.setEndpoint(endpoint);

        this.mapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public List<Request> getRequests() {
        List<Request> requests;
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        requests = this.mapper.scan(Request.class, scanExpression);

        return requests;
    }

    @Override
    public boolean addRequest(Request request) {
        try {
            this.mapper.save(request);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean approveRequest(Request request) {
        try {
            Map<String, AttributeValue> eav = new HashMap<>();
            eav.put(":val1", new AttributeValue().withS(request.getCommand()));

            DynamoDBQueryExpression<ToukaBotEntry> queryExpression = new DynamoDBQueryExpression<ToukaBotEntry>()
                    .withKeyConditionExpression("command = :val1")
                    .withExpressionAttributeValues(eav);

            List<ToukaBotEntry> result = this.mapper.query(ToukaBotEntry.class, queryExpression);
            result.get(0).addImage(request.getUrl());

            this.mapper.save(result.get(0));
            this.mapper.delete(request);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean declineRequest(Request request) {
        try {
            this.mapper.delete(request);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}