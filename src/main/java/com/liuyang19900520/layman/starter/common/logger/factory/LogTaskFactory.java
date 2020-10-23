package com.liuyang19900520.layman.starter.common.logger.factory;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
import com.liuyang19900520.layman.starter.common.logger.database.entity.SysErrorLog;
import com.liuyang19900520.layman.starter.common.logger.database.service.SysErrorLogService;
import com.liuyang19900520.layman.starter.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;

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
@DependsOn("springContextHolder")
@Slf4j
public class LogTaskFactory {

    private static SysErrorLogService sysErrorLogService = SpringContextHolder.getBean(SysErrorLogService.class);

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
                    log.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
