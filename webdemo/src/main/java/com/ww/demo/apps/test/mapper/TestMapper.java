package com.ww.demo.apps.test.mapper;

import com.ww.demo.apps.test.domain.Test;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestMapper
{
    List<Test> getList();
}
