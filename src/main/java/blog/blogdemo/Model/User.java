package blog.blogdemo.Model;


public class User {

  private long id;
  private String accountId;
  private String name;
  private String token;
  private long gmtCreated;
  private long gmtModified;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public long getGmtCreated() {
    return gmtCreated;
  }

  public void setGmtCreated(long gmtCreated) {
    this.gmtCreated = gmtCreated;
  }


  public long getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(long gmtModified) {
    this.gmtModified = gmtModified;
  }

}
