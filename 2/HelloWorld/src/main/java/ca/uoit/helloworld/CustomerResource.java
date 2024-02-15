package ca.uoit.helloworld;

import ca.uoit.util.CustomerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import ca.uoit.domain.Customers;
import ca.uoit.domain.Customer;
import jakarta.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {
    Customers customers;
    ObjectMapper objectMapper = new ObjectMapper();

    public CustomerResource(){
        try {
            this.customers = objectMapper.readValue(
                    CustomerUtil.readFileContents("/customers.json"),
                    Customers.class);
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadResource() { //reruns contructor which rereads the customers data
        try {
            this.customers = objectMapper.readValue(
                    CustomerUtil.readFileContents("/customers.json"),
                    Customers.class);
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


//    @GET
//    @Produces("application/json")
//    public String getAllCustomers() {
//        loadResource();
//        String customersList = "Error";
//        try {
//            customersList = objectMapper.writeValueAsString(customers);
//        } catch(JsonProcessingException e){
//            throw new RuntimeException(e);
//        }
//        return customersList;
//    }
    @GET
    @Produces("application/json")
    public Response getAllCustomers() {
        loadResource();
        String customersList = "Error";
        try {
            customersList = objectMapper.writeValueAsString(customers);
        } catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
        Response response = Response.status(200)
                .header("Access-Control-Allow-Origin", "http://localhost:63342")
                .header("Content-Type", "application/json")
                .entity(customers)
                .build();
        return response;
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getCustomerById(@PathParam("id") String cid)
    {
        loadResource();
        Customer cFound = customers.findCustomerById(cid);
        if(cFound != null)
        {
            try {
                return objectMapper.writeValueAsString(cFound);
            } catch (JsonProcessingException e)
            {
                throw new RuntimeException(e);
            }
        }
        return "ERORR ID NOT FOUND";
    }

}