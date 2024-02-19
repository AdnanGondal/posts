package com.adnangondal.posts.controller;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.model.PostsResponseModel;
import com.adnangondal.posts.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class PostsController {

  private final PostService postService;

  @PostMapping("/posts/user/{userId}")
  public ResponseEntity<Long> createPost(
      @PathVariable Long userId,
      @Valid @RequestBody NewPostRequest request) {

    Long postId = postService.createPost(request, userId);

    return new ResponseEntity<>(postId, HttpStatus.CREATED);
  }

  @GetMapping("/posts")
  public ResponseEntity<PostsResponseModel> getPosts(){
    PostsResponseModel posts = postService.getAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/user/{userId}")
  public ResponseEntity<PostsResponseModel> getPostsByUserId(@PathVariable("userId") Long userId){
    PostsResponseModel posts = postService.getPostsByUserId(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId){
    Post post = postService.getPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<Void> deletePostById(@PathVariable("postId") Long postId){
   postService.deletePostById(postId);
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/posts/{postId}")
  public ResponseEntity<Long> updatePostById(@PathVariable("postId") Long postId,
                                          @Valid @RequestBody NewPostRequest request ){
    Long id = postService.updatePostId(postId, request);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

}
