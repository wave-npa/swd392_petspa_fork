package org.petspa.petcaresystem.boarding_detail.repository;

import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingDetailRepository extends JpaRepository<BoardingDetail, Long>{

}
