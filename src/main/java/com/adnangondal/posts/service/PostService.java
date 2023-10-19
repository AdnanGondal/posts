package com.adnangondal.posts.service;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.mapper.PostMapper;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.model.PostsResponseModel;
import com.adnangondal.posts.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
   PostsResponseModel response = new PostsResponseModel();
   response.setPosts(postRepository.findAll());
   return response;
  }

}
