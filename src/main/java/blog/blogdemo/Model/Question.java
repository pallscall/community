package blog.blogdemo.Model;


import lombok.Data;

@Data
public class Question {

  private long id;
  private String title;
  private String description;
  private long gmtCreated;
  private long gmtModified;
  private long author;
  private long comentCount;
  private long viewCount;
  private long likeCount;
  private String tag;

}
