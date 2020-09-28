/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.shortest.path.util;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author schauke
 */
@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private String jwttoken;

}
