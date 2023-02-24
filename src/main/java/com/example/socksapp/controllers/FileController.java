package com.example.socksapp.controllers;
import com.example.socksapp.services.SocksOperationService;
import com.example.socksapp.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/socks/files")
@Tag(name = "API для работы с файлами", description = "Импорт и экспорт файлов")
@RequiredArgsConstructor
public class FileController {

    private final SocksService socksService;
    private final SocksOperationService socksOperationService;

    @GetMapping("/export")
    @Operation(summary = "Выгрузка json-файла")
    public ResponseEntity<InputStreamResource> downloadSocksJson() {
        try {
            File socksFile = socksService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(socksFile.length()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("/import")
    @Operation(summary = "Загрузка json-файла")
    public ResponseEntity<String> uploadSocksJson(@RequestParam MultipartFile file){
        try {

            socksService.importFromFile(file);
            return ResponseEntity.ok("Файл импортирован");
        }catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка при загрузке файла");
        }
    }
    @GetMapping("/operation/export")
    @Operation(summary = "Выгрузка json-файла")
    public ResponseEntity<InputStreamResource> downloadSocksOperationJson() {
        try {
            File socksFile = socksOperationService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(socksFile.length()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("/operation/import")
    @Operation(summary = "Загрузка json-файла операций")
    public ResponseEntity<String> uploadSocksOperationJson(@RequestParam MultipartFile file) {
        try {

            socksOperationService.importFromFile(file);
            return ResponseEntity.ok("Файл импортирован");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка при загрузке файла");
        }
    }
}
