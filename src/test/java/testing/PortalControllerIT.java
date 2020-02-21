package com.WebApp4;

import com.WebApp.DemoApplication;
import com.WebApp.Topic.pojo.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PortalControllerIT {
    //ner;
////import org.springframework.web.multipart.MultipartFile;
////
////import com.fasterxml.jackson.core.JsonParseException;
////import com.fasterxml.jackson.core.type.TypeReference;
////import com.fasterxml.jackson.databind.JsonMappingException;
    @LocalServerPort
    private int port;
    //
    @Autowired
    private ObjectMapper mapper;

    private final String portalBaseURL = IntegrationTestingConstants.PORTAL_BASE_URL;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    //
    @Before
    public void before() {
        headers.add("Authorization", IntegrationTestingConstants.BEARER_TOKEN);
    }
    //
    private String createURLWithPort(String uri)
    {
        return "http://localhost:" + port + uri;
    }
    @Test
    public void testGetAllTopics() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/topics"),
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(IntegrationTestingConstants.MYRES, response.getBody(), false);


    }


    @Test
    public void addTopic() {

        Topic topic = new Topic("topic11", "Spring", "10 Steps");

        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/topics"),
                HttpMethod.POST, entity, String.class);

//        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

//        assertTrue(actual.contains(IntegrationTestingConstants.MYRES));

        assertEquals(200,response.getStatusCodeValue());

    }
    @Test
    public void updateTopic() {

        Topic topic = new Topic("topic11", "Spring", "10 Steps");

        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/topics/"+topic.getId()),
                HttpMethod.PUT, entity, String.class);


        assertEquals(200,response.getStatusCodeValue());

    }
    @Test
    public void DeleteTopic() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
String topic="topic11";
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/topics/"+topic),
                HttpMethod.DELETE, entity, String.class);
        assertEquals(200,response.getStatusCodeValue());



    }


}

