package com.dr.DTO.impl;

import com.dr.DTO.DTO;
import com.dr.domain.Player;
import com.dr.domain.BasketballClub;
import com.dr.exceptions.NoSuchBasketballClubException;
import com.dr.exceptions.NoSuchPlayerException;
import com.dr.exceptions.NoSuchSponsorException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class BasketballClubDTO extends DTO<BasketballClub> {
    public BasketballClubDTO(BasketballClub basketballClub, Link link) throws NoSuchSponsorException, NoSuchPlayerException, NoSuchBasketballClubException {
        super(basketballClub, link);
    }

    public Long getBasketballClubId() {
        return getEntity().getId();
    }

    public String getNameOfBasketballClub() {
        return getEntity().getNameOfBasketballClub();
    }

    public String getFinances() {
        return getEntity().getFinances();
    }

    public Player getPlayer() {
        return getEntity().getPlayerByPlayer();
    }


}
