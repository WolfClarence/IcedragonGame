package com.icedragongame.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.icedragongame.constant.ConstantBySelf;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *       自定义元数据对象处理器
 *
 *@author songyuxuan
 *
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());

        /*有字段则填写,无则不填*/
        this.setFieldValByName("buildTime",(new Date(System.currentTimeMillis())),metaObject);
        this.setFieldValByName("scanNum",(0),metaObject);
        this.setFieldValByName("replyNum",(0),metaObject);
        this.setFieldValByName("auditStatus","未审核",metaObject);
        this.setFieldValByName("userStatus",ConstantBySelf.DEFAULT_USER_STATE,metaObject);
        this.setFieldValByName("userPoints",ConstantBySelf.DEFAULT_USER_POINTS,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
