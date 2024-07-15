package org.petspa.petcaresystem.boarding_detail.service;

import java.util.Collection;

import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.stereotype.Service;

@Service
public interface BoardingDetailService {

    Collection<BoardingDetail> findAllBoardingDetail();

    BoardingDetail findBoardingDetailById(Long boardingDetailId);

    BoardingDetail saveBoardingDetail(BoardingDetail boardingDetail);

    BoardingDetail updateBoardingDetail(BoardingDetail boardingDetail);

    BoardingDetail deleteBoardingDetail(Long boardingDetailId);

}
