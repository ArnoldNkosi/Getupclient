package za.co.metropolitan.getup.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.LifeHackProductRequestDto;
import za.co.metropolitan.getup.client.dto.LifeHackRequestDto;
import za.co.metropolitan.getup.client.dto.LifeHackResponseDto;
import za.co.metropolitan.getup.client.service.LifeHackService;

import java.util.List;

@RestController
@RequestMapping(value = "/lifehacks")
public class LifeHackController {


    @Autowired
    private LifeHackService lifeHackService;

    @CrossOrigin
    @RequestMapping(value = "/validClientLifehacks", method = RequestMethod.POST, consumes = "application/json")
    public List<LifeHackResponseDto> getValidClientLifeHacks(@RequestBody LifeHackRequestDto request) throws Exception {
        List<LifeHackResponseDto> lifeHackList = lifeHackService.getValidLifeHacksForClient(request.getClientIDNumber(),request.getProductName(),request.getStartPeriod(),request.getEndPeriod());
        return lifeHackList;

    }


    @CrossOrigin
    @RequestMapping(value = "/validProductLifehacks", method = RequestMethod.POST, consumes = "application/json")
    public List<String> getValidProductLifeHacks(@RequestBody LifeHackProductRequestDto request) throws Exception {
        List<String> lifeHackList = lifeHackService.getValidLifeHacksForProduct(request.getProductName());
        return lifeHackList;

    }
}
