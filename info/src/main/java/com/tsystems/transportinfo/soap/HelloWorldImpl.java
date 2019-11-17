package com.tsystems.transportinfo.soap;

import javax.jws.WebService;

@WebService(
                portName = "HelloWorldImplPort",
                serviceName = "HelloWorldServiceLocal",
                wsdlLocation = "META-INF/wsdl/HelloWorldService.wsdl",
                endpointInterface = "com.tsystems.transportinfo.soap.HelloWorld",
                targetNamespace = "http://soap.transportinfo.tsystems.com/"
        )
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String echo(String input)
    {
        return input;
    }

}
