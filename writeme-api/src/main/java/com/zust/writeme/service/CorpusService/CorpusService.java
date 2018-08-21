package com.zust.writeme.service.CorpusService;

import com.zust.writeme.model.Corpus;

public interface CorpusService {

    int add(Corpus corpus);

    int delete(int corpusId);
}
