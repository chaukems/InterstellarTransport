package za.co.interstellar.transport.shortest.path.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import za.co.interstellar.transport.shortest.path.entity.Planet;
import za.co.interstellar.transport.shortest.path.entity.Route;

public class DijkstraAlgorithm {

    private final List<Planet> nodes;
    private final List<Route> routes;
    private Set<Planet> settledNodes;//Nodes with known distance
    private Set<Planet> unSettledNodes;//Nodes that we cannot reach
    private Map<Planet, Planet> predecessors;
    private Map<Planet, Double> distance; 
            
    public DijkstraAlgorithm(Graph graph) {
        this.nodes = new ArrayList<>(graph.getNodes());
        this.routes = new ArrayList<>(graph.getRoutes());
    }

    public void execute(final Planet source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0D);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            Planet node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Planet node) {
        List<Planet> adjacentNodes = getNeighbours(node);
        adjacentNodes.stream().filter((target) -> (getPlanetShortestDistance(target) > getPlanetShortestDistance(node)
                + getDistance(node, target))).map((target) -> {
                    distance.put(target, getPlanetShortestDistance(node)
                            + getDistance(node, target));
            return target;
        }).map((target) -> {
            predecessors.put(target, node);
            return target;
        }).forEachOrdered((target) -> {
            unSettledNodes.add(target);
        });
    }

    private double getDistance(Planet node, Planet target) {
        for (Route route : routes) {
            if (route.getPlanetOrigin() != null && route.getPlanetDestination() != null) {
                if (route.getPlanetOrigin().equals(node)
                        && route.getPlanetDestination().equals(target)) {
                    return route.getDistance();
                }
            }
        }
        throw new RuntimeException("Error.");
    }

    private List<Planet> getNeighbours(Planet node) {
        List<Planet> neighbours = new ArrayList<>();
        routes.stream().filter((route) -> (route.getPlanetOrigin() != null && route.getPlanetDestination() != null)).filter((route) -> (route.getPlanetOrigin().equals(node) && !isSettled(route.getPlanetDestination()))).forEachOrdered((route) -> {
            neighbours.add(route.getPlanetDestination());
        });
        return neighbours;
    }

    private Planet getMinimum(Set<Planet> nodes) {
        Planet minimum = null;
        for (Planet node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getPlanetShortestDistance(node) < getPlanetShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private double getPlanetShortestDistance(Planet destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Planet> getShortestPath(Planet target) {
        LinkedList<Planet> path = new LinkedList<>();
        Planet step = target;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    private boolean isSettled(Planet node) {
        return settledNodes.contains(node);
    }

}
