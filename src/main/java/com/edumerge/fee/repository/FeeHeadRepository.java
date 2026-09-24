package com.edumerge.fee.repository;

import com.edumerge.fee.entity.FeeHead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeHeadRepository extends JpaRepository<FeeHead, Long> {

    boolean existsByName(String name);
}