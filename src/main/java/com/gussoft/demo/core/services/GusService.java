package com.gussoft.demo.core.services;

import com.gussoft.demo.core.dtos.ResponseDTO;
import com.gussoft.demo.integration.tranfer.request.RequestDTO;
import reactor.core.publisher.Mono;

public interface GusService {

    Mono<ResponseDTO> callService(RequestDTO responseDTO);

}
