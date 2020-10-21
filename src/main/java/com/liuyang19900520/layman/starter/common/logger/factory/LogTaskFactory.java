package com.liuyang19900520.layman.starter.common.logger.factory;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
import com.liuyang19900520.layman.starter.common.logger.database.entity.SysErrorLog;
import com.liuyang19900520.layman.starter.common.logger.database.service.SysErrorLogService;
import com.liuyang19900520.layman.starter.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.logging.LogManager;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/21
 */

public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static SysErrorLogService sysErrorLogService = SpringContextUtils.getBean(SysErrorLogService.class);

    public static TimerTask exceptionLog(final Long userId, final IResultCode resultCode,
                                         final String clazzName, final String methodName,
                                         final String path, final String detail
    ) {
        return new TimerTask() {
            @Override
            public void run() {
                SysErrorLog errorLog = LogFactory.createErrorLog(userId, resultCode, clazzName, methodName, path, detail
                );
                try {
                    sysErrorLogService.save(errorLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
