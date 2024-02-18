package com.adnangondal.posts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.mapper.PostMapper;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.model.PostsResponseModel;
import com.adnangondal.posts.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostServiceTest {

  @Mock
  private PostRepository postRepository;

  private PostMapper postMapper = Mappers.getMapper(PostMapper.class);


  private PostService postsService;

  @BeforeEach
  public void setup() {
    // Initialize mocks and inject them into the service
    MockitoAnnotations.openMocks(this);

    postsService = new PostService(postRepository, postMapper);
  }

  @Test
  public void testCreatePost() {
    // Arrange
    NewPostRequest request = NewPostRequest
        .builder()
        .content("Hello World")
        .imageUrl("imageUrl.com")
        .build();
    Long userId = 1L;

    Post createdPost = new Post();
    createdPost.setId(1L);
    createdPost.setContent(request.getContent());
    createdPost.setImageUrl(request.getImageUrl());
    createdPost.setUserId(userId);

    // Mock the behavior of the PostRepository
    when(postRepository.save(any(Post.class))).thenReturn(createdPost);

    // Act
    Long postId = postsService.createPost(request, userId);

    // Assert
    assertThat(postId).isEqualTo(1L);
  }

  @Test
  public void testGetAllPosts() {
    // Arrange
    var postBuilder = Post.builder();

    List<Post> mockPosts = List.of(
        postBuilder.id(1L).content("Test Post 1").imageUrl("test1.jpg").userId(1L).build(),
        postBuilder.id(2L).content("Test Post 2").userId(2L).build()
    );

    // Mock the behavior of the PostRepository
    when(postRepository.findAll()).thenReturn(mockPosts);

    // Act
    PostsResponseModel result = postsService.getAllPosts();

    // Assert
    assertThat(result.getPosts())
        .hasSize(2)
        .extracting("id", "content")
        .containsExactly(
            tuple(1L, "Test Post 1"),
            tuple(2L, "Test Post 2"));
  }

}