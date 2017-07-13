package com.jbillote.toukabotrequests.dao;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.jbillote.toukabotrequests.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private final String accessKey = System.getenv("AWS_ACCESS_KEY");
    private final String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
    private final String endpoint = "https://dynamodb.us-west-1.amazonaws.com";

    private DynamoDBMapper mapper;

    public UserDaoImpl() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonDynamoDB dynamoDB = new AmazonDynamoDBClient(credentials);

        dynamoDB.setEndpoint(endpoint);

        this.mapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public User findByUsername(String username) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(username));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("username = :val1")
                .withExpressionAttributeValues(eav);

        List<User> result = this.mapper.query(User.class, queryExpression);

        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public boolean register(User user) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));

            this.mapper.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean login(User user) {
        List<User> users;
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        users = this.mapper.scan(User.class, scanExpression);

        return users.size() == 1;
    }
}
