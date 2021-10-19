package com.vlad.repin.alpha.bank.client;

import com.vlad.repin.alpha.bank.dto.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "open-exchange-client", url = "${alpha-bank.open-exchange-rates.url}")
public interface ExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "historical/{date}.json")
    ExchangeRates getHistoricalRates(
        @PathVariable String date,
        @RequestParam("app_id") String appId
    );
}
