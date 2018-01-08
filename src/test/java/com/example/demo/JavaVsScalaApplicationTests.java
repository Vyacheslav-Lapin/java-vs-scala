package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JavaVsScalaApplicationTests {

    public static final String JSON =
            "{\"user\":{\"id\":1,\"firstName\":\"Vasya\",\"lastName\":\"Pupkin\",\"dateOfBirth\":\"1985-11-24\",\"emailAddress\":\"jkh%40ma.il\"},\"bonuses\":[{\"id\":1,\"description\":\"New year bonus\"},{\"id\":2,\"description\":\"Birthday bonus\"},{\"id\":3,\"description\":\"Random bonus\"}]}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/userData?emailAddress=jkh%40ma.il")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(JSON)));
    }
}
