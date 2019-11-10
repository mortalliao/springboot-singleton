package com.springboot.demo.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author Jim
 * <p>
 * 抽象父类实体，包含六个基本字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public abstract class BaseEntity<T extends Model<T>> extends Model<T> {

    /**
     * 数据版本(修改一版本累加1)
     */
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    protected Long version;

    /**
     * 删除标志( Y：已删除，N：未删除)
     */
    @TableLogic
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    protected String isDeleted;

    /**
     * 创建人的ID
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @Length(min = 1, max = 64, message = "长度不正确 创建人ID createdBy 1~64")
    @ApiModelProperty("创建人ID")
    protected String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    protected Date createTime;

    /**
     * 修改人的ID
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @Length(min = 1, max = 64, message = "长度不正确 修改人ID updatedBy 1~64")
    @ApiModelProperty("修改人ID")
    protected String updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    protected Date updateTime;

    public static final String VERSION = "version";
    public static final String IS_DELETED = "is_deleted";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String UPDATE_TIME = "update_time";

}
