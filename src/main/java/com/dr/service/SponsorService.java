package com.dr.service;

import com.dr.Repository.BasketballClubRepository;
import com.dr.Repository.SponsorRepository;
import com.dr.domain.BasketballClub;
import com.dr.domain.Sponsor;
import com.dr.exceptions.ExistsBasketballClubsForSponsorException;
import com.dr.exceptions.NoSuchBasketballClubException;
import com.dr.exceptions.NoSuchSponsorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class SponsorService {
    @Autowired
    SponsorRepository sponsorRepository;

    @Autowired
    BasketballClubRepository basketballClubRepository;

    public Set<Sponsor> getSponsorsByBasketballClubId(Long BasketballClub_id) throws NoSuchBasketballClubException {
        BasketballClub basketballClub = basketballClubRepository.findById(BasketballClub_id).get();//2.0.0.M7
        if (basketballClub == null) throw new NoSuchBasketballClubException();
        return basketballClub.getSponsors();
    }

    public Sponsor getSponsor(Long sponsor_id) throws NoSuchSponsorException {
        Sponsor sponsor = sponsorRepository.findById(sponsor_id).get();//2.0.0.M7
        if (sponsor == null) throw new NoSuchSponsorException();
        return sponsor;
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    @Transactional
    public void createSponsor(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    @Transactional
    public void updateSponsor(Sponsor uSponsor, Long sponsor_id) throws NoSuchSponsorException {
        Sponsor sponsor= sponsorRepository.findById(sponsor_id).get();//2.0.0.M7
        if (sponsor == null) throw new NoSuchSponsorException();
        //update
        sponsor.setNameOfSponsor(uSponsor.getNameOfSponsor());
    }

    @Transactional
    public void deleteSponosr(Long sponsor_id) throws NoSuchSponsorException, ExistsBasketballClubsForSponsorException {
        Sponsor sponsor = sponsorRepository.findById(sponsor_id).get();//2.0.0.M7

        if (sponsor == null) throw new NoSuchSponsorException();
        if (sponsor.getBasketballClubSet().size() != 0) throw new ExistsBasketballClubsForSponsorException();
        sponsorRepository.delete(sponsor);
    }
}
