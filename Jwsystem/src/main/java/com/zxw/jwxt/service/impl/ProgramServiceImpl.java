package com.zxw.jwxt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxw.jwxt.domain.Program;
import com.zxw.jwxt.mapper.ProgramMapper;
import com.zxw.jwxt.service.IProgramService;
import com.zxw.jwxt.vo.QueryProgramVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2020-01-27
 */
@Service
public class ProgramServiceImpl extends ServiceImpl<ProgramMapper, Program> implements IProgramService {

    @Autowired
    private ProgramMapper programMapper;

    @Override
    public Program findProgram(QueryProgramVO queryProgram) {
        Program program = programMapper.findProgram(queryProgram.getSpecialtyId());
        return program;
    }
}
