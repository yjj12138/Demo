package com.service.impl;

import com.bean.Major;
import com.dao.MajorMapper;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;
    //查询所有专业
    @Override
    public List<Major> getAll() {
        return majorMapper.getAll();
    }
}
