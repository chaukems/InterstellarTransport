/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.shortest.path.util;

import javax.servlet.ServletException;

/**
 *
 * @author schauke
 */
public class UnauthorizedException extends ServletException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

}
