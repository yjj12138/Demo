package com.service.impl;

import com.bean.Infotype;
import com.dao.InfotypeMapper;
import com.service.InfotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfotypeServiceImpl implements InfotypeService {
    @Autowired
    private InfotypeMapper infotypeMapper;
    @Override
    public List<Infotype> getAll() {
        return infotypeMapper.getAll();
    }
}
