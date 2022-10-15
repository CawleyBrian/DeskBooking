/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author brian
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "EMPLOYEE_NAME")
    private String name;
    
    //associations

    //An empployee can have multiple office assinged and vice versa
    @ManyToMany(targetEntity=Office.class, mappedBy = "assignedEmployees")
    private Set<Office> offices;
    //A booking is mapped to one employee
    @OneToMany(mappedBy = "employee")
    private List<DailyDeskBooking> bookings;
    
    public Employee() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Office> getOffices() {
    return offices;
    }
    
    public void setOffices(Set<Office> offices) {
    this.offices = offices;
    }
    
    public List<DailyDeskBooking> getBookings() {
    return bookings;
    }
    
    public void setBookings(List<DailyDeskBooking> bookings) {
    this.bookings = bookings;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Employee[ id=" + id + " ]";
    }
    
}
