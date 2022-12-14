package com.blogosphere.albums.photos;

import com.blogosphere.albums.Album;
import com.blogosphere.core.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "photos", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Photo extends AbstractEntity {

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "thumbnail_url", nullable = false)
  private String thumbnailUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  private Album album;

}
