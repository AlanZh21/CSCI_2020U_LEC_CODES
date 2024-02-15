package org.example.helloworldio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Path("/read")
public class HelloResource {

    ObjectMapper objectMapper = new ObjectMapper();
    @GET
    @Produces("text/html")
    public String listEndpoints(){
        String result = "Available endpoints include : <br>" +
                "api/read/{colName}: returns average of a column in a csv file <br>" +
                "api/read/book: Returns the word frequencies in a book";
        return result;
    }

    @GET
    @Produces("text/plain")
    @Path("/{colName}")
    public String getColAverage(@PathParam("colName") String colName) {
        String result = "Error";
        URL url = this.getClass().getClassLoader().getResource("/data/grades.csv"); //getClassLoader = gets path where compiled class are
        //getResource, get the resource
        System.out.println(url);
        File gradesFile = null;
        try {
            gradesFile = new File(url.toURI()); //expects URI, converts url
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //want to create a function to read csv file
        if(gradesFile != null){
            ExampleCSVParser csvParser = new ExampleCSVParser(gradesFile);
            result = csvParser.getColumnAverage(colName);
        }

        return result;
    }
    @GET
    @Produces("application/json")
    @Path("/book")
    public Response getWordFrequency() {
        URL url = this.getClass().getClassLoader().getResource("books"); //getClassLoader = gets path where compiled class are
        //getResource, get the resource
        System.out.println(url);
        File booksDir = null;
        try {
            booksDir = new File(url.toURI()); //expects URI, converts url
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //want to create a function to read csv file
        Response response = null;
        if(booksDir != null){
            ExampleBookParser bookParser = new ExampleBookParser();
            Map<String,Integer> wordFrequencyMap = bookParser.getWordFrequency(booksDir);
            try {
                response = Response.status(200)
                        .header("Access-Control-Allow-Origin", "http://localhost:63342")
                        .header("Content-Type", "application/json")
                        .entity(objectMapper.writeValueAsString(wordFrequencyMap))
                        .build();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    @POST
    @Consumes("text/plain")
    @Path("/save")
    public Response saveToFile(String data){
        try {
            Map<String, String> result = objectMapper.readValue(data, HashMap.class);
            System.out.println(result.get("title"));
            System.out.println(result.get("content"));

            URL url = this.getClass().getClassLoader().getResource("/data");
            File newFileDir = null;
            try {
                newFileDir = new File(url.toURI()); //expects URI, converts url
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            if(newFileDir != null){
                ExampleBookWriter bookWriter = new ExampleBookWriter();
                bookWriter.createBookFile(newFileDir, result.get("title"), result.get("content"));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        Response response = Response.status(200)
                .header("Content-Type", "application/json")
                .build();

        return response;
    }
}