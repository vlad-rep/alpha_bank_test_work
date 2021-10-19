package com.vlad.repin.alpha.bank.serviсe;


import com.vlad.repin.alpha.bank.client.ExchangeClient;
import com.vlad.repin.alpha.bank.dto.ExchangeRates;
import com.vlad.repin.alpha.bank.dto.RateDifferenceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenExchangeService {
    private final ExchangeClient exchangeClient;

    @Value("${alpha-bank.open-exchange-rates.app-id}")
    private String appId;

    @Value("${alpha-bank.open-exchange-rates.currency}")
    private String currency;

    public RateDifferenceStatus getRateDifferenceBetweenTwoDays() {
        ExchangeRates currentRates = getHistoricalRates(LocalDateTime.now());
        ExchangeRates yesterdayRates = getHistoricalRates(LocalDateTime.now().minusDays(1));

        Double currentRate = currentRates.getRates().get(currency);
        log.info("currentRate = {}", currentRate);

        Double yesterdayRate = yesterdayRates.getRates().get(currency);
        log.info("yesterdayRate = {}", yesterdayRate);

        if (currentRate > yesterdayRate) {
            return RateDifferenceStatus.INCREASED;
        } else if (currentRate.equals(yesterdayRate)) {
            return RateDifferenceStatus.DID_NOT_CHANGE;
        } else return RateDifferenceStatus.DECREASED;
    }

    private ExchangeRates getHistoricalRates(LocalDateTime dateTime) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String afterFormatDate = formatDate.format(dateTime);
        log.debug("afterFormatDate = {}", afterFormatDate);
        ExchangeRates responseDateRate = exchangeClient.getHistoricalRates(afterFormatDate, appId);
        log.debug("responseDateRate = {}", responseDateRate);
        return responseDateRate;
    }
}
