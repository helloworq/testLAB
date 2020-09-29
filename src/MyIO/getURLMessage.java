package MyIO;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
@Deprecated
public class getURLMessage {
    public static void main(String[] args) {
        System.out.println(getMessage("https://music.migu.cn/v3/music/album/1135563295?origin=7"));
    }
    //获取目标链接的html数据
    public static String getMessage (String URL){
        String contents=null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(URL);
            httpGet.setHeader("User-Agent",DownLoad_My_Configs.httpGet_Header);
            HttpResponse response = httpClient.execute(httpGet);
            contents = EntityUtils.toString(response.getEntity(), "utf-8");//utf-8
        }catch (Exception e){
            e.printStackTrace();
        }
        return contents;
    }
}
