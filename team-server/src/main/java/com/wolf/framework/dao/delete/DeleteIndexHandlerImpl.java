package com.wolf.framework.dao.delete;

import com.wolf.framework.lucene.HdfsLucene;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.index.Term;

/**
 *
 * @author aladdin
 */
public class DeleteIndexHandlerImpl implements DeleteHandler {

    private final DeleteHandler deleteHandler;
    private final HdfsLucene hdfsLucene;

    public DeleteIndexHandlerImpl(DeleteHandler deleteHandler, HdfsLucene hdfsLucene) {
        this.deleteHandler = deleteHandler;
        this.hdfsLucene = hdfsLucene;
    }

    @Override
    public void delete(String keyValue) {
        //删除数据
        this.deleteHandler.delete(keyValue);
        //删除索引
        Term term = new Term(HdfsLucene.KEY_NAME, keyValue);
        this.hdfsLucene.deleteDocument(term);
    }

    @Override
    public void batchDelete(List<String> keyValues) {
        //删除数据
        this.deleteHandler.batchDelete(keyValues);
        //删除索引
        Term term;
        List<Term> termList = new ArrayList<Term>(keyValues.size());
        for (String keyValue : keyValues) {
            term = new Term(HdfsLucene.KEY_NAME, keyValue);
            termList.add(term);
        }
        this.hdfsLucene.deleteDocument(termList);
    }
}