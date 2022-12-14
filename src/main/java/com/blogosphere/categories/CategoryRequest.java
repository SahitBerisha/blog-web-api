package com.blogosphere.categories;

import javax.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank String name) {

}
