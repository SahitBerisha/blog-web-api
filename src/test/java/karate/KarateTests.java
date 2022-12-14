package karate;

import com.blogosphere.BlogosphereApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT,
    classes = {BlogosphereApplication.class})
public class KarateTests {

  @Karate.Test
  Karate createAlbumTest() {
    return Karate.run("classpath:karate/albums/post.feature");
  }

  @Karate.Test
  Karate readAlbumTest() {
    return Karate.run("classpath:karate/albums/get.feature");
  }

  @Karate.Test
  Karate createPostTest() {
    return Karate.run("classpath:karate/posts/post.feature");
  }

  @Karate.Test
  Karate readPostTest() {
    return Karate.run("classpath:karate/posts/get.feature");
  }
}
