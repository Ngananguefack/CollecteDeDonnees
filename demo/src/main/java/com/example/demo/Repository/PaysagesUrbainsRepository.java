package com.example.demo.Repository;

import com.example.demo.Entities.PaysagesUrbains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysagesUrbainsRepository extends JpaRepository<PaysagesUrbains, Integer> {
}