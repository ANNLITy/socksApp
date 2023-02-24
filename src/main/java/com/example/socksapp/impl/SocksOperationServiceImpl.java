package com.example.socksapp.impl;

import com.example.socksapp.model.operation.OperationType;
import com.example.socksapp.model.operation.SocksOperation;
import com.example.socksapp.model.socks.SocksNewBatch;
import com.example.socksapp.services.FileService;
import com.example.socksapp.services.SocksOperationService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SocksOperationServiceImpl implements SocksOperationService {
    private final FileService fileService;
    private List<SocksOperation> operationsList = new ArrayList<>();
    private Path path = Path.of("src/main/resources","socks.json");

    @Override
    public void accept(SocksNewBatch socksNewBatch) {
        operationsList.add(new SocksOperation(OperationType.ACCEPT, socksNewBatch));

    }

    @Override
    public void issuance(SocksNewBatch socksNewBatch) {
        operationsList.add(new SocksOperation(OperationType.ISSUANCE, socksNewBatch));

    }

    @Override
    public void reject(SocksNewBatch socksNewBatch) {
        operationsList.add(new SocksOperation(OperationType.REJECT, socksNewBatch));

    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(operationsList,path).toFile();

    }

    @Override
    public void importFromFile(MultipartFile file) throws IOException {
        operationsList =fileService.uploadFromFile(file, path, new TypeReference<List<SocksOperation>>() {});


    }
}
