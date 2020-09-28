package za.co.interstellar.transport.shortest.path;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShortestPathSteps {

    String myname = "", mycert = "";
    int mymarks = 0;

    private String sourcePlanet;
    private String destinationPlanet;

    @Given("^that (.*) is the source planet and (.*) is the destination planet$")
    public void source_destination_planet(String planetOrigin, String planetDestination) throws Throwable {
        sourcePlanet = planetOrigin;
        destinationPlanet = planetDestination;
        log.debug("planetOrigin = {}  planetDestination =  {}", sourcePlanet, destinationPlanet);
    }

    @When("^(.*) is not (.*)$")
    public void path_not_null(String path, String results) throws Throwable {
        log.debug("path = " + path, " results = " + results);
    }

    @Then("^(.*) path is calculated and displayed$")
    public void shortest_path_calculated(String shortDistance) throws Throwable {
        log.debug("shortDistance = " + shortDistance);
    }

}
