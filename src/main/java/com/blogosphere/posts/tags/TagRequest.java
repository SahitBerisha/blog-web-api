package com.blogosphere.posts.tags;

import javax.validation.constraints.NotBlank;

public record TagRequest(@NotBlank String name) {

}
