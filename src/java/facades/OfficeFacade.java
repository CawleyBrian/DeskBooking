/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entities.DailyDeskBooking;
import entities.Desk;
import entities.Employee;
import entities.Office;
import exceptions.DeskNotAvailableException;
import exceptions.WorkerDoesNotHaveAccessException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brian
 */
@Stateless
public class OfficeFacade extends AbstractFacade<Office> {

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;
    
    //used when an operation is required on a desk entity.
    private DeskFacade deskFacade;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OfficeFacade() {
        super(Office.class);
        this.deskFacade = new DeskFacade();
    }


    public boolean checkAccess(Long employeeId, Long officeId) {
        
        // Get employee and office instance from database
        Employee employee = em.find(Employee.class, employeeId);
        Office office = em.find(Office.class, officeId);
        
        // check if employee is in Office's set of assigned employees
        return office.getAssignedEmployees().contains(employee);
    }
    
    
    public DailyDeskBooking bookDesk(Long deskId, String bookingDate, Long employeeId)
            throws WorkerDoesNotHaveAccessException, DeskNotAvailableException{
        
        //get office Id of the Desk.
        long officeId = em.find(Desk.class, deskId).getOffice().getId();
        //throw exception if employee does not have access to the office.

        if(!checkAccess(employeeId, officeId)){
            throw new WorkerDoesNotHaveAccessException();
        }
        if(!deskFacade.isAvailable(deskId, bookingDate)){
                throw new DeskNotAvailableException();
        }
        
        return deskFacade.bookDesk(deskId, bookingDate, employeeId);
         
    }
    
    public boolean isAvailable(Long deskId, String bookingDate){
        return deskFacade.isAvailable(deskId, bookingDate);
        
    }

}
