package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TComments;
import com.zxw.jwxt.dto.CommentDTO;
import com.zxw.jwxt.service.CommentsService;
import com.zxw.jwxt.vo.QueryCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/comment")
public class TCommentsController extends BaseController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/pageQuery")
    public ResponseEntity pageQuery(QueryCommentVO commentVO){
        IPage<CommentDTO> page =  commentsService.pageQuery(commentVO);
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody TComments tComments){
        RS rs= commentsService.add(tComments);
        return ResponseEntity.ok(rs);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody TComments tComments){
        RS rs= commentsService.edit(tComments);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String id){
        RS rs= commentsService.delete(id);
        return ResponseEntity.ok(rs);
    }

}
