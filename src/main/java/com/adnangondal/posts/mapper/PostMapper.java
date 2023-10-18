package com.adnangondal.posts.mapper;

import com.adnangondal.posts.entity.Post;
import com.adnangondal.posts.model.NewPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", source = "userId")
  Post mapToEntity(NewPostRequest request, Long userId);

}
