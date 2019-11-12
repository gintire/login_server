package com.example.gintire;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class JsonTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void getJson_test() throws  Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"IPhone11\",\"desc\":\"Apple phone\",\"price\":1500000,\"image_path\":\"/images/iphone11\"},{\"id\":2,\"name\":\"IPhoneX\",\"desc\":\"Apple phone\",\"price\":1100000,\"image_path\":\"/images/iphoneX\"}]"))
                .andDo(MockMvcResultHandlers.print());
    }
}
