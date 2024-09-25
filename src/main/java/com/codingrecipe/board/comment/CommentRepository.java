package com.codingrecipe.board.comment;

import com.codingrecipe.board.board.BoardEntity;
import com.codingrecipe.board.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}