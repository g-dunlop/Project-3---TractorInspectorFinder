package com.codeclan.example.TractorFinder.components;

import com.codeclan.example.TractorFinder.models.Inspector;
import com.codeclan.example.TractorFinder.models.Tractor;
import com.codeclan.example.TractorFinder.repositories.InspectorRepository;
import com.codeclan.example.TractorFinder.repositories.TractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    TractorRepository tractorRepository;

    @Autowired
    InspectorRepository inspectorRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        Tractor tractor1 = new Tractor("Massey Ferguson");
        tractorRepository.save(tractor1);
        Tractor tractor2 = new Tractor("Fendt");
        tractorRepository.save(tractor2);
        Tractor tractor3 = new Tractor("John Deere");
        tractorRepository.save(tractor3);
        Tractor tractor4 = new Tractor("Case");
        tractorRepository.save(tractor4);

        Inspector inspector1 = new Inspector("Morse", "FK147NZ", "1 I Heart Tractors Wayyyyy", "012346847284", 56.143209, -3.634701);
        inspectorRepository.save(inspector1);
        inspector1.addTractor(tractor1);
        inspectorRepository.save(inspector1);

        inspector1.addTractor(tractor4);
        inspectorRepository.save(inspector1);
    }
}
