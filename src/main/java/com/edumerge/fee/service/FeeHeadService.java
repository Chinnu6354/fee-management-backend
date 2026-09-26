package com.edumerge.fee.service;

import com.edumerge.fee.entity.FeeHead;
import com.edumerge.fee.repository.FeeHeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeHeadService {

    private final FeeHeadRepository feeHeadRepository;

    public FeeHeadService(FeeHeadRepository feeHeadRepository) {
        this.feeHeadRepository = feeHeadRepository;
    }

    public FeeHead createFeeHead(FeeHead feeHead) {

        if (feeHeadRepository.existsByName(feeHead.getName())) {
            throw new RuntimeException("Fee head already exists");
        }

        return feeHeadRepository.save(feeHead);
    }

    public List<FeeHead> getAllFeeHeads() {
        return feeHeadRepository.findAll();
    }

    public FeeHead getFeeHeadById(Long id) {

        return feeHeadRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fee head not found"));
    }

    public FeeHead updateFeeHead(
            Long id,
            FeeHead updatedFeeHead) {

        FeeHead feeHead = getFeeHeadById(id);

        feeHead.setName(updatedFeeHead.getName());
        feeHead.setDescription(updatedFeeHead.getDescription());
        feeHead.setActive(updatedFeeHead.getActive());

        return feeHeadRepository.save(feeHead);
    }

    public void deleteFeeHead(Long id) {

        FeeHead feeHead = getFeeHeadById(id);

        feeHead.setActive(false);

        feeHeadRepository.save(feeHead);
    }
}