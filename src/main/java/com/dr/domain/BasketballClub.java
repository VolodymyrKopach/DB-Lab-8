package com.dr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dr.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "basketball_club", schema = "lab_8_m_dr")
public class BasketballClub implements EntityInterface {
    private Long id;
    private String nameOfBasketballClub;
    private String finances;
    private Player playerByPlayer;
    Set<Sponsor> sponsors = new HashSet<>();


    public BasketballClub(String nameOfBasketballClub, String finances, Player playerByPlayer) {
        this.nameOfBasketballClub = nameOfBasketballClub;
        this.finances = finances;
        this.playerByPlayer = playerByPlayer;
    }

    public BasketballClub(Long id, String nameOfBasketballClub, String finances) {
        this.id = id;
        this.nameOfBasketballClub = nameOfBasketballClub;
        this.finances = finances;
    }

    public BasketballClub() {
    }


    @Id
    @Column(name = "id_of_basketball_club", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_basketball_club", nullable = true, length = 50)
    public String getNameOfBasketballClub() {
        return nameOfBasketballClub;
    }

    public void setNameOfBasketballClub(String nameOfBasketballClub) {
        this.nameOfBasketballClub = nameOfBasketballClub;
    }

    @Basic
    @Column(name = "finances", nullable = true, length = 50)
    public String getFinances() {
        return finances;
    }

    public void setFinances(String finances) {
        this.finances = finances;
    }

    @ManyToOne
    @JoinColumn(name = "id_of_player", referencedColumnName = "id_of_player", nullable = false)
    @JsonIgnore
    public Player getPlayerByPlayer() {
        return playerByPlayer;
    }

    public void setPlayerByPlayer(Player playerByPlayer) {
        this.playerByPlayer = playerByPlayer;
    }


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "basketball_club_sponsor",
            joinColumns = { @JoinColumn(name = "id_of_basketball_club", referencedColumnName = "id_of_basketball_club", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_of_sponsor", referencedColumnName = "id_of_sponsor", nullable = false), }
    )
    @JsonIgnore
    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    public void addBasketballClubSponsor(Sponsor sponsor){
        if(!getSponsors().contains(sponsor)){
            getSponsors().add(sponsor);
        }
        if(!sponsor.getBasketballClubSet().contains(this)){
            sponsor.getBasketballClubSet().add(this);
        }
    }

    public void deleteSponsorEntity(Sponsor sponsor){
        if(getSponsors().contains(sponsor)){
            getSponsors().remove(sponsor);
        }
        if(sponsor.getBasketballClubSet().contains(this)){
            sponsor.getBasketballClubSet().remove(this);
        }
    }



    @Override
    public String toString() {
        return "MobileEntity{" +
                "id=" + id +
                ", nameOfBasketballClub='" + nameOfBasketballClub + '\'' +
                ", finances='" + finances + '\'' +
                ", sponsors=" + sponsors +
                '}';
    }

    public String toStringJoinTable(){
        return "BasketballClubEntity{" +
                "id=" + id +
                " sponsors=" + sponsors +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketballClub that = (BasketballClub) o;
        return id == that.id &&
                Objects.equals(nameOfBasketballClub, that.nameOfBasketballClub) &&
                Objects.equals(finances, that.finances);}


    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfBasketballClub, finances);
    }



}
