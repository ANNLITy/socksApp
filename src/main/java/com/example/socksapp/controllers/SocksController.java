package com.example.socksapp.controllers;

import com.example.socksapp.controllers.dto.ResponseDto;
import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import com.example.socksapp.model.socks.SocksNewBatch;
import com.example.socksapp.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учёта носков")
@RequiredArgsConstructor
public class SocksController {
    private  final SocksService socksService;

@PostMapping
@Operation(summary = "Регистрирует приход товара на склад",description = "Параметры запроса передаются в теле запроса в виде JSON-объекта")
@ApiResponse(responseCode = "200",description = "Удалось добавить приход")
@ApiResponse(responseCode = "400",description = "Параметры запроса отсутствуют или имеют некорректный формат")
@ApiResponse(responseCode = "500",description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto>accept(@RequestBody SocksNewBatch socksNewBatch){
    socksService.accept(socksNewBatch);
    return ResponseEntity.ok(new ResponseDto("Носки успешно добавлены на склад"));

    }
    @GetMapping
    @Operation(summary = "Регистрирует приход товара на склад",description = "Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается")
    @ApiResponse(responseCode = "200",description = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа")
    @ApiResponse(responseCode = "400",description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto>getCount(@RequestBody Colors color, @RequestParam Size size,@RequestParam int cottonMin, @RequestParam int cottonMax){
        int socksCount = socksService.getCount(color,size,cottonMin,cottonMax);
        return ResponseEntity.ok(new ResponseDto(socksCount+ " - количество носков"));

    }
    @PutMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponse(responseCode = "200",description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400",description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto>issuance(@RequestBody SocksNewBatch socksNewBatch){
    int socksCount = socksService.issuance(socksNewBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount+ " носков отпущено со склада"));

    }
    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков. ")
    @ApiResponse(responseCode = "200",description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400",description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto>reject(@RequestBody SocksNewBatch socksNewBatch){
        int socksCount = socksService.reject(socksNewBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount+ " - количество носков, списанных со склада"));

    }
}
