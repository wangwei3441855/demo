package com.ww.demo.apps.test.service;

import com.ww.demo.apps.test.domain.Test;
import com.ww.demo.apps.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangwei on 2017/6/19.
 */
@Service
public class TestServiceImpl implements TestService
{
    @Autowired
    private TestMapper testMapper;

    @Transactional(readOnly = true)
    public List<Test> getList()
    {
        return testMapper.getList();
    }
}
