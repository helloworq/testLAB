package Printer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.print.*;
import java.io.*;
import java.net.Socket;
/*
public class a {
    public static void a() {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File("C:\\Users\\12733\\Desktop\\aaa.pdf"));
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setJobName("快递面单打印");
            PrintService printService = PrintServiceLookup
                    .lookupDefaultPrintService();
            if (printService != null) {
                printJob.setPrintService(printService);
            } else {
                System.out.print("打印失败，未找到默认打印机，请检查。");
                return;
            }
            // }
            //设置纸张及缩放
            PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.SCALE_TO_FIT);
            //设置多页打印
            Book book = new Book();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向
            pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
            pageFormat.setPaper(getPaper());//设置纸张
            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
            printJob.setPageable(book);
            printJob.setCopies(1);//设置打印份数
            //添加打印属性
            HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
            //pars.add(Sides.DUPLEX); //设置单双页
            printJob.print(pars);
        } catch (PrinterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Paper getPaper() {
        Paper paper = new Paper();
        // 默认为A4纸张，对应像素宽和高分别为 595, 842
        int width = 284;
        int height = 567;
        // 设置边距，单位是像素，10mm边距，对应 28px
        int marginLeft = 5;
        int marginRight = 0;
        int marginTop = 5;
        int marginBottom = 0;
        paper.setSize(width, height);
        // 下面一行代码，解决了打印内容为空的问题
        paper.setImageableArea(marginLeft, marginRight, width - (marginLeft + marginRight), height - (marginTop + marginBottom));
        return paper;
    }


    public static String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

      public static void printPIC(String filePath) throws Exception {
        File file = new File(filePath); // 获取选择的文件
        Socket socket = new Socket("172.16.2.170", 9100);

        OutputStream out = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        //建立数组
        byte[] buf = new byte[1024];
        int len = 0;
        //判断是否读到文件末尾
        while ((len = fis.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        //告诉服务端，文件已传输完毕
        socket.shutdownOutput();
        socket.close();
        fis.close();
    }



}




    /**
     * 这个项目用来测试
     */
    /**
     * Arrays.copyOf方法接收一个数组a，和一个整形变量n，返回一个数组b
     * 大意是从接收a数组开始，复制n个元素到b，然后返回
     */
        /*
        int[] a={1,4,25,5,2,5,325,2352,343241,414,14,12241,312,314,532,1,13};
        int[] b = new int[0];
        System.out.println(Arrays.toString(Arrays.copyOf(a,3)));


        int size=111;//size必须为奇数
        int start_space=size/2;
        int start_star=1;
        for (int i = 0; i < size/2+1; i++) {
            for (int j = 0; j < start_space; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < start_star; j++) {
                System.out.print("*");
            }
            System.out.println();
            start_space--;
            start_star+=2;
        }
        start_space++;
        start_star-=2;
        for (int i = 0; i < size/2; i++) {
            start_space++;
            start_star-=2;
            for (int j = 0; j < start_space; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < start_star; j++) {
                System.out.print("*");
            }
            System.out.println();
        }



        int num=4;
        initTree(head,num);
        System.out.println("遍历测试结果如下");
        readTree(head);





        DataStructure.TreeNode head=new DataStructure.TreeNode();//头指针指向树结构
        int[] 文字部分替换data={1,32,1312,32,32,323,123,123,213,213,23,2132,13,213,123,213,12};
        DataStructure.TreeUntis.initTree(head,文字部分替换data);
        System.out.println("遍历测试结果如下");
        DataStructure.TreeUntis.readTree(head);


         */

    //a.a();
    //connectPrinter c=new connectPrinter("C:\\Users\\12733\\Desktop\\ttt.pdf","A3");
    //c.printPDF();
    //connectPrinter.JPGPrint(new File("C:\\Users\\12733\\Desktop\\aaa.doc"));
    //System.out.println(new RW_File().read_word("C:\\Users\\12733\\Desktop\\aaa.doc"));
    //System.out.println(connectPrinter.readWord("C:\\Users\\12733\\Desktop\\aaa.doc"));
    //connectPrinter.print2("C:\\Users\\12733\\Desktop\\ppp.jpg");


        /*
        String word = "C:\\Users\\12733\\Desktop\\服务器说明.doc";
        String name = "zhukaixin".concat(".pdf");
        String pdf = "C:\\Users\\12733\\Desktop\\" + name;
        connectPrinter.wToPdfChange(word, pdf);

         */

    //String[] name="dasdsadas.jpg".split("\\.");
    //System.out.println(name[0]);
    //connectPrinter c=new connectPrinter("",connectPrinter.A3,connectPrinter.horizontal);
    //System.out.println(connectPrinter.wToPdfChange());
    //a.printPIC("C:\\Users\\12733\\Desktop\\ppp.jpg");

        /*

        int times=countCharInString(test,'\n')+1;
        String replaceChar=test.replace("："," ").replace("　"," ").replace("\n"," ");

        System.out.println(replaceChar);
        String[] getLines=replaceChar.split(" ");
        for(int i=5;i<getLines.length;i=i+6){
            System.out.println(getLines[i]);
        }


        //testStream.test();
        //testStream.testworld();
        //connectPrinter.wToPdfChange("C:\\Users\\12733\\Desktop\\ttt.doc");
        String[] strArray=StringData.WORLD_PLACE_INFO.split("\n");
        ArrayList<String> AsE=new ArrayList<>();
        ArrayList<String> AsC=new ArrayList<>();
        for(String s:strArray){
            int ECapart=s.lastIndexOf(" ");//获取中英字符分割线
            AsE.add(s.substring(0,ECapart));
            AsC.add(s.substring(ECapart));
        }
        System.out.println(AsC.);

         */
        /*
        int start=309;
        int end;
        Scanner in=new Scanner(System.in);
        while(true){
            end=in.nextInt();
            System.out.println("从"+start+"开始"+"到"+end+"结束");
            excel.poiTestMethod(start,end);
            start=end+1;
        }
        //excel.poiTestMethod(174,183);

         */


        /*

      JFrame jf = new JFrame();
      jf.add(erstelleSpinnenDiagramm());
      jf.pack();
      jf.setVisible(true);
      */


    //radar();//雷达图
    //pie();
    //BAR();


/*

    String filePath = "C:\\Users\\12733\\Desktop\\第三屏打印模板(1)(1).xml";
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    File file = new File(filePath);
    Document document = builder.parse(file);
    //Element root = document.getDocumentElement();
    NodeList node = document.getElementsByTagName("w:t");
//wt  wr  wp  w:txbxContent  v:textbox  v:shape  w:pict
// mc:Fallback  mc:AlternateContent
        System.out.println(node.getLength());
                for (int i = 0; i < node.getLength(); i++) {
        //System.out.println(i+"   "+"${"+node.item(i).getTextContent()+"}");
        //注意修改完之后需要重新将数据写回到文件里
        //if (node.item(i).getTextContent().startsWith("成立时间")) {
        //    System.out.println("修改前       " + node.item(i).getTextContent());
        //    node.item(i).setTextContent("成立时间：6666年6月66号");
        //    System.out.println("修改后!      " + node.item(i).getTextContent());
        //}
        String data="text";
        //System.out.println(i+"   "+node.item(i).getTextContent());
        //map.put("${text}","modfy");
        System.out.println("map.put("+"\""+node.item(i).getTextContent()+"\",\""+data+"\");");
        }

*/

