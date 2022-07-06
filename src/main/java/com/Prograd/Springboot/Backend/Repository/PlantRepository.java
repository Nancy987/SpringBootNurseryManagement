package com.Prograd.Springboot.Backend.Repository;

import com.Prograd.Springboot.Backend.Modals.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlantRepository extends JpaRepository<Plant,Integer> {
    @Query("select u.cost FROM Plant u WHERE u.plant_id =:id")
    public float findCostByPlantId(@Param("id") int id);
}
