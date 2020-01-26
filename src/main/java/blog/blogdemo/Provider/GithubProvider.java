package blog.blogdemo.Provider;

import blog.blogdemo.Dto.Access_tokenDto;
import blog.blogdemo.Dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component //对象自动实例化
public class GithubProvider {
    public String getAccessToken(Access_tokenDto access_tokenDto){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(access_tokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+accessToken)
                .build();
        System.out.println(request);
        try{
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            GithubUser githubUser = JSON.parseObject(body, GithubUser.class); //将body转化为类对象
            System.out.println(body);
            System.out.println(githubUser.getName());

            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
