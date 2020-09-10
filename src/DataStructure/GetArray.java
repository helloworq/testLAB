package DataStructure;

import MyIO.DownLoad_My_Configs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GetArray {
    String target="var_array type_array:=type_array('ggs','jjh','wsb','csl','dd','bb');";
    String v="INSERT INTO ars_area_adjacent(id, areaCode,areaName,adjacentAreaCode,adjacentAreaName) VALUES(SEQ_ARS_HIBERNATE.NEXTVAL, '330500000000', '湖州市', '330100000000', '杭州市');";
    public static void main(String[] args) throws IOException {
    }

    String toLongString(String FilePath) throws IOException {
        FileReader fileReader=new FileReader(FilePath);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String contentline;
        String head="type_array(";
        String tile=");";
        String res="";
        while ((contentline=bufferedReader.readLine())!=null){
            //System.out.println("'"+contentline+"',");
            //String prev="'"+contentline+"',"+"\n";
            //res+=prev;
            String prev= "'"+contentline.split("'")[0]+"''"+
                    contentline.split("'")[1]+"''"+
                    contentline.split("'")[2]+"''"+
                    contentline.split("'")[3]+"''"+
                    contentline.split("'")[4]+"''"+
                    contentline.split("'")[5]+"''"+
                    contentline.split("'")[6]+"''"+
                    contentline.split("'")[7]+"''"+
                    contentline.split("'")[8].replace(";","")+"',";
            res+=prev+"\n";
        }
        System.out.println(head+res+tile);
        //System.out.println(DownLoad_My_Configs.test);
        return res;
    }
}
