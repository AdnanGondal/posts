package com.adnangondal.posts.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adnangondal.posts.config.IntegrationTestConfig;
import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.model.NewPostRequest;
import com.adnangondal.posts.model.PostsResponseModel;
import com.adnangondal.posts.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = IntegrationTestConfig.class)
public class PostControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostRepository postRepository;

  @BeforeEach
  public void setUp(){
    postRepository.deleteAll();
  }

  @Test
  public void testCreatePost() {
    NewPostRequest post = NewPostRequest.builder().content("Hello World!").imageUrl("imageUrl").build();

    ResponseEntity<Long> response = restTemplate.postForEntity("/api/posts/user/{userId}", post, Long.class, 1L);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    Assertions.assertThat(postRepository.findAll()).hasSize(1);
  }


  @Test
  public void testGetAllPosts() {
    createMultiplePosts();

    ResponseEntity<PostsResponseModel> response = restTemplate.getForEntity("/api/posts", PostsResponseModel.class);
    List<Post> posts = Objects.requireNonNull(response.getBody()).getPosts();

    assertEquals(HttpStatus.OK, response.getStatusCode());

    Assertions.assertThat(posts)
        .hasSize(3)
        .extracting("content", "userId", "imageUrl")
        .containsExactly(
            tuple( "Hello World!", 1L , "url"),
            tuple("Hello World 2!", 1L , "url2"),
                tuple("Hello World 3!", 2L, "url3"));
  }

  @Test
  public void testGetPostsByUserId() {
    createMultiplePosts();

    ResponseEntity<PostsResponseModel> response = restTemplate.getForEntity("/api/posts/user/1", PostsResponseModel.class);
    List<Post> posts = Objects.requireNonNull(response.getBody()).getPosts();

    assertEquals(HttpStatus.OK, response.getStatusCode());

    Assertions.assertThat(posts)
            .hasSize(2)
            .extracting("content", "userId", "imageUrl")
            .containsExactly(
                    tuple( "Hello World!", 1L , "url"),
                    tuple("Hello World 2!", 1L , "url2"));
  }

  private void createMultiplePosts() {
    var postBuilder = Post.builder();
    Post post1 =
        postBuilder.content("Hello World!").userId(1L).imageUrl("url").createdDate(LocalDateTime.now()).build();
    Post post2 = postBuilder.content("Hello World 2!").userId(1L).imageUrl("url2").createdDate(LocalDateTime.now()).build();
    Post post3 = postBuilder.content("Hello World 3!").userId(2L).imageUrl("url3").createdDate(LocalDateTime.now()).build();
    postRepository.saveAll(List.of(post1, post2, post3));
  }
}
