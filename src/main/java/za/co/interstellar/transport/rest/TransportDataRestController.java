/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import za.co.interstellar.transport.service.TransportDataService;
import za.co.interstellar.transport.util.RequestDto;

/**
 *
 * @author VukosiNyeleti
 */
@RestController
@RequestMapping("/interstellar/transport")
@EnableWebMvc
public class TransportDataRestController {

    @Autowired
    private TransportDataService transportDataService;

    @PostMapping(path = "/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDistanceAndShortestPath(@RequestBody RequestDto requestDto) {

        return transportDataService.getDistanceAndPath(requestDto);
    }

}
