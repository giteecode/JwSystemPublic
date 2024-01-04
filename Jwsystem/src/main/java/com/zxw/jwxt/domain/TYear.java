package com.zxw.jwxt.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TYear对象", description="")
public class TYear implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String grade;

    private String name;


}
