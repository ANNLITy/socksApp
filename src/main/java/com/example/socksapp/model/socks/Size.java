package com.example.socksapp.model.socks;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Size {
    S36(36),
    S37(37),
    S38(38),
    S39(39),
    S40(40),
    S41(41),
    S42(42),
    S43(43),
    S44(44),
    S45(45);

    private int size;

    public int getSize() {
        return size;
    }
}