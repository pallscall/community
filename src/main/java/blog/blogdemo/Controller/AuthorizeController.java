package blog.blogdemo.Controller;

import blog.blogdemo.Dto.Access_tokenDto;
import blog.blogdemo.Dto.GithubUser;
import blog.blogdemo.Mapper.UserMapper;
import blog.blogdemo.Model.User;
import blog.blogdemo.Provider.GithubProvider;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}") private String clientId;
    @Value("${github.client.secret}") private String clientSecret;
    @Value("${github.redirect.uri}") private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Access_tokenDto access_tokenDto = new Access_tokenDto();
        access_tokenDto.setClient_id(clientId);
        access_tokenDto.setClient_secret(clientSecret);
        access_tokenDto.setCode(code);
        access_tokenDto.setRedirect_uri(redirectUri);
        access_tokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(access_tokenDto);
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreated(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreated());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            System.out.println("url"+ githubUser.getAvatarUrl());
            userMapper.insert(user);  //插入数据库
            request.getSession().setAttribute("user",githubUser); //写入session

            //写入Cookie
            response.addCookie(new Cookie("token",token));

            return "redirect:/"; //重定向
        }else{
            return "redirect:/";
        }
    }


}
