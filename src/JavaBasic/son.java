package JavaBasic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class son extends father {
    public son(String fatherSaying){
        super(fatherSaying);
    }
    public void sonSay(){
        System.out.println(fatherSaying);
        System.out.println(fatherSay());
    }
    /*JSONObject stack=new JSONObject();
    stack.put("name",jsonArray.getJSONObject(i).getJSONObject("hcqfw").get("label").toString());
    stack.put("value",jsonArray.getJSONObject(i).getJSONObject("jcz").get("value").toString());
    JSONObject datas=new JSONObject();
    datas.put("name",jsonArray.getJSONObject(i).getJSONObject("r").get("label").toString());
    datas.put("stacks",stack);

    JSONObject res=new JSONObject();
    res.put("name",jsonArray.getJSONObject(i).getJSONObject("t").get("label").toString());
    res.put("datas",datas);

    jsonArrayres.add(res);*/
    /*            JSONObject jsonObjectAll=new JSONObject();
            jsonObjectAll.put("year",jsonArray.getJSONObject(i).getJSONObject("t").get("label").toString());
            jsonObjectAll.put("provinceName",jsonArray.getJSONObject(i).getJSONObject("r").get("label").toString());
            jsonObjectAll.put("hcqfwLable",jsonArray.getJSONObject(i).getJSONObject("hcqfw").get("label").toString());
            jsonObjectAll.put("jczValue",jsonArray.getJSONObject(i).getJSONObject("jcz").get("value").toString());
            jsonArrayres.add(jsonObjectAll);*/
    public static void main(String[] args) throws FileNotFoundException {
        String text=readFileContent("C:\\Users\\12733\\Desktop\\response_1599637349332.json");
        JSONObject jsonObject=JSONObject.parseObject(text);
        JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONObject("data").getJSONArray("data");
        JSONArray jsonArrayres=new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(JSON.toJSONString(jsonArrayres));
            JSONObject jsonObjectprev=jsonArray.getJSONObject(i);
            String year=jsonObjectprev.getJSONObject("t").getString("label");
            String prov=jsonObjectprev.getJSONObject("r").getString("label");
            String hcqfw=jsonObjectprev.getJSONObject("hcqfw").getString("label");
            String jcz=jsonObjectprev.getJSONObject("jcz").getString("value");
            if (null==getYearIndex(jsonArrayres,year) &&null==getProvIndex(jsonArrayres,year,prov))
            {
                JSONObject stackObj=new JSONObject();//加入stack成员
                stackObj.put("name",hcqfw);
                stackObj.put("value",jcz);
                JSONArray stack=new JSONArray();
                stack.add(stackObj);

                JSONObject datasObj=new JSONObject();//加入datas成员
                datasObj.put("name",prov);
                datasObj.put("stacks",stack);
                JSONArray datas=new JSONArray();
                datas.add(datasObj);

                JSONObject res=new JSONObject();//加入总成员
                res.put("name",year);
                res.put("datas",datas);
                jsonArrayres.add(res);
            }
            else if (null!=getYearIndex(jsonArrayres,year)
                    &&null==getProvIndex(jsonArrayres,year,prov))
            {
                JSONObject stackObj=new JSONObject();//加入stack成员
                stackObj.put("name",hcqfw);
                stackObj.put("value",jcz);
                JSONArray stack=new JSONArray();
                stack.add(stackObj);

                JSONObject datasObj=new JSONObject();//加入datas成员
                datasObj.put("name",prov);
                datasObj.put("stacks",stack);
                //JSONArray datas=new JSONArray();
                //datas.add(datasObj);

                Integer yearIndex=getYearIndex(jsonArrayres,year);
                JSONObject jsonObjectres=jsonArrayres.getJSONObject(yearIndex);
                jsonObjectres.getJSONArray("datas").add(datasObj);

            }
            else if (null!=getYearIndex(jsonArrayres,year)
                    &&null!=getProvIndex(jsonArrayres,year,prov))
            {
                JSONObject stackObj=new JSONObject();//加入stack成员
                stackObj.put("name",hcqfw);
                stackObj.put("value",jcz);
                JSONArray stack=new JSONArray();
                stack.add(stackObj);

                Integer yearIndex=getYearIndex(jsonArrayres,year);
                Integer provIndex=getProvIndex(jsonArrayres,year,prov);
                JSONObject jsonObjectlinshi=jsonArrayres.getJSONObject(yearIndex);
                JSONObject jsonObjectres=jsonObjectlinshi.getJSONArray("datas").getJSONObject(provIndex);

                jsonObjectres.getJSONArray("stacks").add(stack);
            }
        }
        System.out.println(JSON.toJSONString(jsonArrayres));
    }

    public static Integer getYearIndex(JSONArray jsonArray,String year){
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            String year1=jsonObject.getString("name");
            if (year1.equals(year))
                return i;
        }
        return null;
    }
    public static Integer getProvIndex(JSONArray jsonArray,String year,String prov){
        Integer yearIndex=getYearIndex(jsonArray,year);
        if (null==yearIndex){
            return null;
        }
        JSONArray newJsonArray=jsonArray.getJSONObject(yearIndex).getJSONArray("datas");
        for (int i = 0; i < newJsonArray.size(); i++) {
            JSONObject jsonObject=newJsonArray.getJSONObject(i);
            if (jsonObject.getString("name").equals(prov)){
                return i;
            }
        }
        return null;
    }


    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}

