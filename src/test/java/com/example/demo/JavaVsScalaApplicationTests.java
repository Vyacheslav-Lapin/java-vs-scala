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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class JavaVsScalaApplicationTests {

    //language=JSON
    private static final String CORRECT_JSON = "{\n" +
                    "  \"user\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"firstName\": \"Vasya\",\n" +
                    "    \"lastName\": \"Pupkin\",\n" +
                    "    \"dateOfBirth\": \"1985-11-24\",\n" +
                    "    \"emailAddress\": \"Vasya.Pupkin@ma.il\"\n" +
                    "  },\n" +
                    "  \"bonuses\": [\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"description\": \"New year 10% bonus for tickets [Ticket(id=101, description=It`s my first ticket!), Ticket(id=102, description=It`s my second ticket!)]\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"description\": \"Birthday 15% bonus for tickets [Ticket(id=101, description=It`s my first ticket!), Ticket(id=102, description=It`s my second ticket!)]\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 3,\n" +
                    "      \"description\": \"Random 5% bonus for tickets [Ticket(id=101, description=It`s my first ticket!), Ticket(id=102, description=It`s my second ticket!)]\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

    //language=JSON
    private final String ERROR_JSON = "{\n" +
            "  \"timestamp\": \"2018-01-08T16:23:56.112+0000\",\n" +
            "  \"status\": 404,\n" +
            "  \"error\": \"Not Found\",\n" +
            "  \"message\": \"User with this email is not registered in the system!\",\n" +
            "  \"path\": \"/userData\"\n" +
            "}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCorrectAnswer() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/userData?emailAddress=Vasya.Pupkin%40ma.il")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(CORRECT_JSON));
    }

    @Test
    public void getErrorAnswer() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/userData?emailAddress=noname%40ma.il")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
//                .andExpect(content().json(ERROR_JSON));
    }
}
