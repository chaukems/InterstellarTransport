package za.co.interstellar.transport.web.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import za.co.interstellar.transport.web.util.RequestDto;

@FeignClient(name = "shortest-path-service")
public interface ShortestPathServiceProxy {

    @PostMapping(path = "/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDistanceAndShortestPath(@RequestBody RequestDto requestDto);
}
