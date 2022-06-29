package com.Prograd.Springboot.Backend.Controllers;

import com.Prograd.Springboot.Backend.Modals.Plant;
import com.Prograd.Springboot.Backend.Security.Jwt.JwtTokenHelper;
import com.Prograd.Springboot.Backend.exceptions.PlantNotFound;
import com.Prograd.Springboot.Backend.Service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("")
public class PlantController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    private PlantService PlantService;

    public PlantController(PlantService PlantService) {
        this.PlantService = PlantService;
    }
    @PostMapping("admin/addPlant")
    public ResponseEntity<Plant> savePlant(@RequestBody Plant Plant){
        return new ResponseEntity<Plant>(PlantService.savePlant(Plant), HttpStatus.CREATED);
    }
    @GetMapping("plants")
    public List<Plant> getAllPlants(){
        return PlantService.getAllPlants();
    }
    @GetMapping("admin/plant/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("id") int id) throws PlantNotFound {
        return new ResponseEntity<Plant>(PlantService.getPlantById(id), HttpStatus.OK);
    }
    @PutMapping("admin/plant/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable("id") int id,@RequestBody Plant Plant) throws PlantNotFound {
        return new ResponseEntity<Plant>(PlantService.updatePlant(Plant,id), HttpStatus.OK);
    }
    @DeleteMapping("admin/plant/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable("id") int id){
        PlantService.deletePlant(id);
        return new ResponseEntity<String>("Plant is deleted successfully.",HttpStatus.OK);
    }
}
