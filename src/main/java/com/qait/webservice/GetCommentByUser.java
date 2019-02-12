package com.qait.webservice;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qait.dao.Driver;
import com.qait.dao.Operation;

import org.json.JSONObject;

@Path("/comments")
public class GetCommentByUser {

    Driver driver = new Driver();
    Connection con = driver.getDatabaseConnection();
    Operation operate = new Operation(con);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response commentsByUser(@QueryParam("uemail") String UEmail) {

        String output = operate.readCommentByUser(UEmail);
        if(output != null)
        {
            return Response.status(200).entity(output).build();
        }
        return Response.status(204).build();
    }

}