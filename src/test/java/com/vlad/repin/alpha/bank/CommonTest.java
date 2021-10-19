package com.vlad.repin.alpha.bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommonTest {

    @LocalServerPort
    public int serverPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    public <T, R> T makeGet(
        String url,
        R request,
        ParameterizedTypeReference<T> responseType
    ) {
        HttpEntity<R> httpEntity = new HttpEntity<>(request);

        ResponseEntity<T> response = restTemplate.exchange(
            getFullUrl(url),
            HttpMethod.GET,
            httpEntity,
            responseType
        );

        Assertions
            .assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.OK);

        return response.getBody();
    }

    public String loadFromResource(String resourceName) {
        return ResourceLoader.loadFromResource(CommonTest.class, resourceName);
    }

    public byte[] loadFromResourceByteArray(String resourceName) {
        return ResourceLoader.loadFromResourceAsByteArray(CommonTest.class, resourceName);
    }

    public <T> T loadFromJson(String resourceName, TypeReference<T> typeReference) {
        return ResourceLoader.loadFromJson(objectMapper, CommonTest.class, resourceName, typeReference);
    }

    public String getFullUrl(String urlTail) {
        return "http://localhost:" + serverPort + "/" + urlTail;
    }
}
