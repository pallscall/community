package blog.blogdemo.Controller;

import blog.blogdemo.Dto.Access_tokenDto;
import blog.blogdemo.Dto.GithubUser;
import blog.blogdemo.Provider.GithubProvider;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("$(github.client.id)") private String clientId;
    @Value("$(github.client.secret)") private String clientSecret;
    @Value("$(github.redirect.uri)") private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        Access_tokenDto access_tokenDto = new Access_tokenDto();
        access_tokenDto.setClient_id(clientId);
        access_tokenDto.setClient_secret(clientSecret);
        access_tokenDto.setCode(code);
        access_tokenDto.setRedirect_uri(redirectUri);
        access_tokenDto.setState(state);
        githubProvider.getAccessToken(access_tokenDto);
        return "Index";
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            GithubUser githubUser = JSON.parseObject(body, GithubUser.class); //将body转化为类对象
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
