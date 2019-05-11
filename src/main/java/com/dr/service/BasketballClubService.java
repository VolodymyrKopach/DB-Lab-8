package com.dr.service;

import com.dr.Repository.PlayerRepository;
import com.dr.Repository.BasketballClubRepository;
import com.dr.Repository.SponsorRepository;
import com.dr.domain.BasketballClub;
import com.dr.domain.Player;
import com.dr.domain.Sponsor;
import com.dr.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class BasketballClubService {
    @Autowired
    BasketballClubRepository basketballClubRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    SponsorRepository sponsorRepository;

    public Set<BasketballClub> getPlayerId(Long BasketballClub_id) throws NoSuchPlayerException {
        Player player = playerRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (player == null) throw new NoSuchPlayerException();
        return player.getBasketballClubs();
    }

    public BasketballClub getBasketballClubs(Long BasketballClub_id) throws NoSuchBasketballClubException {
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        return basketballClub;
    }

    public List<BasketballClub> getAllBasketballClubs() {
        return basketballClubRepository.findAll();
    }

    public Set<BasketballClub> getStudentsByLecturerId(Long sponsor_id) throws NoSuchSponsorException {
        Sponsor sponsor = sponsorRepository.findById(sponsor_id).get();//2.0.0.M7
        if (sponsor == null) throw new NoSuchSponsorException();
        return sponsor.getBasketballClubSet();
    }

    @Transactional
    public void createBasketballClub(BasketballClub basketballClub, Long player_id) throws NoSuchPlayerException {
        if (player_id > 0) {
            Player player = playerRepository.findById(player_id).get();//2.0.0.M7
            if (player == null) throw new NoSuchPlayerException();
            basketballClub.setPlayerByPlayer(player);
        }
        basketballClubRepository.save(basketballClub);
    }

    @Transactional
    public void updateBasketballClub(BasketballClub uBasketballClub, Long BasketballClub_id, Long BasketballClubId) throws NoSuchPlayerException, NoSuchBasketballClubException {
        Player player = playerRepository.findById(BasketballClubId).get();//2.0.0.M7
        if (BasketballClubId > 0) {
            if (player == null) throw new NoSuchPlayerException();
        }
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        basketballClub.setNameOfBasketballClub(uBasketballClub.getNameOfBasketballClub());
        basketballClub.setFinances(uBasketballClub.getFinances());
        if (BasketballClubId > 0) basketballClub.setPlayerByPlayer(player);
        else basketballClub.setPlayerByPlayer(null);
        basketballClubRepository.save(basketballClub);
    }

    @Transactional
    public void deleteBasketballClub(Long BasketballClub_id) throws NoSuchBasketballClubException, ExistsSponsorForBasketballClubException {
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        if (basketballClub.getSponsors().size() != 0) throw new ExistsSponsorForBasketballClubException();
        basketballClubRepository.delete(basketballClub);
    }

    @Transactional
    public void addSponsorForBasketballClub(Long BasketballClub_id, Long sponsor_id)
            throws NoSuchBasketballClubException, NoSuchSponsorException, AlreadyExistsSponsorInBasketballClubException, SponsorAbsentException {
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        Sponsor sponsor = sponsorRepository.findById(sponsor_id).get();//2.0.0.M7
        if (sponsor == null) throw new NoSuchSponsorException();
        if (basketballClub.getSponsors().contains(sponsor) == true) throw new AlreadyExistsSponsorInBasketballClubException();
        basketballClub.getSponsors().add(sponsor);
        basketballClubRepository.save(basketballClub);
    }

    @Transactional
    public void removeSponsorForBasketballClub(Long BasketballClub_id, Long sponsor_id)
            throws NoSuchBasketballClubException, NoSuchSponsorException, BasketballClubHasNotSponsorException {
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        Sponsor sponsor= sponsorRepository.findById(sponsor_id).get();//2.0.0.M7
        if (sponsor == null) throw new NoSuchSponsorException();
        if (basketballClub.getSponsors().contains(sponsor) == false) throw new BasketballClubHasNotSponsorException();
        basketballClub.getSponsors().remove(sponsor);
        basketballClubRepository.save(basketballClub);
    }
}
