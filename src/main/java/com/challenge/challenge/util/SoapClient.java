package com.challenge.challenge.util;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/*
 * SOAP client, for all services, we'll return any object
 */
@Service
public class SoapClient extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request) {
        return (Object) getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
