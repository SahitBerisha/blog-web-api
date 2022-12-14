package com.blogosphere.posts.comments;

import javax.validation.constraints.NotBlank;

public record CommentRequest(@NotBlank String body,
                             @NotBlank String userId,
                             @NotBlank String postId) {

}
