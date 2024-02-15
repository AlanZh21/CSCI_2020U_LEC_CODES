package ca.uoit.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @Path("/{name}")
    @GET
    @Produces("text/plain")
    public String greeting(@PathParam("name") String str) {
        return "Hello, " + str; 
    }
}