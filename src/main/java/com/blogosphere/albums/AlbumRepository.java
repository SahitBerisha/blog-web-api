package com.blogosphere.albums;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AlbumRepository extends JpaRepository<Album, String> {

  @Query("""
      SELECT a 
      FROM Album a
      LEFT JOIN FETCH a.photos
      WHERE a.user.id = :id""")
  List<Album> findAllByUserId(String id);
}
