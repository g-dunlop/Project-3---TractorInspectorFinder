package com.codeclan.example.TractorFinder.controllers;

import com.codeclan.example.TractorFinder.models.Inspector;
import com.codeclan.example.TractorFinder.models.Tractor;
import com.codeclan.example.TractorFinder.repositories.InspectorRepository;
import com.codeclan.example.TractorFinder.repositories.TractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InspectorController {

    @Autowired
    InspectorRepository inspectorRepository;

    @Autowired
    TractorRepository tractorRepository;

    @GetMapping(value = "/inspectors")
    public ResponseEntity<List<Inspector>> getAllInspectors(){
        return new ResponseEntity<>(inspectorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/inspectors/{id}")
    public ResponseEntity getInspector(@PathVariable Long id){
        return new ResponseEntity<>(inspectorRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/inspectors")
    public ResponseEntity<Inspector> postInspector(@RequestBody Inspector inspector){
        Inspector inspectorToAdd = new Inspector(
                inspector.getName(),
                inspector.getPostcode(),
                inspector.getAddress(),
                inspector.getPhoneNumber(),
                inspector.getEmail(),
                inspector.getLat(),
                inspector.getLng()
        );

        for (int i=0; i<inspector.getNumberOfTractorIds(); i++) {
            int id = (int)inspector.getTractorIds().get(i);
            Tractor tractor = tractorRepository.findById((long) id).get();
            inspectorToAdd.addTractor(tractor);
        }
        inspectorRepository.save(inspectorToAdd);
        return new ResponseEntity<>(inspector, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "inspectors/{id}")
    public ResponseEntity<Long> deleteInspector(@PathVariable Long id){
        inspectorRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "inspectors/{id}")
    public ResponseEntity<Inspector> putInspector(@RequestBody Inspector inspector, @PathVariable Long id){
        Inspector inspectorToUpdate = inspectorRepository.findById(id).get();
        inspectorToUpdate.setName(inspector.getName());
        inspectorToUpdate.setPostcode((inspector.getPostcode()));
        inspectorToUpdate.setAddress(inspector.getAddress());
        inspectorToUpdate.setPhoneNumber(inspector.getPhoneNumber());
        inspectorToUpdate.setLat(inspector.getLat());
        inspectorToUpdate.setLng(inspector.getLng());
        inspectorRepository.save(inspectorToUpdate);
        return new ResponseEntity<>(inspectorToUpdate, HttpStatus.OK);
    }
}
