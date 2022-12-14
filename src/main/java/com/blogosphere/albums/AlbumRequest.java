package com.blogosphere.albums;

import javax.validation.constraints.NotBlank;

public record AlbumRequest(@NotBlank String title, @NotBlank String userId) {

}
