package com.blogosphere.categories;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryResponse {

  String id;
  String name;
  List<PostTitleResponse> posts;
}

record PostTitleResponse(String id, String title) {

}