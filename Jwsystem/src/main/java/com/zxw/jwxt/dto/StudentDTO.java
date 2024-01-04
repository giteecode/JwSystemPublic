package com.zxw.jwxt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zxw
 * @date 2020/1/1 10:03
 */
@Data
public class StudentDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String sid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String sname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String spname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tcname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String gname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer absent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer attendance;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer usually;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer exam;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer score;
}
