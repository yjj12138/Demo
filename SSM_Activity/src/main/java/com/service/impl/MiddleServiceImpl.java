package com.service.impl;

import com.dao.MiddleMapper;
import com.service.MiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiddleServiceImpl implements MiddleService {
    @Autowired
    private MiddleMapper middleMapper;
    @Override
    @Transactional
    public int deleteRoleAndMenubyRid(int roleid) {
        return middleMapper.deleteRoleAndMenubyRid(roleid);
    }
}
