package com.dr.service;

import com.dr.Repository.PlayerRepository;
import com.dr.Repository.BasketballClubRepository;
import com.dr.domain.Player;
import com.dr.exceptions.ExistsBasketballClubsForPlayerException;
import com.dr.exceptions.NoSuchPlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    private boolean ascending;

    @Autowired
    BasketballClubRepository basketballClubRepository;

    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    public Player getGroupOfStudent(Long BasketballClub_id) throws NoSuchPlayerException {
        Player groupOfStudents = playerRepository.findById(BasketballClub_id).get();//2.0.0.M7
        System.out.println(groupOfStudents);
        if (groupOfStudents == null) throw new NoSuchPlayerException();
        return groupOfStudents;
    }

    @Transactional
    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

    @Transactional
    public void updatePlayer(Player player, Long BasketballClub_id) throws NoSuchPlayerException {
        Player groupOfStudents1 = playerRepository.findById(BasketballClub_id).get();//2.0.0.M7

        if (groupOfStudents1 == null) throw new NoSuchPlayerException();
        groupOfStudents1.setBasketballClubs(player.getBasketballClubs());
        playerRepository.save(groupOfStudents1);
    }

    @Transactional
    public void updatePlayer(Long BasketballClub_id) throws NoSuchPlayerException, ExistsBasketballClubsForPlayerException {
        Player groupOfStudents = playerRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (groupOfStudents == null) throw new NoSuchPlayerException();
        if (groupOfStudents.getBasketballClubs().size() != 0) throw new ExistsBasketballClubsForPlayerException();
        playerRepository.delete(groupOfStudents);
    }


}
