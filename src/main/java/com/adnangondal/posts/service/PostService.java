package com.adnangondal.posts.service;

import com.adnangondal.posts.Exception.PostNotFoundException;
import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.mapper.PostMapper;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.model.PostsResponseModel;
import com.adnangondal.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public Long createPost(NewPostRequest request, Long user_id){
    Post post = postMapper.mapToEntity(request, user_id);
    return postRepository.save(post).getId();
  }

  public PostsResponseModel getAllPosts() {
   return PostsResponseModel.builder().posts(postRepository.findAll()).build();
  }

  public PostsResponseModel getPostsByUserId(Long userId){
    return PostsResponseModel.builder().posts(postRepository.findAllByUserId(userId)).build();
  }

  public Post getPostById(Long postId){
    return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
  }

  public void deletePostById(Long postId){
    if (!postRepository.existsById(postId)) {
      throw new PostNotFoundException(postId);
    }
   postRepository.deleteById(postId);
  }

}
