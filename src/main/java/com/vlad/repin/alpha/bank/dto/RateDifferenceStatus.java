package com.vlad.repin.alpha.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RateDifferenceStatus {
    DID_NOT_CHANGE("zero"),
    INCREASED("rich"),
    DECREASED("broke");

    private String giphyTag;
}
