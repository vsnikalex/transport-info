package com.tsystems.transportinfo.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorld {

    @WebMethod
    String echo(String input);

}
