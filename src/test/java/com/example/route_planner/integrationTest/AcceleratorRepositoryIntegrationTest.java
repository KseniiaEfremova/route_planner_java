package com.example.route_planner.integrationTest;


import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.example.route_planner.models.Accelerator;
import com.example.route_planner.models.PriceToAccelerator;
import com.example.route_planner.repository.AcceleratorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=key",
        "amazon.aws.secretkey=key2" })
public class AcceleratorRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    AcceleratorRepository repository;

    @BeforeEach
    public void before() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Accelerator.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
    }

    @AfterEach
    public void after() {
        DeleteTableRequest request = dynamoDBMapper.generateDeleteTableRequest(
                Accelerator.class);
        amazonDynamoDB.deleteTable(request);
    }


    @Test
    public void testAccelerator() {
        List<PriceToAccelerator> connections = new ArrayList<>();
        connections.add(new PriceToAccelerator("SIR", 200));
        connections.add(new PriceToAccelerator("PRO", 120));
        Accelerator accelerator = new Accelerator("SOL", "Sun", connections);
        repository.save(accelerator);
        List<Accelerator> result = (List<Accelerator>) repository.findAll();
        assertEquals(1, result.size());
        Accelerator returnedResult = result.get(0);
        assertEquals("SOL", returnedResult.getId());
        assertEquals("Sun", returnedResult.getName());
        assertEquals(returnedResult.getConnections().size(), 2);
        assertEquals(returnedResult.getConnections().get(0).getId(), "SIR");
        assertEquals(returnedResult.getConnections().get(0).getHu(), 200);
        assertEquals(returnedResult.getConnections().get(1).getId(), "PRO");
        assertEquals(returnedResult.getConnections().get(1).getHu(), 120);
    }
}