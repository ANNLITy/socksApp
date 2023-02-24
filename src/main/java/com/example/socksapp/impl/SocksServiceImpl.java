package com.example.socksapp.impl;

import com.example.socksapp.exception.ValidationException;
import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import com.example.socksapp.model.socks.Socks;
import com.example.socksapp.model.socks.SocksNewBatch;
import com.example.socksapp.repository.SocksRepository;
import com.example.socksapp.services.FileService;
import com.example.socksapp.services.SocksOperationService;
import com.example.socksapp.services.ValidationService;
import com.example.socksapp.services.SocksService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private final SocksOperationService operationService;
    private  Path path = Path.of("src/main/resources","socks.json");

    @Override
    public void accept(SocksNewBatch socksNewBatch) {
        checkSocksBatch(socksNewBatch);
        operationService.accept(socksNewBatch);
        socksRepository.save(socksNewBatch);
    }

    @Override
    public int issuance(SocksNewBatch socksNewBatch) {
        checkSocksBatch(socksNewBatch);
        operationService.issuance(socksNewBatch);

        return socksRepository.remove(socksNewBatch);
    }

    @Override
    public int reject(SocksNewBatch socksNewBatch) {
        checkSocksBatch(socksNewBatch);
        operationService.reject(socksNewBatch);

        return socksRepository.remove(socksNewBatch);
    }

    @Override
    public int getCount(Colors color, Size size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
            Map<Socks, Integer> socksMap = socksRepository.getAll();
            for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
                Socks socks = socksItem.getKey();

                if (socks.getColor().equals(color) &&
                        socks.getSize().equals(size) &&
                        socks.getCuttonPart() >= cottonMin &&
                        socks.getCuttonPart() <= cottonMax) {
                    return socksItem.getValue();
                }
            }
            return 0;
        }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(),path).toFile();
    }

    @Override
    public void importFromFile(MultipartFile file) throws IOException {
     List<SocksNewBatch> socksNewBatchList =fileService.uploadFromFile(file, path, new TypeReference<List<SocksNewBatch>>() {});
     socksRepository.replace(socksNewBatchList);
    }


    private void checkSocksBatch(SocksNewBatch socksNewBatch) {
        if (!validationService.validata(socksNewBatch)) {
            throw new ValidationException();
        }
    }
}
