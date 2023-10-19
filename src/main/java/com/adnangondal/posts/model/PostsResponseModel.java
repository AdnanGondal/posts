package com.adnangondal.posts.model;

import com.adnangondal.posts.entity.Post;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class PostsResponseModel {

  @Singular
  List<Post> posts;

}
