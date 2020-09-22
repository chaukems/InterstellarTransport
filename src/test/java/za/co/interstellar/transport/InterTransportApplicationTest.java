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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import za.co.interstellar.transport.entity.Planet;
import za.co.interstellar.transport.entity.Route;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.DijkstraAlgorithm;
import za.co.interstellar.transport.util.Graph;
import za.co.interstellar.transport.util.RequestDto;


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

    private List<Planet> planets;
    private List<Route> routes;

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
    public void testGetShortestDistanceRest() throws JsonProcessingException, Exception {

        String uri = "/interstellar/transport/getDistance";
        RequestDto requestDto = new RequestDto();
        requestDto.setSource("A");
        requestDto.setDestination("Z");

        String inputJson = mapToJson(requestDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(content, "{\"distance\":\"13.97\",\"path\":\"[A, B, H, G, Z]\",\"errorCode\":null,\"errorMessage\":null}");
    }

    @Test
    public void testShortestDistanceBetweenRouteBAndH() {
        planets = planetRepo.findAll();
        routes = routesRepo.findAll();

        Planet sourcePlanet = planetRepo.findByPlanetNode("B");
        Planet destinationPlanet = planetRepo.findByPlanetNode("H");

        Graph graph = new Graph(planets, routes);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(sourcePlanet);
        LinkedList<Planet> path = dijkstra.getShortestPath(destinationPlanet);

        System.out.println("path = " + path);

        assertNotNull(path);
        assertTrue(path.size() > 0);

        List<String> pathNodes = new ArrayList<>();

        for (Planet planet : path) {
            pathNodes.add(planet.getPlanetNode());
        }

        assertEquals(pathNodes.toString(), "[B, H]");
    }

    @Test
    public void testForInfinityOrEmptyOrNullPath() {
        planets = planetRepo.findAll();
        routes = routesRepo.findAll();

        Planet sourcePlanet = planetRepo.findByPlanetNode("B");
        Planet destinationPlanet = planetRepo.findByPlanetNode("F");

        Graph graph = new Graph(planets, routes);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(sourcePlanet);
        LinkedList<Planet> path = dijkstra.getShortestPath(destinationPlanet);

        assertEquals(path, null);
    }
   
}
