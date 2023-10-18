package com.adnangondal.posts.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPostRequest {
  private String content;
  private String imageUrl;
}
