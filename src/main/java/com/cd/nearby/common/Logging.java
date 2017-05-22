/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Asce
 */

@Aspect
@Component
public class Logging {

    @Autowired
    ObjectMapper mapper;

//    final static Logger logger = Logger.getLogger(Logging.class);
    static final Logger logger = Logger.getLogger("debugLogger");
    static final Logger resultLog = Logger.getLogger("reportsLogger");

    /**
     *
     * @param joinPoint
     * @param result
     * @throws JsonProcessingException
     */
    @AfterReturning(
            pointcut = "execution(public * com.cd.nearby.controller.NearbyController.getMerchant(..))  || "
                    + "execution(public * com.cd.nearby.controller.OfferController.getOffer(..))||"
                    + "execution(public * com.cd.nearby.controller.OfferController.updateOffer(..))||"
                    + "execution(public * com.cd.nearby.controller.OfferController.addOffer(..))||"
                    + "execution(public * com.cd.nearby.controller.OfferController.deleteOffer(..))",
            returning = "result")
    public void tokenLog(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        
        String response = mapper.writeValueAsString(result);
        String request = mapper.writeValueAsString(joinPoint.getArgs()[0]);
        logger.info("Request\t" + request);
        logger.info("Response\t" + response + "\n");
    }
    @AfterThrowing(
            pointcut = "execution (* com.cd.nearby.common.ExceptionHandling.exceptionHandling(..))"
                    ,
            throwing = "ex")
    public void ExcptionLog(Exception ex) {

        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        
        resultLog.error(errors.toString());
    }
}