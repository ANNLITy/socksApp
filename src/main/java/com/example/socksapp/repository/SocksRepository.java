package com.example.socksapp.repository;

import com.example.socksapp.model.socks.Socks;
import com.example.socksapp.model.socks.SocksNewBatch;

import java.util.List;
import java.util.Map;

public interface SocksRepository {
    void save(SocksNewBatch socksNewBatch);
    int remove(SocksNewBatch socksNewBatch);
    Map<Socks,Integer> getAll();
    List<SocksNewBatch> getList();
    void  replace(List<SocksNewBatch> socksNewBatchList);
}
