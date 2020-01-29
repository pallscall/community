package blog.blogdemo.Controller;

import blog.blogdemo.Mapper.QuestionMapper;
import blog.blogdemo.Mapper.UserMapper;
import blog.blogdemo.Model.Question;
import blog.blogdemo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request, Model model){
        /*回显*/
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description.equals("")){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag==null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        System.out.println(title);
        User user = null;
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        System.out.println(user);
        if(user == null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreated());
        question.setAuthor(user.getId());
        questionMapper.insert(question);

        return "redirect:/";
    }
}
