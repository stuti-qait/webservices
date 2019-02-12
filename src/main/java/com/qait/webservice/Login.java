package com.qait.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qait.dao.Driver;
import com.qait.dao.Operation;

import org.json.JSONObject;

@Path("/login")
public class Login {

    Driver driver = new Driver();
    Connection con = driver.getDatabaseConnection();
	Operation operate = new Operation(con);
    String output = "";
    JSONObject jsonObject;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("uemail") String UEmail, @FormParam("upassword") String UPassword) {

        String pass = operate.getPassword(UEmail);
        System.out.println(pass);
        if( pass == null)
        {
            jsonObject = new JSONObject("{ 'message': 'User does not exist.'}");      
            return Response.status(401).entity(jsonObject.toString()).build();
        }
        else if(UPassword.equals(pass))
        {
            jsonObject = new JSONObject("{ 'message': 'Success'}");      
            return Response.status(200).entity(jsonObject.toString()).build();
        }
        
        jsonObject = new JSONObject("{ 'message': 'Bad Request'}");      
        return Response.status(400).entity(jsonObject.toString()).build();
    }

}