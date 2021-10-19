package com.vlad.repin.alpha.bank.serviсe;


import com.vlad.repin.alpha.bank.client.GifClient;
import com.vlad.repin.alpha.bank.client.GiphyClient;
import com.vlad.repin.alpha.bank.dto.GiphyGif;
import com.vlad.repin.alpha.bank.dto.RateDifferenceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiphyService {
    public static final String RATING = "g";
    public static final String ID_TAG = "id";

    private final GiphyClient giphyClient;
    private final GifClient gifClient;

    @Value("${alpha-bank.giphy.api-key}")
    private String apiKey;

    public byte[] getRandomGif(RateDifferenceStatus status) {
        GiphyGif giphyGif = giphyClient.getRandomGif(apiKey, status.getGiphyTag(), RATING);
        log.debug("giphyGif = {}", giphyGif);
        return getGif(giphyGif);
    }

    private byte[] getGif(GiphyGif gifMap) {
        String id = (String) gifMap.getData().get(ID_TAG);
        log.debug("gif id = {}", id);
        return gifClient.getGif(id);
    }
}
