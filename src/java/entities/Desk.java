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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author brian
 */
@Entity
@Table(name = "DESK")
public class Desk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne //Desk is mapped to one Office.
    @JoinColumn(name="office_id")
    private Office office;
    @OneToMany(mappedBy = "desk") //Many bookings against the same desk.
    private Set<DailyDeskBooking> bookings;

    public Desk(){
    }
    
    //Getters and setters methods
    
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {    
        this.office = office;
    }

    public Set<DailyDeskBooking> getBookings() {
        return bookings;
    }

    
    public void setBookings(Set<DailyDeskBooking> bookings) {
        this.bookings = bookings;
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
        if (!(object instanceof Desk)) {
            return false;
        }
        Desk other = (Desk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Desk[ id=" + id + " ]";
    }
    
}