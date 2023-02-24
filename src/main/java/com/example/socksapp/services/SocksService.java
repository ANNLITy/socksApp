package com.example.socksapp.services;

import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import com.example.socksapp.model.socks.SocksNewBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SocksService {
    void accept(SocksNewBatch socksNewBatch);
    int issuance(SocksNewBatch socksNewBatch);
    int reject(SocksNewBatch socksNewBatch);
    int getCount(Colors color, Size size, int cottonMin, int cottonMax);
    File exportFile() throws IOException;
    void importFromFile(MultipartFile file) throws IOException;
}
