package com.liuyang19900520.layman.starter.common.logger.factory;

import com.liuyang19900520.layman.starter.common.api.IResultCode;
import com.liuyang19900520.layman.starter.common.logger.database.entity.SysErrorLog;

/**
 * <p>
 * 日志工厂
 * </p>
 *
 * @author Max Liu
 * @since 2020/10/21
 */
public class LogFactory {
    /**
     * 创建错误日志
     */
    public static SysErrorLog createErrorLog(Long userId, IResultCode resultCode, String clazzName, String methodName, String path, String detail) {
        SysErrorLog sysErrorLog = new SysErrorLog();
        sysErrorLog.setErrorCode(resultCode.getCode());
        sysErrorLog.setErrorMsg(resultCode.getMessage());
        sysErrorLog.setPath(path);
        sysErrorLog.setDetail(detail);
        sysErrorLog.setClassName(clazzName);
        sysErrorLog.setMethod(methodName);
        sysErrorLog.setCreateUser(userId);
        sysErrorLog.setUpdateUser(userId);
        return sysErrorLog;
    }
}
