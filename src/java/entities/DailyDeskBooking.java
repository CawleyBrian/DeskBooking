/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author brian
 */
@Entity
@Table(name = "DAILYDESKBOOKING")
public class DailyDeskBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BOOKINGDATE")
    private String bookingDate;
    @ManyToOne
    @JoinColumn(name = "desk_id",referencedColumnName="ID")
    private Desk desk;    
    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName="ID")
    private Employee employee;
    
    //constructor
    public DailyDeskBooking() {
    }
    
    //getter and setter methods

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyDeskBooking)) {
            return false;
        }
        DailyDeskBooking other = (DailyDeskBooking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DailyDeskBooking[ id=" + id + " ]";
    }
    
    
        /**
     * Get the value of bookingDate
     *
     * @return the value of bookingDate
     */
    public String getBookingDate() {
        return bookingDate;
    }

    /**
     * Set the value of bookingDate
     *
     * @param bookingDate new value of bookingDate
     */
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    
}