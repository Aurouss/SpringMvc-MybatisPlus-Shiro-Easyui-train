package com.wangzhixuan.service.impl;

import com.wangzhixuan.mapper.SysLogMapper;
import com.wangzhixuan.model.SysLog;
import com.wangzhixuan.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description：
 * @author：zhixuan.wang
 * @date：2015/10/30 10:40
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void insertLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
