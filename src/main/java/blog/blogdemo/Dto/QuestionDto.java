package blog.blogdemo.Dto;

import blog.blogdemo.Model.User;
import lombok.Data;

@Data
public class QuestionDto {
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
    private User user;
}
