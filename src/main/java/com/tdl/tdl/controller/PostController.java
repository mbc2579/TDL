package com.tdl.tdl.controller;


import com.tdl.tdl.dto.PostRequestDto;
import com.tdl.tdl.dto.PostResponseDto;
import com.tdl.tdl.security.UserDetailsImpl;
import com.tdl.tdl.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/tdl/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 조회
    @GetMapping("")
    public String getPosts(Model model){
        List<PostResponseDto> post = postService.getPosts();
        model.addAttribute("postList", post);
        return "main";
    }

    // 게시물 등록
    @PostMapping("")
    @ResponseBody
    public PostResponseDto createPost (@RequestPart("contents") PostRequestDto requestDto,
                                       @RequestPart("fileName") List<MultipartFile> multipartFiles,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        if (multipartFiles == null) {
            throw new Exception("파일 업로드 필수");
        }
        return postService.createPost(requestDto, multipartFiles, userDetails.getUser());
    }

    // 게시물 선택 조회
    @GetMapping("/{id}")
    @ResponseBody
    public PostResponseDto getSelectPost(@PathVariable Long id){
        return postService.getSelectPost(id);
    }

    // 게시물 수정
    @PatchMapping("/{id}")
    @ResponseBody
    public PostResponseDto updatePost (
            @PathVariable Long id, @RequestPart("content") PostRequestDto requestDto,
            @RequestPart("imgUrl") List<MultipartFile> multipartFiles, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return postService.updatePost(id, requestDto, multipartFiles, userDetails.getUser());
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deletePost(
            @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return postService.deletePost(id, userDetails.getUser());
    }

    // 게시물 좋아요
    @PostMapping("/like/{id}")
    @ResponseBody
    public String likePost (@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.likePost(id, userDetails.getUser());
    }

    // 게시물 좋아요 취소
    @DeleteMapping("/like/{id}")
    @ResponseBody
    public String deleteLikePost (@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deleteLikePost(id, userDetails.getUser());
    }
}
