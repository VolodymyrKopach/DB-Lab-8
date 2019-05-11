package com.dr.Repository;

import com.dr.domain.BasketballClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface BasketballClubRepository extends JpaRepository<BasketballClub, Long> {

}
