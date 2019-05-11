package com.dr.controller;

import com.dr.DTO.impl.SponsorDTO;
import com.dr.domain.Sponsor;
import com.dr.exceptions.ExistsBasketballClubsForSponsorException;
import com.dr.exceptions.NoSuchBasketballClubException;
import com.dr.exceptions.NoSuchSponsorException;
import com.dr.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SponsorController {
    @Autowired
    SponsorService sponsorService;

    @GetMapping(value = "/api/sponsor/BasketballClub/{BasketballClub_id}")
    public ResponseEntity<Set<SponsorDTO>> getSponsorByBasketballClubID(@PathVariable Long BasketballClub_id) throws NoSuchBasketballClubException, NoSuchSponsorException {
        Set<Sponsor> sponsorSet = sponsorService.getSponsorsByBasketballClubId(BasketballClub_id);
        Link link = linkTo(methodOn(SponsorController.class).getAllSponsors()).withSelfRel();

        Set<SponsorDTO> sponsorDTOS = new HashSet<>();
        for (Sponsor entity : sponsorSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            SponsorDTO dto = new SponsorDTO(entity, selfLink);
            sponsorDTOS.add(dto);
        }

        return new ResponseEntity<>(sponsorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/sponsor/{sponsor_id}")
    public ResponseEntity<SponsorDTO> getSponsor(@PathVariable Long sponsor_id) throws NoSuchSponsorException, NoSuchBasketballClubException {
        Sponsor sponsor = sponsorService.getSponsor(sponsor_id);
        Link link = linkTo(methodOn(SponsorController.class).getSponsor(sponsor_id)).withSelfRel();

        SponsorDTO sponsorDTO = new SponsorDTO(sponsor, link);

        return new ResponseEntity<>(sponsorDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/sponsor")
    public ResponseEntity<Set<SponsorDTO>> getAllSponsors() throws NoSuchSponsorException, NoSuchBasketballClubException {
        List<Sponsor> sponsors = sponsorService.getAllSponsors();
        Link link = linkTo(methodOn(SponsorController.class).getAllSponsors()).withSelfRel();

        Set<SponsorDTO> sponsorDTOS = new HashSet<>();
        for (Sponsor entity : sponsors) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            SponsorDTO dto = new SponsorDTO(entity, selfLink);
            sponsorDTOS.add(dto);
        }

        return new ResponseEntity<>(sponsorDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/api/sponsor")
    public ResponseEntity<SponsorDTO> addSponsor(@RequestBody Sponsor sponsor) throws NoSuchSponsorException, NoSuchBasketballClubException {
        sponsorService.createSponsor(sponsor);
        Link link = linkTo(methodOn(SponsorController.class).getSponsor(sponsor.getId())).withSelfRel();

        SponsorDTO sponsorDTO = new SponsorDTO(sponsor, link);

        return new ResponseEntity<>(sponsorDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/sponsor/{sponsor_id}")
    public ResponseEntity<SponsorDTO> updateSponsor(@RequestBody Sponsor uSponsor, @PathVariable Long sponsor_id) throws NoSuchSponsorException, NoSuchBasketballClubException {
        sponsorService.updateSponsor(uSponsor, sponsor_id);
        Sponsor sponsor = sponsorService.getSponsor(sponsor_id);
        Link link = linkTo(methodOn(SponsorController.class).getSponsor(sponsor_id)).withSelfRel();

        SponsorDTO sponsorDTO = new SponsorDTO(sponsor, link);

        return new ResponseEntity<>(sponsorDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/sponsor/{sponsor_id}")
    public  ResponseEntity deleteSponsor(@PathVariable Long sponsor_id) throws ExistsBasketballClubsForSponsorException, NoSuchSponsorException {
        sponsorService.deleteSponosr(sponsor_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
