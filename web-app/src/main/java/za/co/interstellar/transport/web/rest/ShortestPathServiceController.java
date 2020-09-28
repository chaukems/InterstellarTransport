package za.co.interstellar.transport.web.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import za.co.interstellar.transport.web.util.RequestDto;

@Slf4j
@RestController
public class ShortestPathServiceController {

    @Autowired
    private ShortestPathServiceProxy proxy;

    @Autowired
    private ShortestPathServiceProxySimple simpleProxy;

    @PostMapping(path = "/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDistanceAndShortestPath(@RequestBody RequestDto requestDto) {
        return proxy.getDistanceAndShortestPath(requestDto);
    }

    @PostMapping(path = "/getShortestDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getCountryUsingRestTemplate(@RequestBody RequestDto requestDto) {

        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity("http://localhost:8100/getDistance", requestDto, String.class);

        return responseEntity.getBody();
    }

    @PostMapping(path = "/feign/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getCountryUsingFeign(@RequestBody RequestDto requestDto) {
        return simpleProxy.getDistanceAndShortestPath(requestDto);
    }

}
