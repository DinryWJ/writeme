package com.zust.writeme.service.corpusService;



public interface CorpusService {

    int add(String articleName,int userId);

    int delete(int corpusId);
}
