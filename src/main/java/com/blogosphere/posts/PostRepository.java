package com.blogosphere.posts;

import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, String> {

  Page<Post> findAllByCategoryId(String id, Pageable pageable);

  Page<Post> findAll(Pageable pageable);

  @Query("""
      SELECT p
      FROM Post p
      WHERE p.user.id = :userId
      AND p.createdAt >= :from
      AND p.createdAt <= :to""")
  Page<Post> findAllByDateRange(@Param("userId") String userId,
                                @Param("from") Instant from,
                                @Param("to") Instant to,
                                Pageable pageable);

  @Query(""" 
      SELECT p
      FROM Post p
      WHERE p.user.id = :userId
      ORDER BY p.createdAt
      DESC""")
  List<Post> findLastThreePosts(@Param("userId") String userId,
                                Pageable pageable);
}
