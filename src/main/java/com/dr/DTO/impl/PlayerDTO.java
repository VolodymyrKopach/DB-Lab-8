package com.dr.DTO.impl;

import com.dr.DTO.DTO;
import com.dr.controller.BasketballClubController;
import com.dr.domain.Player;
import com.dr.domain.BasketballClub;
import com.dr.exceptions.NoSuchBasketballClubException;
import com.dr.exceptions.NoSuchPlayerException;
import com.dr.exceptions.NoSuchSponsorException;
import org.springframework.hateoas.Link;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class PlayerDTO extends DTO<Player> {
    public PlayerDTO(Player player, Link link) throws NoSuchPlayerException, NoSuchSponsorException, NoSuchBasketballClubException {
        super(player, link);
        add(linkTo(methodOn(BasketballClubController.class).getBasketballClubsByPlayerId(getEntity().getId())).withRel("mobile"));
    }

    public Long getBasketballClubId() {
        return getEntity().getId();
    }

    public Integer getCodeOfPlayer() {
        return getEntity().getCodeOfPlayer();
    }
  

    public Set<BasketballClub> getBasketballClubs() {
        return getEntity().getBasketballClubs();
    }
}
