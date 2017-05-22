/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.common;

import com.cd.nearby.json.response.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Asce
 */
@Component
public class ExceptionHandling {

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response exceptionHandling(Exception ex) {
        
        ex.printStackTrace();
        Response exceptionResponse = new Response();
        exceptionResponse.setCode(1);
        exceptionResponse.setDesc("FAILURE");
        return exceptionResponse;
        
    }
}
