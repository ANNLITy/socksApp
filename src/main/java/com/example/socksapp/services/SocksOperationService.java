package com.example.socksapp.services;

import com.example.socksapp.model.socks.SocksNewBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SocksOperationService {
    void accept(SocksNewBatch socksNewBatch);
    void issuance(SocksNewBatch socksNewBatch);
    void reject(SocksNewBatch socksNewBatch);
    File exportFile() throws IOException;
    void importFromFile(MultipartFile file) throws IOException;
}
