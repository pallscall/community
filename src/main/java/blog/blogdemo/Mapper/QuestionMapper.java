package blog.blogdemo.Mapper;

import blog.blogdemo.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_created,gmt_modified,author,coment_count,view_count,like_count,tag) values(#{title},#{description},#{gmtCreated},#{gmtModified},#{author},#{comentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> query(@Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select("select count(1) from QUESTION")
    int Count();
}
