/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.interstellar.transport.entity.Planet;
import za.co.interstellar.transport.entity.Route;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.DijkstraAlgorithm;
import za.co.interstellar.transport.util.Graph;
import za.co.interstellar.transport.util.RequestDto;
import za.co.interstellar.transport.util.ResponseDto;

/**
 *
 * @author schauke
 */
@Service
public class TransportDataService {

    @Autowired
    private PlanetsRepo planetRepo;

    @Autowired
    private RoutesRepo routesRepo;

    @Autowired
    private Gson gson;

    public ResponseDto getDistanceAndPath(RequestDto requestDto) {

        Planet sourcePlanet = planetRepo.findByPlanetNode(requestDto.getSource().toUpperCase());
        Planet destinationPlanet = planetRepo.findByPlanetNode(requestDto.getDestination().toUpperCase());

        if (sourcePlanet == null && destinationPlanet == null) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setErrorCode("400");
            responseDto.setErrorMessage("Bad request.");
            return responseDto;
        }

      
        Graph graph = new Graph(planetRepo.findAll(), routesRepo.findAll());
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(sourcePlanet);
        LinkedList<Planet> path = dijkstra.getShortestPath(destinationPlanet);

        List<String> pathNodes = new ArrayList<>();
        String results = "";

        if (path != null) {
            for (Planet planet : path) {
                pathNodes.add(planet.getPlanetNode());
            }
            results = pathNodes.toString();
        } else {
            results = "Infinity";
        }

        Route route = routesRepo.findByPlanetOriginAndPlanetDestination(sourcePlanet, destinationPlanet);

        if (route == null) {
            route = Collections.max(routesRepo.findByPlanetDestination(destinationPlanet), Comparator.comparing(s -> s.getDistance()));
        }

        String distance = (route != null && path != null) ? route.getDistance() + "" : "";

        ResponseDto responseDto = new ResponseDto();
        responseDto.setDistance(distance);
        responseDto.setPath(results);

        return responseDto;
    }
}
