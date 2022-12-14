package com.blogosphere.albums.photos;

import javax.validation.constraints.NotBlank;

public record PhotoRequest(@NotBlank String title,
                           @NotBlank String url,
                           @NotBlank String thumbnailUrl) {

}
