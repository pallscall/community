package blog.blogdemo.Model;

import lombok.Data;

@Data
public class User {

  private long id;
  private String accountId;
  private String name;
  private String token;
  private long gmtCreated;
  private long gmtModified;
  private String avatarUrl;


}
