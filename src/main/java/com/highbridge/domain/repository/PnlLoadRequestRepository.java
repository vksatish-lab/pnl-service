package com.highbridge.domain.repository;

import com.highbridge.domain.PnlLoadRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PnlLoadRequestRepository extends JpaRepository<PnlLoadRequest, Long> {
}
