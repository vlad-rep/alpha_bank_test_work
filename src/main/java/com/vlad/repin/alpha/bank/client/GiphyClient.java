package com.vlad.repin.alpha.bank.client;

import com.vlad.repin.alpha.bank.dto.GiphyGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy-client", url = "${alpha-bank.giphy.main-url}")
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    GiphyGif getRandomGif(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag,
            @RequestParam("rating") String rating
    );
}
