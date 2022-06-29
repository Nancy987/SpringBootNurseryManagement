package com.Prograd.Springboot.Backend.Repository;

import com.Prograd.Springboot.Backend.Modals.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Integer> {
}
