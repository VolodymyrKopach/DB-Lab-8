package com.dr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dr.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "player", schema = "lab_8_m_dr")
public class Player implements EntityInterface {
    private Long id;
    private int codeOfPlayer;

    public Player(Long id, int codeOfPlayer) {
        this.id = id;
        this.codeOfPlayer = codeOfPlayer;
    }

    private Set<BasketballClub> basketballClubByPlayer;

    @Id
    @Column(name = "id_of_player", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player(Long id) {
        this.id = id;
    }

    public Player() {
    }

    @Basic
    @Column(name = "code_of_player", nullable = true)
    public int getCodeOfPlayer() {
        return codeOfPlayer;
    }

    public void setCodeOfPlayer(int codeOfPlayer) {
        this.codeOfPlayer = codeOfPlayer;
    }


    @OneToMany(
            mappedBy = "playerByPlayer",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<BasketballClub> getBasketballClubs() {
        return basketballClubByPlayer;
    }
    public void setBasketballClubs(Set<BasketballClub> basketballClubs)
    {
        this.basketballClubByPlayer = basketballClubs;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return id == that.id &&
                Objects.equals(codeOfPlayer, that.codeOfPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeOfPlayer);
    }
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", codeOfPlayer='" + codeOfPlayer + '\'' +
                '}';
    }

}
