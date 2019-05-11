package com.dr.DTO.impl;

import com.dr.DTO.DTO;
import com.dr.domain.Sponsor;
import com.dr.exceptions.NoSuchSponsorException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class SponsorDTO extends DTO<Sponsor> {
    public SponsorDTO(Sponsor sponsor, Link link) throws NoSuchSponsorException {
        super(sponsor, link);
    }

    public Long getSponsorId() {
        return getEntity().getId();
    }

    public String getNameOfSponsor() {
        return getEntity().getNameOfSponsor();
    }

    public String getSurnameOfSponsor() {
        return getEntity().getSurnameOfSponsor();
    }
}
