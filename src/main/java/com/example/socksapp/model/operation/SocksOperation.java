package com.example.socksapp.model.operation;

import com.example.socksapp.model.socks.SocksNewBatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksOperation {
    private OperationType type;
    private SocksNewBatch socksNewBatch;
    private final LocalDateTime dateTime= LocalDateTime.now();
}
