package blog.blogdemo.Controller;

import blog.blogdemo.Dto.PageDto;
import blog.blogdemo.Dto.QuestionDto;
import blog.blogdemo.Mapper.QuestionMapper;
import blog.blogdemo.Mapper.UserMapper;
import blog.blogdemo.Model.Question;
import blog.blogdemo.Model.User;
import blog.blogdemo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.ModalExclude;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "6") int page,
                        @RequestParam(name="size",defaultValue = "1") int size){
        Cookie[] cookies = request.getCookies();
        PageDto pagelist = questionService.list(page,size);
        model.addAttribute("qlist",pagelist);
        if(cookies == null) return "Index";
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    System.out.println(user.getAvatarUrl()+user.getName());
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }


        return "Index";
    }
}
