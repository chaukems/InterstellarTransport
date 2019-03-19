/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.RequestDto;

/**
 *
 * @author VukosiNyeleti
 */
/*@RunWith(SpringRunner.class)

@SpringBootTest(classes = TransportDataRestController.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransportDataRestController.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {BeanConfiguration.class, AppConfig.class})
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterTransportApplicationTest {

    protected MockMvc mvc;
    
    @Autowired
    private RoutesRepo routesRepo;

    @Autowired
    private PlanetsRepo planetRepo;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void testGetShortestDistance() throws JsonProcessingException, Exception {

        String uri = "/interstellar/transport/getDistance";
        RequestDto requestDto = new RequestDto();
        requestDto.setSource("F");
        requestDto.setDestination("P");

        String inputJson = mapToJson(requestDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(content, "{\"distance\":\"Infinity\",\"path\":\"[P]\"}");

    }

}
