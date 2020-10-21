package com.liuyang19900520.layman.starter.common.logger.database.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyang19900520.layman.starter.common.logger.database.dao.SysErrorLogMapper;
import com.liuyang19900520.layman.starter.common.logger.database.entity.SysErrorLog;
import com.liuyang19900520.layman.starter.common.logger.database.service.SysErrorLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 错误日志 服务实现类
 * </p>
 *
 * @author Max Liu
 * @since 2020-10-21
 */
@Service
public class SysErrorLogServiceImpl extends ServiceImpl<SysErrorLogMapper, SysErrorLog> implements SysErrorLogService {

}
