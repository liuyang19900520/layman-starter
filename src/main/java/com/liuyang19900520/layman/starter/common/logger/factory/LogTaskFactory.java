package com.liuyang19900520.layman.starter.common.logger.factory;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
import com.liuyang19900520.layman.starter.common.logger.database.entity.SysErrorLog;
import com.liuyang19900520.layman.starter.common.logger.database.service.SysErrorLogService;
import com.liuyang19900520.layman.starter.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;

import java.util.TimerTask;

/**
 * <p>
 * 日志任务工厂
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/21
 */
@DependsOn("springContextHolder")
@Slf4j
public class LogTaskFactory {

    private final static SysErrorLogService sysErrorLogService = SpringContextHolder.getBean(SysErrorLogService.class);

    public static TimerTask exceptionLog(final Long userId, final IResultCode resultCode,
                                         final String clazzName, final String methodName,
                                         final String path, final String detail
    ) {
        return new TimerTask() {
            @Override
            public void run() {
                SysErrorLog errorLog = LogFactory.createErrorLog(userId, resultCode, clazzName, methodName, path, detail
                );
                sysErrorLogService.save(errorLog);
                log.debug(Thread.currentThread().getName());
            }
        };
    }
}
