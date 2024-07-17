package org.petspa.petcaresystem.boarding_detail.service.implement;

import java.util.Collection;

import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.boarding_detail.repository.BoardingDetailRepository;
import org.petspa.petcaresystem.boarding_detail.service.BoardingDetailService;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardingDetailServiceImpl implements BoardingDetailService{

    @Autowired
    BoardingDetailRepository boardingDetailRepository;

    @Override
    public Collection<BoardingDetail> findAllBoardingDetail() {
        return boardingDetailRepository.findAll();
    }

    @Override
    public BoardingDetail findBoardingDetailById(Long boardingDetailId) {
        return boardingDetailRepository.findById(boardingDetailId).orElse(null);
    }

    @Override
    public BoardingDetail saveBoardingDetail(BoardingDetail boardingDetail) {
       return boardingDetailRepository.save(boardingDetail);
    }

    @Override
    public BoardingDetail updateBoardingDetail(BoardingDetail boardingDetail) {
        return boardingDetailRepository.save(boardingDetail);
    }

    @Override
    public BoardingDetail deleteBoardingDetail(Long boardingDetailId) {
        BoardingDetail newBoardingDetail = boardingDetailRepository.findById(boardingDetailId).orElse(null);
        newBoardingDetail.setStatus(Status.INACTIVE);
        return boardingDetailRepository.save(newBoardingDetail);
    }

}
