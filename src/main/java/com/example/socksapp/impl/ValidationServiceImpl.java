package com.example.socksapp.impl;

import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import com.example.socksapp.model.socks.SocksNewBatch;
import com.example.socksapp.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validata(SocksNewBatch socksNewBatch) {
        return socksNewBatch.getSocks() != null
                && socksNewBatch.getNumberOfPairs()>0
                && socksNewBatch.getSocks().getColor() != null
                && socksNewBatch.getSocks().getSize() != null
                && checkCotton(socksNewBatch.getSocks().getCuttonPart(),socksNewBatch.getSocks().getCuttonPart());
    }

    @Override
    public boolean validate(Colors color, Size size, int cottonMin, int cottonMax) {
        return color != null && size != null && checkCotton(cottonMin,cottonMax);
    }
    private boolean checkCotton(int cottonMin, int cottonMax){
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
