package com.Prograd.Springboot.Backend.Service;

import com.Prograd.Springboot.Backend.Modals.Plant;
import com.Prograd.Springboot.Backend.Repository.PlantRepository;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantServiceImpl implements PlantService{
    private PlantRepository PlantRepository;

    public PlantServiceImpl(PlantRepository PlantRepository) {
        this.PlantRepository = PlantRepository;
    }

    @Override
    public Plant savePlant(Plant Plant) {
        return PlantRepository.save(Plant);
    }

    @Override
    public List<Plant> getAllPlants() {
        return PlantRepository.findAll();
    }

    @Override
    public Plant getPlantById(int id) throws PlantNotFound {
        Plant Plant = PlantRepository.findById(id).orElseThrow(()->new PlantNotFound("Plant not exist"));
        return Plant;
    }

    @Override
    public Plant updatePlant(Plant Plant, int id) throws PlantNotFound {
        Plant existingPlant = PlantRepository.findById(id).orElseThrow(()->new PlantNotFound(("Plant not exist")));

        existingPlant.setPlant_name(Plant.getPlant_name());
        existingPlant.setPlant_name(Plant.getPlant_name());
        existingPlant.setCategory(Plant.getCategory());
        existingPlant.setDescription(Plant.getDescription());
        existingPlant.setCost(Plant.getCost());

        return PlantRepository.save(existingPlant);
    }

    @Override
    public void deletePlant(int id) {
        PlantRepository.deleteById(id);
    }
}
