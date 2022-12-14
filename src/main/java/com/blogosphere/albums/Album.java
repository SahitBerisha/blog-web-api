package com.blogosphere.albums;

import com.blogosphere.albums.photos.Photo;
import com.blogosphere.albums.photos.exception.PhotoNotFoundException;
import com.blogosphere.core.AbstractEntity;
import com.blogosphere.users.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "albums")
public class Album extends AbstractEntity {

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "album", orphanRemoval = true)
  private List<Photo> photos = new ArrayList<>();

  @Transient
  public void addPhoto(Photo photo) {
    photo.setAlbum(this);
    this.photos.add(photo);
  }

  @Transient
  public Photo getPhotoById(String id) {
    return photos.stream()
        .filter(photo -> photo.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> PhotoNotFoundException.withId(id));
  }

  @Transient
  public void removePhoto(Photo photo) {
    this.photos.remove(photo);
  }
}
