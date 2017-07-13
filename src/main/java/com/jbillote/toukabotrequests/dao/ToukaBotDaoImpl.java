package com.jbillote.toukabotrequests.dao;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.jbillote.toukabotrequests.model.ToukaBotEntry;

import java.util.*;

public class ToukaBotDaoImpl implements ToukaBotDao {
    private final String accessKey = System.getenv("AWS_ACCESS_KEY");
    private final String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
    private final String endpoint = "https://dynamodb.us-west-1.amazonaws.com";

    private DynamoDBMapper mapper;

    public ToukaBotDaoImpl() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonDynamoDB dynamoDB = new AmazonDynamoDBClient(credentials);

        dynamoDB.setEndpoint(endpoint);

        this.mapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public List<String> getCommands() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.setFilterExpression("attribute_exists(images)");
        scanExpression.setProjectionExpression("command");

        List<ToukaBotEntry> result = this.mapper.scan(ToukaBotEntry.class, scanExpression);
        List<String> commands = new ArrayList<>();

        for (ToukaBotEntry entry : result) {
            commands.add(entry.getCommand());
        }

        commands.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return commands;
    }

    @Override
    public List<String> getImages(String command) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(command));

        DynamoDBQueryExpression<ToukaBotEntry> queryExpression = new DynamoDBQueryExpression<ToukaBotEntry>()
                .withKeyConditionExpression("command = :val1")
                .withExpressionAttributeValues(eav);

        List<ToukaBotEntry> result = this.mapper.query(ToukaBotEntry.class, queryExpression);

        return result.get(0).getImages();
    }

    @Override
    public boolean addImage(String command, String url) {
        try {
            Map<String, AttributeValue> eav = new HashMap<>();
            eav.put(":val1", new AttributeValue().withS(command));

            DynamoDBQueryExpression<ToukaBotEntry> queryExpression = new DynamoDBQueryExpression<ToukaBotEntry>()
                    .withKeyConditionExpression("command = :val1")
                    .withExpressionAttributeValues(eav);

            List<ToukaBotEntry> result = this.mapper.query(ToukaBotEntry.class, queryExpression);
            result.get(0).addImage(url);

            this.mapper.save(result.get(0));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
