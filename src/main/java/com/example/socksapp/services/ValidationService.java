package com.example.socksapp.services;

import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import com.example.socksapp.model.socks.SocksNewBatch;

public interface ValidationService {
    boolean validata(SocksNewBatch socksNewBatch);
    boolean validate(Colors color, Size size, int cottonMin, int cottonMax);
}
