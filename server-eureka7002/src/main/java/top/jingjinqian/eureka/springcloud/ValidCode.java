package top.jingjinqian.eureka.springcloud;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ValidCode {



    private ITesseract instance;

    /**
     * [url=home.php?mod=space&uid=952169]@Param[/url] languagePath 语言库地址
     * @param language 语言，语言库文件的开头
     */
    public ValidCode(String languagePath, String language) {
        instance = new Tesseract();
//设置训练库的位置
        instance.setDatapath(languagePath);
//chi_sim ：简体中文， eng 根据需求选择语言库
        instance.setLanguage(language);
        instance.setTessVariable("user_defined_dpi", "300");
    }

    public String ocr(String path) {
        File file = new File(path);
        System.out.println("path:"+path);
        String result = null;
        try {
            result = instance.doOCR(file);
        } catch (TesseractException e) {
            e.printStackTrace();//捕获异常不干活是会被打的！
        }
        System.out.println(result);
        return result.trim();
    }

    public static void main(String[] args) {
        // 识别图片的文件（修改为自己的图片路径）
        String imagePath = "\\D:\\my\\code\\java\\springcloudAlibaba\\validCode.jpg";

        if (imagePath.length()>0){
            imagePath = imagePath.substring(1);
        }
        System.out.println("imagePath:"+imagePath);
        File file = new File(imagePath);
        ITesseract instance = new Tesseract();
        //设置训练库的位置
        String path ="\\D:\\my\\code\\java\\springcloudAlibaba";
        if (path.length()>0){
            path = path.substring(1);
        }
        //打印一下路径，看有没有问题
        //System.out.println("tessdata:"+path);
        instance.setDatapath("D:\\app\\data\\code\\java\\tesst");
        //chi_sim ：简体中文， eng    根据需求选择语言库
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println("result: "+result);
    }
}
