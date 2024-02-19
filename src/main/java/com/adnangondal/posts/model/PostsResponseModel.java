package com.adnangondal.posts.model;

import java.util.List;

import com.adnangondal.posts.entity.Post;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsResponseModel {

  @Singular List<Post> posts;
}
