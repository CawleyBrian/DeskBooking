/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author brian
 */
@Entity
@Table(name = "OFFICE")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Attributes
    @Size(max = 255)
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "INFORMATION")
    private String information;

    //Associations
    @OneToMany(mappedBy = "office")
    private Set<Desk> desks; //Desks assigned to this office.
    @ManyToMany
    private Set<Employee> assignedEmployees; //employees with access

    public Office() {
    }
    
    //getters and setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Set<Desk> getDesks() {
        return desks;
    }

    public void setDesks(Set<Desk> desks) {
        this.desks = desks;
    }

    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    
    public void setAssignedEmployees(Set<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Office[ id=" + id + " ]";
    }
    
}
