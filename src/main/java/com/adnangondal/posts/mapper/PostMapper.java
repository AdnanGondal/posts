package com.adnangondal.posts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.model.NewPostRequest;

@Mapper(componentModel = "spring")
@Component
public interface PostMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", source = "userId")
  @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
  Post mapToEntity(NewPostRequest request, Long userId);

  @Mapping(target = "updatedDate", expression = "java(java.time.LocalDateTime.now())")
  void updatePostFromRequest(NewPostRequest postRequest, @MappingTarget Post entity);
}
