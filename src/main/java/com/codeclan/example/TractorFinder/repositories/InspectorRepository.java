package com.codeclan.example.TractorFinder.repositories;

import com.codeclan.example.TractorFinder.models.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorRepository extends JpaRepository<Inspector, Long> {
}
