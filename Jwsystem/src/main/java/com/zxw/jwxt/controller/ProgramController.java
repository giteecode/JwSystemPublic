package com.zxw.jwxt.controller;


import com.zxw.jwxt.domain.Program;
import com.zxw.jwxt.domain.TClasses;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.service.ClassesService;
import com.zxw.jwxt.service.IProgramService;
import com.zxw.jwxt.vo.QueryProgramVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-27
 */
@RestController
@RequestMapping("/api/program")
public class ProgramController extends BaseController {
    @Autowired
    private IProgramService programService;
    @Autowired
    private ClassesService classesService;

    @GetMapping("/findProgram")
    public ResponseEntity findProgram(QueryProgramVO queryProgram) {
        if (getRealm().getQx().equals("学生")) {
            TStudent student = (TStudent) getRealm();
            TClasses classes = classesService.findById(student.getClassesId());
            queryProgram.setSpecialtyId(classes.getSpecialtyId());
        }
        Program program = programService.findProgram(queryProgram);
        return ResponseEntity.ok(program);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Program program) {
        program.setCreateTime(new Date());
        boolean b = programService.save(program);
        return ResponseEntity.ok(b);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Program program) {
        program.setUpdateTime(new Date());
        boolean b = programService.updateById(program);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        boolean b = programService.removeById(id);
        return ResponseEntity.ok(b);
    }


}
