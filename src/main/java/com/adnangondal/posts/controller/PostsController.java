package com.adnangondal.posts.controller;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class PostsController {

  private final PostService postService;

  @PostMapping("/posts/user/{userId}")
  public ResponseEntity<Long> createPost(
      @PathVariable Long userId,
      @RequestBody NewPostRequest request) {

    Long postId = postService.createPost(request, userId);

    return new ResponseEntity<>(postId, HttpStatus.CREATED);
  }

}
