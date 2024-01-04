package com.zxw.jwxt.domain;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2020-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CourseSystem对象", description="")
public class CourseSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer status;


}
