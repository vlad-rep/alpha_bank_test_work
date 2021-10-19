package com.vlad.repin.alpha.bank;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
public class ExchangeRateControllerTest extends CommonTest {

    private static final ParameterizedTypeReference<byte[]> BYTE_ARRAY_PARAMETERIZED_TYPE_REFERENCE =
        new ParameterizedTypeReference<>() {
        };

    @Test
    void getGifByDifferenceTwoDaysRateDownSuccessTest() {
        WireMockServer wireMockServer = new WireMockServer(8095);
        wireMockServer.start();

        createGetStub(
            wireMockServer,
            getUrlMockOpenExchangeRateForDate(LocalDateTime.now().minusDays(1)),
            loadFromResource("exchange.rate.previous.date.json")
        );
        createGetStub(
            wireMockServer,
            getUrlMockOpenExchangeRateForDate(LocalDateTime.now()),
            loadFromResource("exchange.rate.down.current.date.json")
        );
        createGetStub(
            wireMockServer,
            "/random?api_key=4wi6j1T6Pk3k7sdUTnyP326hj7aOBc13&tag=broke&rating=g",
            loadFromResource("giphy.broke.response.json")
        );
        createGetStub(
            wireMockServer,
            "/media/5hgIExulbZSIq04lLh/giphy.gif",
            loadFromResourceByteArray("giphy.broke.gif")
        );

        byte[] expectedResult = loadFromResourceByteArray("giphy.broke.gif");

        byte[] response = makeGet(
            "api/v1/exchange/rate/difference/gif/get",
            null,
            BYTE_ARRAY_PARAMETERIZED_TYPE_REFERENCE
        );

        Assertions.assertThat(response).isEqualTo(expectedResult);
        wireMockServer.stop();
    }

    private static String getUrlMockOpenExchangeRateForDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String result = formatDate.format(localDateTime);
        return "/historical/" + result + ".json?app_id=e777549ed8194bc9aa925506be9101a2";
    }

    private void createGetStub(WireMockServer wireMockServer, String url, byte[] body) {
        wireMockServer.stubFor(WireMock.get(url)
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                    .withBody(body)
            ));
    }

    private void createGetStub(WireMockServer wireMockServer, String url, String body) {
        wireMockServer.stubFor(WireMock.get(url)
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(body)
            ));
    }
}
