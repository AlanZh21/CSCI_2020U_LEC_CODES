package ca.uoit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.Objects;
public class Customers {

    @JsonProperty("customers")
    public List<Customer> customers;

    public Customer findCustomerById(String id)
    {
        Customer cFound = null;
        for(Customer c : customers)
        {
            if(Objects.equals(c.getId(), id))
            {
                cFound = c;
                break;
            }
        }
        return cFound;
    }
}
