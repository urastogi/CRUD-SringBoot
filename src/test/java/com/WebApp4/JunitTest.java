package com.WebApp4;

import com.WebApp.Topic.Service.TopicService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitTest {
    @Test
public void sumwith3numbers(){
        TopicService topicservice= new TopicService();
       int result=topicservice.sum(new int[]{1,2,3});
           assertEquals(6,result);
       System.out.println(result);
    }
}
