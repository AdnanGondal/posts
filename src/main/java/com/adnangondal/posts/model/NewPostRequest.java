package com.adnangondal.posts.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPostRequest {

  @NotNull @Length(min = 1, message = "Content must be at least 2 characters long") private String content;

  @URL(message = "Must be a valid URL")
  private String imageUrl;
}
