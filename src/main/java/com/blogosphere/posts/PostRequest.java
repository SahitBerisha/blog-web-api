package com.blogosphere.posts;

import javax.validation.constraints.NotBlank;

public record PostRequest(@NotBlank String title, String body) {

}
