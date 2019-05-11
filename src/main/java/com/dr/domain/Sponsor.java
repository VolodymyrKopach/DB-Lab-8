package com.dr.domain;

import com.dr.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sponsor", schema = "lab_8_m_dr")
public class Sponsor implements EntityInterface {
    private Long id;
    private String nameOfSponsor;
    private String surnameOfSponsor;
    private Set<BasketballClub> basketballClubs = new HashSet<>();
    @Id
    @Column(name = "id_of_sponsor", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_sponsor", nullable = false, length = 50)
    public String getNameOfSponsor() {
        return nameOfSponsor;
    }

    public void setNameOfSponsor(String nameOfSponsor) {
        this.nameOfSponsor = nameOfSponsor;
    }

    @Basic
    @Column(name = "surname_of_sponsor", nullable = true, length = 50)
    public String getSurnameOfSponsor() {
        return surnameOfSponsor;
    }

    public void setSurnameOfSponsor(String surnameOfSponsor) {
        this.surnameOfSponsor = surnameOfSponsor;
    }


    @ManyToMany(targetEntity = BasketballClub.class, mappedBy="sponsors")
    public Set<BasketballClub> getBasketballClubSet() {
        return basketballClubs;
    }

    public void setBasketballClubSet(Set<BasketballClub> mobiles) {
        this.basketballClubs = mobiles;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sponsor that = (Sponsor) o;
        return id == that.id &&
                Objects.equals(nameOfSponsor, that.nameOfSponsor) &&
                Objects.equals(surnameOfSponsor, that.surnameOfSponsor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfSponsor, surnameOfSponsor);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", nameOfSponsor= " + nameOfSponsor
                + ", surnameOfSponsor= " + surnameOfSponsor;
    }
}
