package com.example.socksapp.impl;

import com.example.socksapp.model.socks.Socks;
import com.example.socksapp.model.socks.SocksNewBatch;
import com.example.socksapp.repository.SocksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private HashMap<Socks, Integer> socksMap = new HashMap<>();
    @Override
    public void save(SocksNewBatch socksNewBatch) {
        Socks socks = socksNewBatch.getSocks();
        if (socksMap.containsKey(socks)){
          socksMap.replace(socks,socksMap.get(socks)+socksNewBatch.getNumberOfPairs());
        }else {
            socksMap.put(socks,socksNewBatch.getNumberOfPairs());
        }

    }

    @Override
    public int remove(SocksNewBatch socksNewBatch) {
    Socks socks =socksNewBatch.getSocks();

if (socksMap.containsKey(socks)){
    int number = socksMap.get(socks);

    if (number>socksNewBatch.getNumberOfPairs()){
        socksMap.replace(socks,socksMap.get(socks)-socksNewBatch.getNumberOfPairs());
        return socksNewBatch.getNumberOfPairs();
    }else {
socksMap.remove(socks);
return number;
    }
}
return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return null;
    }

    @Override
    public List<SocksNewBatch> getList() {
        List<SocksNewBatch> socksNewBatchList = new ArrayList<>();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            socksNewBatchList.add(new SocksNewBatch(socksItem.getKey(),socksItem.getValue()));
        }

        return socksNewBatchList;
    }

    @Override
    public void replace(List<SocksNewBatch> socksNewBatchList) {
        socksMap.clear();

        for (SocksNewBatch batch: socksNewBatchList) {
            save(batch);
        }
    }
}
