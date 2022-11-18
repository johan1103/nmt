package SWM_RM.NMT.rest;

import SWM_RM.NMT.domain.dto.rest.MlScoreSet;
import SWM_RM.NMT.domain.dto.rest.RequestText;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class RestSend {

    public ResponseEntity<MlScoreSet> sendEngine(String text) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://3.39.208.21")
                .path("/getScore")
                .encode()
                .build()
                .expand(text) // {userId}, {userName}에 들어갈 값을 순차적으로 입력
                .toUri();

        System.out.println(uri);

        RequestText req = new RequestText();
        req.setAnswer(text);

        RequestEntity<RequestText> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("---------called RestSend");
        ResponseEntity<MlScoreSet> response = restTemplate.exchange(requestEntity, MlScoreSet.class);
        // RequestEntity객체와 반환 type을 적어서 출력
        System.out.println("get chong jum "+response.getBody().getChongjumScore());
        return response;
    }
}