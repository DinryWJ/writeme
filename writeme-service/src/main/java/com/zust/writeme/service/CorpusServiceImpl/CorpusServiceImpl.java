package com.zust.writeme.service.CorpusServiceImpl;

import com.zust.writeme.dao.CorpusMapper;
import com.zust.writeme.model.Corpus;
import com.zust.writeme.service.CorpusService.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorpusServiceImpl implements CorpusService {
   @Autowired
    private CorpusMapper corpusMapper;

    @Override
    public int add(Corpus corpus) {
        return corpusMapper.insert(corpus);
    }

    @Override
    public int delete(int corpusId) {
        return corpusMapper.deleteByPrimaryKey(corpusId);
    }
}
