package blog.blogdemo.Mapper;



import blog.blogdemo.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    @Insert("insert into USER(name,account_id,token,gmt_created,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreated},#{gmtModified})")
    void insert(User user);
}