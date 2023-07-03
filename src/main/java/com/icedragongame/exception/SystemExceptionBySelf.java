package com.icedragongame.exception;

import com.icedragongame.myenum.SystemError;
import lombok.Data;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author gengxuelong
 *
 *
 */
@Data
public class SystemExceptionBySelf extends RuntimeException{

    SystemError systemError;
    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       构造函数
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public SystemExceptionBySelf(SystemError systemError){
        super(systemError.getMsg());
        this.systemError = systemError;
    }
}
