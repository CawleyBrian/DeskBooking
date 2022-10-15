/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entities.DailyDeskBooking;
import entities.Desk;
import entities.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author brian
 */
@Stateless
public class DeskFacade extends AbstractFacade<Desk> {

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;
    
    private DailyDeskBookingFacade dailyDeskBookingFacade;
    private EmployeeFacade employeeFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeskFacade() {
        super(Desk.class);
        this.dailyDeskBookingFacade = new DailyDeskBookingFacade();
        this.employeeFacade = new EmployeeFacade();
    }
    
    
    public boolean isAvailable(Long deskId, String bookingDate){
        
        TypedQuery<DailyDeskBooking> query = em.createQuery(
        "SELECT e from DailyDeskBooking where e.deskId = '" + deskId + "' and"
                + " e.bookingdate = '" + bookingDate+"'", DailyDeskBooking.class);

        // if there is no booking, return true for available
        // or return false if there is an existing booking.
        return query.getSingleResult() == null;
    }
    
    public DailyDeskBooking bookDesk(Long deskId, String bookingDate, Long employeeId){
        
        //get the Desk and Employee instances
        Desk desk = em.find(Desk.class, deskId);
        Employee employee = em.find(Employee.class, employeeId);
        
        //create and set variables for booking
        DailyDeskBooking booking = new DailyDeskBooking();
        booking.setBookingDate(bookingDate);
        booking.setDesk(desk);
        booking.setEmployee(employee);
        
        //save new booking and add to Desk's and Employee's list of bookings.
        dailyDeskBookingFacade.create(booking);
        desk.getBookings().add(booking);
        employee.getBookings().add(booking);       
        em.merge(desk);
        em.merge(employee);
        return booking;
    }
    

    
}
