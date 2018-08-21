package com.zust.writeme.service.corpusService;

import com.zust.writeme.model.Corpus;

public interface CorpusService {

    int add(Corpus corpus);

    int delete(int corpusId);
}
