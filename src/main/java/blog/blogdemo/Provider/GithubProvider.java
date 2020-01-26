package blog.blogdemo.Provider;

import blog.blogdemo.Dto.Access_tokenDto;
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
}
