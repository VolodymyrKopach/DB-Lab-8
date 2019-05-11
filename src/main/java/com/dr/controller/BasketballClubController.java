package com.dr.controller;

import com.dr.DTO.impl.BasketballClubDTO;
import com.dr.domain.BasketballClub;
import com.dr.exceptions.*;
import com.dr.service.BasketballClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class BasketballClubController {
    @Autowired
    BasketballClubService basketballClubService;
// get BasketballClub by class id
    @GetMapping(value = "/api/BasketballClub/player/{player_id}")
    public ResponseEntity<List<BasketballClubDTO>> getBasketballClubsByPlayerId(@PathVariable Long player_id) throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        Set<BasketballClub> basketballClubSet= basketballClubService.getPlayerId(player_id);

        Link link = linkTo(methodOn(BasketballClubController.class).getAllBasketballClubs()).withSelfRel();

        List<BasketballClubDTO> basketballClubDTOS = new ArrayList<>();
        for (BasketballClub entity : basketballClubSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            BasketballClubDTO dto = new BasketballClubDTO(entity, selfLink);
            basketballClubDTOS.add(dto);
        }

        return new ResponseEntity<>(basketballClubDTOS, HttpStatus.OK);
    }
// get BasketballClub
    @GetMapping(value = "/api/BasketballClub/{BasketballClub_id}")
    public ResponseEntity<BasketballClubDTO> getBasketballClubs(@PathVariable Long BasketballClub_id) throws NoSuchBasketballClubException, NoSuchSponsorException, NoSuchPlayerException {
        BasketballClub basketballClubs = basketballClubService.getBasketballClubs(BasketballClub_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getBasketballClubs(BasketballClub_id)).withSelfRel();

        BasketballClubDTO basketballClubDTO = new BasketballClubDTO(basketballClubs, link);

        return new ResponseEntity<>(basketballClubDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/BasketballClub")
    public ResponseEntity<Set<BasketballClubDTO>> getAllBasketballClubs() throws NoSuchBasketballClubException, NoSuchSponsorException, NoSuchPlayerException {
        List<BasketballClub> basketballClubs = basketballClubService.getAllBasketballClubs();
        Link link = linkTo(methodOn(BasketballClubController.class).getAllBasketballClubs()).withSelfRel();

        Set<BasketballClubDTO> basketballClubDTOS = new HashSet<>();
        for (BasketballClub entity : basketballClubs) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            BasketballClubDTO dto = new BasketballClubDTO(entity, selfLink);
            basketballClubDTOS.add(dto);
        }

        return new ResponseEntity<>(basketballClubDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/BasketballClub/Sponsor/{sponsor_id}")
    public ResponseEntity<Set<BasketballClubDTO>> getBasketballClubsBySponsorID(@PathVariable Long sponsor_id) throws NoSuchSponsorException, NoSuchBasketballClubException, NoSuchPlayerException {
        Set<BasketballClub> basketballClubSet = basketballClubService.getStudentsByLecturerId(sponsor_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getAllBasketballClubs()).withSelfRel();

        Set<BasketballClubDTO> basketballClubDTOS = new HashSet<>();
        for (BasketballClub entity : basketballClubSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            BasketballClubDTO dto = new BasketballClubDTO(entity, selfLink);
            basketballClubDTOS.add(dto);
        }

        return new ResponseEntity<>(basketballClubDTOS, HttpStatus.OK);
    }
// add BasketballClub
    @PostMapping(value = "/api/BasketballClub/Player/{player_id}")
    public  ResponseEntity<BasketballClubDTO> addBasketballClub(@RequestBody BasketballClub basketballClub, @PathVariable Long player_id)
            throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        basketballClubService.createBasketballClub(basketballClub, player_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getBasketballClubs(basketballClub.getId())).withSelfRel();

        BasketballClubDTO basketballClubDTO = new BasketballClubDTO(basketballClub, link);

        return new ResponseEntity<>(basketballClubDTO, HttpStatus.CREATED);
    }
//update BasketballClub
    @PutMapping(value = "/api/BasketballClub/{BasketballClub_id}/player/{player_id}")
    public  ResponseEntity<BasketballClubDTO> updateBasketballClub(@RequestBody BasketballClub basketballClub,
                                                                   @PathVariable Long BasketballClub_id, @PathVariable Long player_id)
            throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        basketballClubService.updateBasketballClub(basketballClub, BasketballClub_id, player_id);
        BasketballClub basketballClub1 = basketballClubService.getBasketballClubs(BasketballClub_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getBasketballClubs(BasketballClub_id)).withSelfRel();

        BasketballClubDTO basketballClubDTO = new BasketballClubDTO(basketballClub1, link);

        return new ResponseEntity<>(basketballClubDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/BasketballClub/{BasketballClub_id}")
    public  ResponseEntity deleteBasketballClub(@PathVariable Long student_id) throws NoSuchBasketballClubException, ExistsSponsorForBasketballClubException, ExistsSponsorForBasketballClubException {
        basketballClubService.deleteBasketballClub(student_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/BasketballClub/{BasketballClub_id}/Sponsor/{sponsor_id}")
    public  ResponseEntity<BasketballClubDTO> addSponsorForBasketballClub(@PathVariable Long BasketballClub_id, @PathVariable Long sponsor_id)
            throws NoSuchBasketballClubException, NoSuchSponsorException, NoSuchPlayerException, AlreadyExistsSponsorInBasketballClubException, SponsorAbsentException {
        basketballClubService.addSponsorForBasketballClub(BasketballClub_id,sponsor_id);
        BasketballClub basketballClubs = basketballClubService.getBasketballClubs(BasketballClub_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getBasketballClubs(BasketballClub_id)).withSelfRel();

        BasketballClubDTO basketballClubDTO = new BasketballClubDTO(basketballClubs, link);

        return new ResponseEntity<>(basketballClubDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/BasketballClub/{BasketballClub_id}/{sponsor_id}")
    public  ResponseEntity<BasketballClubDTO> removeSponsorForBasketballClub(@PathVariable Long BasketballClub_id, @PathVariable Long sponsor_id)
            throws NoSuchBasketballClubException, NoSuchSponsorException, NoSuchPlayerException, BasketballClubHasNotSponsorException {
        basketballClubService.removeSponsorForBasketballClub(BasketballClub_id,sponsor_id);
        BasketballClub basketballClubs = basketballClubService.getBasketballClubs(BasketballClub_id);
        Link link = linkTo(methodOn(BasketballClubController.class).getBasketballClubs(sponsor_id)).withSelfRel();

        BasketballClubDTO basketballClubDTO = new BasketballClubDTO(basketballClubs, link);

        return new ResponseEntity<>(basketballClubDTO, HttpStatus.OK);
    }

}

