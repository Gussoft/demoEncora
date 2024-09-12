package com.gussoft.demo.core.services.impl;

import com.gussoft.demo.core.dtos.ResponseDTO;
import com.gussoft.demo.core.services.GusService;
import com.gussoft.demo.integration.tranfer.request.RequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class GusServiceImpl implements GusService {


    //private final WebClient wc;

    public static final String url = "http://d0c79438-6cd6-488d-9b2c-66d9d742a0e9.mock.pstmn.io/notifications/mock";

    @Override
    public Mono<ResponseDTO> callService(RequestDTO request) {
        Mono<ResponseDTO> response = WebClient.create().put()
                .uri(url)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(1));
                })
                .body(Mono.just(request), RequestDTO.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseDTO.class);
        //Mono<String> info = request.map(RequestDTO::getUserId);
        Mono<ResponseDTO> r1 = response
                .map(r -> {
                            r.setUserId(request.getUserId());
                            r.setTimestamp(LocalDateTime.now());
                            return r;
                        }
                );
        log.info("RESPONSE");
        /*Mono<ResponseDTO> r2 = response.zipWith(info)
                .map(re -> {
                    String res = re.getT2();
                    re.getT1().setUserId(res);
                    re.getT1().setTimestamp(LocalDateTime.now());
                    return re.getT1();
                });
*/
        return r1;
    }


}
