package com.board.board.Controller;

import com.board.board.entity.board;
import com.board.board.entity.Comment;
import com.board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping("/write")
    public String boardwriteForm(){

        return "boardwrite";
    }
    @PostMapping("/writepro")
    public String boardwritepro(board board){
//        System.out.println(board.getTitle());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date now = new Date();
        String nowTime = sdf2.format(now);
        board.setDdate(nowTime);

        boardService.write(board);

        return"redirect:/list";
    }
    @GetMapping("/list")
    public String boardlist(Model model){
        model.addAttribute("list",boardService.boardList());

        return"boardlist";
    }
    @GetMapping("/view")
    public String boardview(Model model,Integer id){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = ((UserDetails) principal).getUsername();
        //String password = principal.getPassword();



        model.addAttribute("board",boardService.boardview(id));
        model.addAttribute("comments",boardService.commentList());
        model.addAttribute("un",username);

        return "boardview";
    }
    @PostMapping("/writeproc")
    public String commentwriteproc(Comment comment){
//        comment.setBoardid(1);
//        comment.setUsername("sdf");
       //comment.setComment("dddd");

        String s= String.valueOf(comment.getBoardid());

        boardService.writecom(comment);

        return"redirect:/view?id="+s;
    }

    @GetMapping("/delete")
    public String deleteComment(Integer id,Integer bid){
        boardService.deleteComment(id);
        String s=String.valueOf(bid);
        return"redirect:/view?id="+s;
    }
    @GetMapping("/modify")
    public String commentmodifyForm(Integer id,Integer bid,Model model){
        model.addAttribute("comm",boardService.commentview(id));

        return "modify";
    }
    @PostMapping("/cupdate")
    public String commentupdate(Comment comment){
        Comment comtemp=boardService.commentview(comment.getId());
//        comtemp.setBoardid(comment.getBoardid());
        comtemp.setContent(comment.getContent());
//        comtemp.setUsername(comment.getUsername());
        String s=String.valueOf(comment.getBoardid());
        boardService.writecom(comtemp);
        return"redirect:/view?id="+s;
    }


}
