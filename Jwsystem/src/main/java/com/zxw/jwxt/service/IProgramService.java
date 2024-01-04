package com.zxw.jwxt.service;

import com.zxw.jwxt.domain.Program;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.vo.QueryProgramVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-27
 */
public interface IProgramService extends IService<Program> {

    Program findProgram(QueryProgramVO queryProgram);
}
