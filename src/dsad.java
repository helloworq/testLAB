import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: xinhuidong
 * @Date: 2019/11/30 0030 09:56


public class dsad{
    public static void test() throws Exception{
        WordUtils wordUtil = new WordUtils();
        Map<String, Object> params = new HashMap<>();
        params.put("${name}", "辛惠东");
        params.put("${sex}", "男");
        params.put("${national}", "汉族");
        params.put("${birthday}", "生日");
        try{
            String path="C:\\Users\\12733\\Desktop\\a.xml";  //模板文件位置
            String targetWordPath="C:\\Users\\Administrator\\Desktop\\out.docx";  //生成word位置
            wordUtil.getWord(path,params,targetWordPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void test2() throws Exception{
        // 模板文件名
        String templateFileName = "test.ftl";
        // 模板文件所在位置
        String templateFileDir = "/templates/";
        // 要生成的文件 全路径文件名
        String fileName="C:\\Users\\12733\\Desktop\\out.docx";  //生成word位置
        // 组装FreeMarker占位符对应的数据
        Map<String, Object> params = new HashMap<>();
        params.put("crowd_name", "单身贵族");
        params.put("nick_name", "辛惠东");
        params.put("sex", "男性");
        params.put("age", "50岁以上");
        params.put("tel", "15345970712");
        params.put("crowd_desc", "单身贵族66666");

    }


    public void test3() throws Exception{
        System.out.println("1111111111111");
        File file = new File("C:\\Users\\Administrator\\Desktop\\test1.pdf");
        //PrintPDF.printPDF(file);

    }
}
*/