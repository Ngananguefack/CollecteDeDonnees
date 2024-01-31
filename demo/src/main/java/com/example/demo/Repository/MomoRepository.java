package com.example.demo.Repository;

import com.example.demo.Entities.Cadre;
import com.example.demo.Entities.Momo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomoRepository extends JpaRepository<Momo,Integer> {
}
