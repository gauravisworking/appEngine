package com.test.helloWorld.webservice;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
@Path("/hr/")
public class EmployeeResource {
 
 @GET
 @Produces("text/plain")
 @Path("/employee") 
 public String getEmployee() {
        return "Hello World!";
    }
 
}