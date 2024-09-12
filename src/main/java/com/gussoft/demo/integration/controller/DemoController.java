package com.gussoft.demo.integration.controller;

import com.gussoft.demo.core.dtos.ResponseDTO;
import com.gussoft.demo.core.services.GusService;
import com.gussoft.demo.integration.tranfer.request.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class DemoController {

    @Autowired
    private GusService service;

    @PostMapping("/demo")
    public Mono<ResponseDTO> call(@RequestBody RequestDTO requestDTO) {
        return service.callService(requestDTO);
    }
}
