/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package resources;

import entities.DailyDeskBooking;
import entities.Office;
import exceptions.DeskNotAvailableException;
import exceptions.WorkerDoesNotHaveAccessException;
import facades.OfficeFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author brian
 */
@Path("office")
public class OfficeResource {

    OfficeFacade officeFacade = lookupOfficeFacadeBean();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OfficeResource
     */
    public OfficeResource() {
    }

    //GET method for retrieving Office.
    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOffice(@PathParam("id") Long id) {
        //variable to store office instance retrieved using office Id.
        Office office = officeFacade.find(id);

        //Return string representation of office if not null
        //Or return not found text string;
        return (office != null) ? office.toString() : "Office not found";
    }

    //Get desk availablity
    //using parameters Office Id, deskId, bookingDate
    @GET
    @Path("{id}/{deskId}/{bookingDate}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDeskAvailabilty(@PathParam("id") Long id,
            @PathParam("deskId") Long deskId,
            @PathParam("bookingDate") String bookingDate) {
        //variable to store office instance retrieved using office Id.
        Office office = officeFacade.find(id);

        // return error message if Office Id is not found
        if (office == null) {
            return "Invalid office Id";
        }

        // store desk availability in boolean variable
        Boolean available = officeFacade.isAvailable(deskId, bookingDate);

        // return string message for desk availability.
        if (available) {
            return "desk " + deskId + " is available on " + bookingDate;
        } else {
            return "desk " + deskId + " is not available on " + bookingDate;
        }

    }

    //POST method for making a booking
    @POST
    @Path("/create/{id}/{deskId}/{bookingDate}/{employeeId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String postBooking(@PathParam("id") Long id,
            @PathParam("deskId") Long deskId,
            @PathParam("bookingDate") String bookingDate,
            @PathParam("employeeId") Long employeeId)
            throws WorkerDoesNotHaveAccessException, DeskNotAvailableException {

        Office office = officeFacade.find(id);

        // return error message if Office Id is not found
        if (office == null) {
            return "Invalid office Id";
        }

        //Create booking using officeFacade method
        DailyDeskBooking booking = officeFacade.bookDesk(deskId, bookingDate,
                employeeId);

        //Return string representation of booking
        return booking.toString();

    }

    //Used to initialise facade variable
    private OfficeFacade lookupOfficeFacadeBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (OfficeFacade) c.lookup("java:global/WebApplication1/OfficeFacade!facades.OfficeFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
