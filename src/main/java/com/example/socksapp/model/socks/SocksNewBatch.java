package com.example.socksapp.model.socks;

import com.example.socksapp.model.socks.Socks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksNewBatch {
    private Socks socks;
    private int numberOfPairs;
}


