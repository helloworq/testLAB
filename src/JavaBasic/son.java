package JavaBasic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//定义一个实体
 class UserBean {
    private String id;
    private String birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
public class son extends father {
    public son(String fatherSaying){
        super(fatherSaying);
    }
    public void sonSay(){
        System.out.println(fatherSaying);
        System.out.println(fatherSay());
    }
    public static void main(String[] args) throws InterruptedException {
        /*String text1=readFileContent("C:\\Users\\12733\\Desktop\\处理json数据\\处理后的流域面积.json");
        String text2=readFileContent("C:\\Users\\12733\\Desktop\\处理json数据\\处理后的流域长度.json");
        String text3=readFileContent("C:\\Users\\12733\\Desktop\\处理json数据\\原始多年份多省份数据.json");
        List<JSONArray> list=new ArrayList<>();
        JSONArray jsonObject=JSONArray.parseArray(text1);
        JSONArray jsonObject1=JSONArray.parseArray(text2);
        list.add(jsonObject);
        list.add(jsonObject1);

        //System.out.println(handleJson(text1,"","成功！"));
        System.out.println(divideJsonArray(list));
        System.out.println(handleJson(text3,"","成功!"));*/
        System.out.println("with recursive r as (\n"+
                "       select * from v_solution_tree where parentcode = ?1 \n"+
                "     union all\n"+
                "       select T.* from v_solution_tree T, r where T.parentcode = r.code\n"+
                "     )\n"+
                "select * from r order by position asc;");
    }

    public static String listToString(List list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }


    public static List<String> stringToList(String str){
        String[] s=str.split(",");
        List<String> list=Arrays.asList(s);
        return list;
    }

    public static void deleteFileUnderFolder(String filePath){
        File file=new File(filePath);
        if (file.isFile())
            file=file.getParentFile();
        if (file.listFiles().length>0) {
            for (File fileTemp : file.listFiles()) {
                if (fileTemp.isDirectory()){
                    continue;
                }
                if (fileTemp.isFile())
                    fileTemp.delete();
            }
        }
    }


    /**
     * 将handleJson处理的结果在这里按照年份进行分组再交给mergeJson处理
     * @param list
     */
    static Object divideJsonArray(List<JSONArray> list){
        //获取各个年份的数量，低于两个的禁止合并处理
        String yearFunc=null;//定义局内year接收for循环内生成的局部值
        Map<String,Integer> map=new HashMap<>();
        //遍历list，获取所有年份出现的次数，不大于一次的不进行合并处理
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                String year=list.get(i).getJSONObject(j).getString("name");
                if (map.containsKey(year)){
                    int number=map.get(year);
                    map.put(year,number++);
                }
                else{
                    map.put(year,1);
                }
            }
        }
        //提取相同年份数据等待合并处理
        List<JSONObject> jsonArrayList=new ArrayList<>();
        JSONArray jsonArray=new JSONArray();
        JSONArray jsonArrayRes=new JSONArray();
        for (int k = 0; k < map.keySet().toArray().length; k++) {

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    String year=list.get(i).getJSONObject(j).getString("name");
                    yearFunc=year;
                    if (map.keySet().toArray()[k].equals(year)){
                        //jsonArray.add();
                        String jsonArrayPrev=JSON.toJSONString(list.get(i).getJSONObject(j));
                        jsonArrayList.add(JSON.parseObject(jsonArrayPrev));
                        jsonArray.clear();
                        continue;
                    }
                }
            }
            //合并处理
            if (jsonArrayList.size()>1){
                String jsonPrev=JSON.toJSONString(mergeJson(jsonArrayList));
                jsonArrayRes.add(JSON.parse(jsonPrev));//调用clear方法会把已加入list的内容也清掉，故使用临时值存储
            }
            else{
                String jsonPrev=JSON.toJSONString(jsonArrayList);
                jsonArrayRes.add(JSON.parse(jsonPrev));
            }
            jsonArrayList.clear();
        }
        return jsonArrayRes;
    }

    /**
     * 调用此方法处理数据之前必须确保list的size>=2
     * 数据可能是有多个年份
     * merge对象必须确保list的值大于2也就是只有有两个值及以上的情况才需要merge，传入的list是通过handleJson方法返回
     * 的jsonarray提取的一个jsonobject对象组成的一个list，这样将mergeJson变成一个工具类不参与业务逻辑
     * @param list
     *
     * @return
     */
    static JSONObject mergeJson(List<JSONObject> list){
        //采取拼接生成的方式
        JSONArray jsonArrayBasic= list.get(0).getJSONArray("datas");

        for (int i = 1; i < list.size(); i++) {
            JSONArray jsonArray= list.get(i).getJSONArray("datas");
            for (int j = 0; j < jsonArray.size(); j++) {
                String prov=jsonArray.getJSONObject(j).getString("name");
                Integer provIndex=getProvIndexWithoutYear(jsonArrayBasic,prov);
                if (null==provIndex){
                    jsonArrayBasic.add(jsonArray.getJSONObject(j));
                }
                else{
                    JSONObject jsonObjectStacks=jsonArray.getJSONObject(j).getJSONArray("stacks").getJSONObject(0);
                    jsonArrayBasic.getJSONObject(provIndex).getJSONArray("stacks").add(jsonObjectStacks);
                }
            }
        }

        //处理完成之后将数据拼接返回
        JSONObject jsonObjectRes=new JSONObject();
        jsonObjectRes.put("datas",jsonArrayBasic);
        jsonObjectRes.put("name",list.get(0).getString("name"));

        return jsonObjectRes;
    }


    public static Integer getProvIndexWithoutYear(JSONArray jsonArray, String prov) {
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.getJSONObject(i).getString("name").equals(prov))
                return i;
        }
        return null;
    }

    /**
     * @param jsonString 查询指标值返回结果 json字符串数据
     * 传入的数据是从指标值接口获得的原始数据，是单个的数据不是list，即使转成jsonobjectu也只是单个维度的数据
     * @param dynamic
     * 返回值是一个json数组，因为考虑到即使从指标值接口获得的数据也可能是有多个年份和多个省份的，根据年份分一个
     * jsonobject的话可能会有多个对象
     * @return
     */
    public static JSONArray handleJson(String jsonString, String dynamic ,String defaultDynamicValue) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("data").getJSONArray("data");
        JSONArray jsonArrayres = new JSONArray();
        String dynamicInnerValue="";
        if (dynamic!=""&&jsonArray.size()>0){
            dynamicInnerValue=jsonArray.getJSONObject(0).getJSONObject(dynamic).getString("label");
        }else {
            dynamicInnerValue=defaultDynamicValue;
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObjectprev = jsonArray.getJSONObject(i);
            String year = jsonObjectprev.getJSONObject("t").getString("label");
            String prov = jsonObjectprev.getJSONObject("r").getString("label");
            String jcz = jsonObjectprev.getJSONObject("jcz").getString("value");
            if (null == getYearIndex(jsonArrayres, year) && null == getProvIndex(jsonArrayres, year, prov)) {
                //加入stack成员
                JSONObject stackObj = new JSONObject();
                stackObj.put("name", dynamicInnerValue);
                stackObj.put("value", jcz);
                JSONArray stack = new JSONArray();
                stack.add(stackObj);

                //加入datas成员
                JSONObject datasObj = new JSONObject();
                datasObj.put("name", prov);
                datasObj.put("stacks", stack);
                JSONArray datas = new JSONArray();
                datas.add(datasObj);

                //加入总成员
                JSONObject res = new JSONObject();
                res.put("name", year);
                res.put("datas", datas);
                jsonArrayres.add(res);
            } else if (null != getYearIndex(jsonArrayres, year) && null == getProvIndex(jsonArrayres, year, prov)) {
                //加入stack成员
                JSONObject stackObj = new JSONObject();
                stackObj.put("name", dynamicInnerValue);
                stackObj.put("value", jcz);
                JSONArray stack = new JSONArray();
                stack.add(stackObj);

                //加入datas成员
                JSONObject datasObj = new JSONObject();
                datasObj.put("name", prov);
                datasObj.put("stacks", stack);

                Integer yearIndex = getYearIndex(jsonArrayres, year);
                JSONObject jsonObjectres = jsonArrayres.getJSONObject(yearIndex);
                jsonObjectres.getJSONArray("datas").add(datasObj);

            } else if (null != getYearIndex(jsonArrayres, year) && null != getProvIndex(jsonArrayres, year, prov)) {
                //加入stack成员
                JSONObject stackObj = new JSONObject();
                stackObj.put("name", dynamicInnerValue);
                stackObj.put("value", jcz);

                Integer yearIndex = getYearIndex(jsonArrayres, year);
                Integer provIndex = getProvIndex(jsonArrayres, year, prov);
                JSONObject jsonObjectlinshi = jsonArrayres.getJSONObject(yearIndex);
                JSONObject jsonObjectres = jsonObjectlinshi.getJSONArray("datas").getJSONObject(provIndex);

                jsonObjectres.getJSONArray("stacks").add(stackObj);
            }
        }
        return jsonArrayres;
    }

    public static Integer getYearIndex(JSONArray jsonArray, String year) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String year1 = jsonObject.getString("name");
            if (year1.equals(year)) {
                return i;
            }
        }
        return null;
    }

    public static Integer getProvIndex(JSONArray jsonArray, String year, String prov) {
        Integer yearIndex = getYearIndex(jsonArray, year);
        if (null == yearIndex) {
            return null;
        }
        JSONArray newJsonArray = jsonArray.getJSONObject(yearIndex).getJSONArray("datas");
        for (int i = 0; i < newJsonArray.size(); i++) {
            JSONObject jsonObject = newJsonArray.getJSONObject(i);
            if (jsonObject.getString("name").equals(prov)) {
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

