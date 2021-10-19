package com.vlad.repin.alpha.bank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "gif-client", url = "${alpha-bank.giphy.download-url}")
public interface GifClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/giphy.gif")
    byte[] getGif(@PathVariable String id);
}
