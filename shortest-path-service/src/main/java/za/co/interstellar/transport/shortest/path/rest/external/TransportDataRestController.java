/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.shortest.path.rest.external;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import za.co.interstellar.transport.shortest.path.service.TransportDataService;
import za.co.interstellar.transport.shortest.path.util.RequestDto;


@RestController
@RequestMapping
@EnableWebMvc
public class TransportDataRestController {

    @Autowired
    private TransportDataService transportDataService;

    @PostMapping(path = "/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "", authorizations = {
        @Authorization(value = "jwtToken")})
    public Object getDistanceAndShortestPath(@RequestBody RequestDto requestDto) {

        return transportDataService.getDistanceAndPath(requestDto);
    }

}
