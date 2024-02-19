package com.adnangondal.posts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adnangondal.posts.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAllByUserId(Long userId);
}
