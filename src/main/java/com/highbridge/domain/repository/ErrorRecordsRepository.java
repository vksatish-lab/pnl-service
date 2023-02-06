package com.highbridge.domain.repository;

import com.highbridge.domain.ErrorRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRecordsRepository extends JpaRepository<ErrorRecords, Long> {
}
