package com.jintonic.shop.service;

import com.jintonic.shop.domain.board.Board;
import com.jintonic.shop.domain.board.BoardRepository;
import com.jintonic.shop.domain.board.BoardSpecification;
import com.jintonic.shop.domain.comment.CommentRepository;
import com.jintonic.shop.domain.user.User;
import com.jintonic.shop.domain.user.UserRepository;
import com.jintonic.shop.dto.BoardDto;
import com.jintonic.shop.dto.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {

    private UserService userService;

    private UserRepository userRepository;
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;

    public BoardService(UserService userService, UserRepository userRepository,
                        BoardRepository boardRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public Page<Board> findAll(Pageable pageable, Pagination pagination) {
        if (pagination.getKeyword() == null) {
            return boardRepository.findAll(pageable);
        }

        String keyword = pagination.getKeyword();
        return (pagination.filterMatcher(Pagination.FilterType.ALL)) ?
                boardRepository.findAll(Specifications.where(BoardSpecification.findByAll(keyword)), pageable) :
                boardRepository.findAll(Specifications.where(BoardSpecification.findByFilter(pagination)), pageable);
    }

    public Board save(String username, BoardDto dto) {
        User user = userRepository.findByUsername(username);
        return boardRepository.save(dto.toEntity(user));
    }

    @Transactional
    public Board update(String username, Long id, BoardDto dto) {
        Board board = boardRepository.findOne(id);
        userService.permissionCheck(username, board);
        return board.update(dto.getTitle(), dto.getContent());
    }

    public Board findOneAndHit(Long id) {
        Board board = boardRepository.findOne(id);
        board.increaseHit();
        boardRepository.save(board);
        return board;
    }

    public Board findOneForMod(String username, Long id) {
        Board board = boardRepository.findOne(id);
        userService.permissionCheck(username, board);
        return board;
    }

    @Transactional
    public void delete(String username, Long id) {
        Board board = boardRepository.findOne(id);
        userService.permissionCheck(username, board);
        commentRepository.delete(board.getComments());
        boardRepository.delete(id);
    }

}
