package com.dr.controller;

import com.dr.DTO.impl.PlayerDTO;
import com.dr.domain.Player;

import com.dr.exceptions.*;
import com.dr.service.PlayerService;
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
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/api/player")
    public ResponseEntity<Set<PlayerDTO>> getAllPlayers() throws NoSuchBasketballClubException, NoSuchSponsorException, NoSuchPlayerException {
        List<Player> GroupOfStudentSet = playerService.getAllPlayer();
        Link link = linkTo(methodOn(PlayerController.class).getAllPlayers()).withSelfRel();

        Set<PlayerDTO> groupOfStudentDTOS = new HashSet<>();
        for (Player entity : GroupOfStudentSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PlayerDTO dto = new PlayerDTO(entity, selfLink);
            groupOfStudentDTOS.add(dto);
        }

        return new ResponseEntity<>(groupOfStudentDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/player/{player_id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long player_id) throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        Player groupOfStudent = playerService.getGroupOfStudent(player_id);
        Link link = linkTo(methodOn(PlayerController.class).getPlayer(player_id)).withSelfRel();
        System.out.println(groupOfStudent);
        PlayerDTO cpuDTO = new PlayerDTO(groupOfStudent, link);

        return new ResponseEntity<>(cpuDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/player/{player_id}")
    public  ResponseEntity<PlayerDTO> addPlayer(@RequestBody Player player) throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        playerService.createPlayer(player);
        Link link = linkTo(methodOn(PlayerController.class).getPlayer(player.getId())).withSelfRel();

        PlayerDTO groupOfStudentDTO = new PlayerDTO(player, link);

        return new ResponseEntity<>(groupOfStudentDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/player/{player_id}")
    public  ResponseEntity<PlayerDTO> updatePlayer(@RequestBody Player uPlayer, @PathVariable Long player_id) throws NoSuchPlayerException, NoSuchBasketballClubException, NoSuchSponsorException {
        playerService.updatePlayer(uPlayer, player_id);
        Player groupOfStudent = playerService.getGroupOfStudent(player_id);
        Link link = linkTo(methodOn(PlayerController.class).getPlayer(player_id)).withSelfRel();

        PlayerDTO groupOfStudentDTO = new PlayerDTO(groupOfStudent, link);

        return new ResponseEntity<>(groupOfStudentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/player/{player_id}")
    public  ResponseEntity deletePlayer(@PathVariable Long player_id) throws NoSuchPlayerException, ExistsBasketballClubsForSponsorException, ExistsBasketballClubsForPlayerException {
        playerService.updatePlayer(player_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
