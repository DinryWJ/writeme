package com.zust.writeme.service.corpusServiceImpl;

import com.zust.writeme.dao.CorpusMapper;
import com.zust.writeme.model.Corpus;
import com.zust.writeme.service.corpusService.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("corpusService")
public class CorpusServiceImpl implements CorpusService {
   @Autowired
    private CorpusMapper corpusMapper;

    @Override
    public int add(String articleName,int userId) {
        Corpus corpus = new Corpus();
        corpus.setCorpusName(articleName);
        corpus.setUserId(userId);
        return corpusMapper.insert(corpus);
    }

    @Override
    public int delete(int corpusId) {
        return corpusMapper.deleteByPrimaryKey(corpusId);
    }
}
