package cn.iocoder.ssy.module.bpm.dal.dataobject.definition;

import cn.iocoder.ssy.framework.common.enums.CommonStatusEnum;
import cn.iocoder.ssy.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.ssy.framework.mybatis.core.type.JsonLongSetTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Set;

/**
 * BPM 用户组
 *
 * @author  Ssy
 */
@TableName(value = "bpm_user_group", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmUserGroupDO extends BaseDO {

    /**
     * 编号，自增
     */
    @TableId
    private Long id;
    /**
     * 组名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 成员用户编号数组
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> userIds;

}
