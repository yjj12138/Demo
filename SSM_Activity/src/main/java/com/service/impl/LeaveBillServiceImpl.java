package com.service.impl;

import com.bean.Leavebill;
import com.dao.LeavebillMapper;
import com.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    @Autowired
    private LeavebillMapper leavebillMapper;

    @Override
    public int insertSelective(Leavebill record) {
        return leavebillMapper.insertSelective(record);
    }

    @Override
    public List<Leavebill> getList(int uid) {
        return leavebillMapper.getList(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(Leavebill record) {
        return leavebillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Leavebill selectByPrimaryKey(Integer id) {
        return leavebillMapper.selectByPrimaryKey(id);
    }
}
