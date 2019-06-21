package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloService {
    @GET
    public String getHello() {
        return "Hello!";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public String postHello(String name) {
        return "Hello: " + name;
    }

}
