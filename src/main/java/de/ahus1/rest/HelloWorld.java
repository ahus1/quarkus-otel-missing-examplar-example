package de.ahus1.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 * @author Alexander Schwartz
 */
@Path("")
public class HelloWorld {

    @GET
    public String hello() {
        return "Hello, World!";
    }
}
