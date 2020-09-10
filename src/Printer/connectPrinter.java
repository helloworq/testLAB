package Printer;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.awt.print.*;
import java.io.*;
import java.net.Socket;
import java.util.stream.*;
/**
 *Date：2019-12-10
 * Author：zhoulei
 * tips:类里需要用到jcob.jar   pdfBox.jar
 *      其中的jcob还需要加一个jcob里的jacob-1.19-x64.dll文件
 *      放到jre或者jdk路径下的bin目录里
 */


public class connectPrinter {
    public final static int A3_WIDTH=595;
    public final static int A3_HEIGHT=842;
    public final static int A4_WIDTH=842;
    public final static int A4_HEIGHT=1191;
    private int width;
    private int height;
    private int direction;
    public String FilePath;
    public final static String IP="172.16.2.228";
    public static final String A3="A3";//打A3纸
    public static final String A4="A4";//打A4纸
    public static int vertical=0;//横向打印
    public static int horizontal=1;//纵向打印

    //不允许以默认构造器创建对象
    private connectPrinter(){}
    /**
     * Date：2019-12-10
     * Author：zhoulei
     * tips：只给出文件路径的情况下默认打A3格式，横向.
     *       后期可根据需求定制单张或多张打印
     * @param FilePath
     */
    public connectPrinter(String FilePath){
        this.FilePath=FilePath;
        this.height=A3_HEIGHT;
        this.width=A3_WIDTH;
        this.direction=0;
    }
    /**
     * Date：2019-12-10
     * Author：zhoulei
     * tips：给出文件路径和尺寸的情况下默认纵向打印
     * @param FilePath
     */
    public connectPrinter(String FilePath,String Size){
        this.FilePath=FilePath;
        if(Size.equals("A3")){
            this.height=A3_HEIGHT;
            this.width=A3_WIDTH;
        }
        else if(Size.equals("A4")){
            this.height=A4_HEIGHT;
            this.width=A4_WIDTH;
        }
        this.direction=0;
    }
    /**
     * Date：2019-12-10
     * Author：zhoulei
     * tips：参数完全定制
     * @param FilePath
     */
    public connectPrinter(String FilePath,String Size,int Page_Direction){
        this.FilePath=FilePath;
        if(Size.equals("A3")){
            this.height=A3_HEIGHT;
            this.width=A3_WIDTH;
        }
        else if(Size.equals("A4")){
            this.height=A4_HEIGHT;
            this.width=A4_WIDTH;
        }
        this.direction=Page_Direction;
    }


    /**
     * Date：2019-12-10
     * Author：zhoulei
     * 工作类，根据传入的文件的后缀进行判断调用哪一个工具
     * 其他工具类设置为private不允许单独调用
     * @throws Exception
     */
    public void print() throws Exception {
        if(FilePath==null) {
            System.out.println("请输入路径名!");
        }
        //判断传入的是word还是pdf。如果是pdf就直接打，是word就先转换成pdf再打
        else if(FilePath.endsWith("doc")||FilePath.endsWith("docx")){
            //FilePath重新赋值为转换后的pdf文件路径
            FilePath=wToPdfChange(FilePath);
            printPDF(FilePath);
        }
        else if(FilePath.endsWith(".jpg")){
            printPIC(FilePath);
        }
        else if(FilePath.endsWith(".pdf")){
            printPDF(FilePath);
        }
    }


    /* 打印指定的文件 */
    private void printPDF(String filepath) throws Exception {
        PDDocument document =null;
        //创建文件对象
        File file=new File(filepath);
        //查找文件
        document = PDDocument.load(file);
        //新建打印机任务
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setJobName("PDF文件打印");
        //查找本地打印机服务
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        if (printService != null) {
            printJob.setPrintService(printService);
        } else {
            System.out.print("打印失败，未找到默认打印机，请检查。");
            return;
        }
        //设置纸张及缩放
        PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.SCALE_TO_FIT);
        //设置多页打印
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();
        //设置打印方向
        pageFormat.setOrientation(direction);//纵向
        pageFormat.setPaper(getPaper());//设置纸张
        book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
        printJob.setPageable(book);
        printJob.setCopies(1);//设置打印份数
        //添加打印属性
        HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
        pars.add(Sides.DUPLEX); //设置单双页，duplex为双面打印
        printJob.print(pars);
    }


    /**
     * Date：2019-12-10
     * Author：zhoulei
     * 设置打印的纸张的数据
     * @return
     */
    private Paper getPaper() {
        //1191*842 A3
        //横向，纵向打印
        Paper paper = new Paper();
        // 默认为A4纸张，对应像素宽和高分别为 595, 842
        // 设置边距，单位是像素，10mm边距，对应 28px
        int marginLeft = 5;
        int marginRight = 0;
        int marginTop = 5;
        int marginBottom = 0;
        paper.setSize(width,height);
        // 下面一行代码，解决了打印内容为空的问题
        paper.setImageableArea(marginLeft, marginRight, width - (marginLeft + marginRight), height - (marginTop + marginBottom));
        return paper;
    }




    /**
     * Word转PDF工具，实现曲线打印Word文档的功能
     * 方法返回转换后生成的PDF文件路径
     * @param wordFile
     */
    public static String wToPdfChange(String wordFile){
        //wordFile word 的路径  //pdfFile pdf 的路径
        //PDF_File_Path的路径名为word文件的路径名+ofPDFtype+.pdf
        //例如word路径为d:\\aaa.doc    则pdf路径为aaaOfPdfType.pdf
        String pdfFile=
                (wordFile.split("\\."))[0]+"OfPdfType".concat(".pdf");
        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        // long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            System.out.println(app.getClass());
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            System.out.println("打开文件: " + wordFile);
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(pdfFile);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + pdfFile);
            Dispatch.call(document, "SaveAs", pdfFile, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
        }catch(Exception e) {
            System.out.println("转换失败"+e.getMessage());
        }finally {
            // 关闭office
            app.invoke("Quit", 0);
        }
        return pdfFile;
    }




    /**
     * 通过 IP+端口 连接打印机打印文件
     *
     * @param filePath
     * @throws Exception
     */
    private void printPIC(String filePath) throws Exception {
        File file = new File(filePath); // 获取选择的文件
        Socket socket = new Socket(IP, 9100);

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