package com.adnangondal.posts.Exception;

public class PostNotFoundException extends RuntimeException {

  public PostNotFoundException(Long postId) {
    super("Post not found with id: " + postId);
  }
}
