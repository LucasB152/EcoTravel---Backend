package be.ecotravel.back.controller;

import be.ecotravel.back.service.StepService;
import be.ecotravel.back.step.dto.StepAddingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/step")
public class StepController {
    private final StepService stepService;

    @Autowired
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @PostMapping()
    public void addStepToItinerary(@RequestBody StepAddingDto stepAddingDto){
        stepService.addStepToItinerary(stepAddingDto);
    }
}
