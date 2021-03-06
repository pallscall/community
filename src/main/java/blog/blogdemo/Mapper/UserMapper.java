package blog.blogdemo.Mapper;



import blog.blogdemo.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{
    @Insert("insert into USER(name,account_id,token,gmt_created,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreated},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from USER where id=#{author}")
    User findById(long author);
}