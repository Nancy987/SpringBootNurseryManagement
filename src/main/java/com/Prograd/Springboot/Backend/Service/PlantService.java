package com.Prograd.Springboot.Backend.Service;

import com.Prograd.Springboot.Backend.Modals.Plant;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;

import java.util.List;

public interface PlantService {
    Plant savePlant(Plant Plant);
    List<Plant> getAllPlants();
    Plant getPlantById(int id) throws PlantNotFound;
    Plant updatePlant(Plant Plant,int id) throws PlantNotFound;
    void deletePlant(int id);
}
