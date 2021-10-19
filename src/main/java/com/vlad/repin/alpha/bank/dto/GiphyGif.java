package com.vlad.repin.alpha.bank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyGif {
    Map<String, Object> data;
    Map<String, Object> meta;
}
