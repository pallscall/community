package blog.blogdemo.Service;

import blog.blogdemo.Dto.PageDto;
import blog.blogdemo.Dto.QuestionDto;
import blog.blogdemo.Mapper.QuestionMapper;
import blog.blogdemo.Mapper.UserMapper;
import blog.blogdemo.Model.Question;
import blog.blogdemo.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PageDto list(int page, int size){
        int totalQuestion = questionMapper.Count();
        int totalNum; //总分页数
        totalNum = (int) Math.ceil((double)totalQuestion/size);
        page = Math.max(page,1);
        page = Math.min(page,totalNum);
        int offset = size*(page-1);
        List<Question>questions=questionMapper.query(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PageDto pageDto = new PageDto();
        for(Question question:questions){
            User user = userMapper.findById(question.getAuthor());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);//数据复制
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestions(questionDtoList);
        pageDto.setPageInfo(totalQuestion,page,size);

        return pageDto;
    }

}
