/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.interstellar.transport.entity.Planet;
import za.co.interstellar.transport.entity.Route;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.Edge;
import za.co.interstellar.transport.util.RequestDto;
import za.co.interstellar.transport.util.ResponseDto;
import za.co.interstellar.transport.util.Vertex;

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

        System.out.println("Source Planet " + gson.toJson(sourcePlanet));
        System.out.println("Destination Planet " + gson.toJson(destinationPlanet));

        if (sourcePlanet == null && destinationPlanet == null) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setErrorCode("400");
            responseDto.setErrorMessage("Bad request.");
            return responseDto;
        }

        Vertex source = new Vertex(sourcePlanet.getPlanetNode());

        

        List<Route> sourcePlanetRoutes = routesRepo.findAll();

        Edge[] adjacencies = new Edge[sourcePlanetRoutes.size()];
        int i = 0;

        for (Route route : sourcePlanetRoutes) {

            adjacencies[i] = new Edge(source, route.getDistance());
            i++;
        }
        source.adjacencies = adjacencies;
        computePaths(source);
        
        Vertex destination = new Vertex(destinationPlanet.getPlanetNode());

        destination.adjacencies = adjacencies;

        List<Vertex> path = getShortestPathTo(destination);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setDistance(destination.minDistance + "");
        responseDto.setPath(path.toString());

        return responseDto;

    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

}
