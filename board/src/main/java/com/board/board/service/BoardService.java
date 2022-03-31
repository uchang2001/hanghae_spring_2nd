package com.board.board.service;

import com.board.board.entity.board;
import com.board.board.repository.BoardRepository;
import com.board.board.entity.Comment;
import com.board.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;
    public void write(board board){

        boardRepository.save(board);

    }

    public List<board> boardList(){
        return boardRepository.findAll();
    }

    public board boardview(Integer id){

        return boardRepository.findById(id).get();
    }
    public Comment commentview(Integer id){

        return commentRepository.findById(id).get();
    }

    public List<Comment> commentList(){
        return commentRepository.findAll();
    }
    public void writecom(Comment comment){


        commentRepository.save(comment);

    }
    public void deleteComment(Integer id){
        commentRepository.deleteById(id);

    }
}
