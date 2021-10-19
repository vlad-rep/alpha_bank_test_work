package com.vlad.repin.alpha.bank.controller;

import com.vlad.repin.alpha.bank.annotation.BaseExceptionHandler;
import com.vlad.repin.alpha.bank.dto.RateDifferenceStatus;
import com.vlad.repin.alpha.bank.serviсe.GiphyService;
import com.vlad.repin.alpha.bank.serviсe.OpenExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exchange/rate")
@Slf4j
@RequiredArgsConstructor
@BaseExceptionHandler
public class ExchangeRateController {

    private final OpenExchangeService openExchangeService;
    private final GiphyService giphyService;

    @GetMapping("difference/gif/get")
    public byte[] getGifByDifferenceTwoDaysRate() {
        log.info("Incoming request");
        RateDifferenceStatus result = openExchangeService.getRateDifferenceBetweenTwoDays();
        log.info("result = {}", result);
        return giphyService.getRandomGif(result);
    }
}
