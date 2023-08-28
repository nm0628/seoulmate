package com.sparta.seoulmate.controller.comment;

import com.sparta.seoulmate.dto.ApiResponseDto;
import com.sparta.seoulmate.dto.comment.CommentListResponseDto;
import com.sparta.seoulmate.dto.comment.CommentRequestDto;
import com.sparta.seoulmate.dto.comment.CommentResponseDto;
import com.sparta.seoulmate.security.UserDetailsImpl;
import com.sparta.seoulmate.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // Comment 생성
    @PostMapping("/{postId}/comment")
    public ResponseEntity<ApiResponseDto> createComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(postId, userDetails.getUser(), commentRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 생성 성공", HttpStatus.OK.value()));
    }

    // Comment 단건 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.getComment(commentId);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // Comment 다건 조회
    @GetMapping("/comments")
    public ResponseEntity<CommentListResponseDto> getComments() {
        CommentListResponseDto commentResponseDto = commentService.getComments();
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // Comment 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(commentId, userDetails.getUser(), commentRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
    }

    // Comment 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}