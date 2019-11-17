package com.tsystems.transportinfo.soap;

import javax.jws.WebService;

@WebService(
                portName = "HelloWorldImplPort",
                serviceName = "HelloWorldService",
                wsdlLocation = "META-INF/wsdl/HelloWorldService.wsdl",
                endpointInterface = "com.tsystems.transportinfo.soap.HelloWorld",
                targetNamespace = "http://soap.transportinfo.tsystems.com/"
        )
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String echo(String input) {
        System.out.println("input: " + input);
        return input;
    }

}
