package com.adnangondal.posts.model;

import com.adnangondal.posts.entity.Post;
import java.util.List;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class PostsResponseModel {

  @Singular
  List<Post> posts;

}
