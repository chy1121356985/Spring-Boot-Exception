package auto;

import cn.hutool.core.util.NumberUtil;
import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class test {
    public static void main(String[] args) throws IOException {
//        String str = "C:\\Users\\11213\\Downloads\\RenderGClientInstall_2.8.68.exe";
//        String st = "D:\\Max";
//        String s = "/mnt/clusterName/assets/user_Group/user_Id/input";
//
//        File file = new File(str);
//        System.out.println(file.getName());
//
//        File file1 = new File(st);
//        System.out.println(file1.getName());
//        System.out.println(file.isFile());
//
//        File file3 = new File(s);
//
//        File file2 = new File(file3, file1.getName());
//        System.out.println(file2);
//        System.out.println(file2.exists());
//
//        File[] files = file1.listFiles();
//        for (File file4 : files) {
//            System.out.println(file4);
//        }
//        //举例：将字符串中的数字全部替换成！号
//        String string = "1d2sd4sfgg";
//        String s1 = string.replaceAll("\\d", "!");
//        System.out.println(s1);
//
//        //根据空格切割字符串
//        String s2 = "是 的 s";
//        for (String s3 : s2.split(" ")) {
//            System.out.println(s3);
//        }
//
//        String name = file.getName();
//        String regular = "\\.\\w+";
//
//        Pattern compile = Pattern.compile(regular);
//        Matcher matcher = compile.matcher(name);
//
//
//        if (matcher.find()) {
//            System.out.println(matcher.group());
//        }
//
//        Pattern p = Pattern.compile("(a*b)");
//        Matcher m = p.matcher("caaabccaab");
//        System.out.println("The input string is: caaabccaab");
//        System.out.println("The Regex is: (a*b)");
//        System.out.println();
//        while (m.find()) {
//            System.out.println("Index: " + m.start());
//
//        }

//        String str = "C:\\Users\\11213\\AppData\\Roaming\\RGClient\\RGClientServer.exe.log";
//        String str2 = "C:\\Users\\11213\\AppData\\Roaming\\RGClient\\output";
//        File file = new File(str);
//        String name = file.getName();
//        Matcher matcher = Pattern.compile("\\.\\w+").matcher(name);
//        String nameBody = null;
//        String nameEnd = null;
//        int fileNumber = 0;
//        if (matcher.find()) {
//            nameBody = name.substring(0, matcher.start());
//            nameEnd = name.substring(matcher.start());
//        }
//        File outfile = new File(str2, name);
//        while (outfile.exists()) {
//            String newFile = nameBody + "-copy" + fileNumber + nameEnd;
//            outfile = new File(str2, newFile);
//        }
//
//
//        try {
//            FileChannel intputFile = new FileInputStream(file).getChannel();
//            FileChannel outputFile = new FileOutputStream(outfile).getChannel();
//            intputFile.transferTo(0, intputFile.size(), outputFile);
//            intputFile.close();
//            outputFile.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String[] split = str.split("\\.");
//
//        for (String s : split) {
//            System.out.println(s);
//        }

//
//        String str = "D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\11\\新建文件夹";
//        File file = new File(str);
//        show(file);
//        System.out.println("文件数量"+test.fileNum);
//        System.out.println("文件夹数量"+test.directoryNum);
//    }
//    public static int fileNum = 0;
//    public static int directoryNum = 0;
//    public static void show(File file) {
//        File[] files = file.listFiles();
//        if (null != files) {
//            for (int i = 0; i < files.length; i++) {
//                String result = files[i].isFile() ? "一个文件" : "一个目录";
//                System.out.println(files[i] + "\t" + result);
//                if ("一个目录".equals(result)) {
//                    test.directoryNum++;
//                    show(files[i]);
//                } else {
//                    test.fileNum++;
//                }
//            }
//        }



        String str = "D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\11\\add_current.rar";
        File file = new File(str);
        test test = new test();
        List<FileMessage> rarList = test.getRarList(str);
        System.out.println(rarList.size());
//        System.out.println(file.getName());
//        String s = checkEncoding(file);
//        ZipFile zipFile = new ZipFile(str, Charset.forName(s));
//        System.out.println(zipFile.size());
//        Integer a =1000;

        // 参数一：除数;参数2：被除数;参数三:小数点后保留的位数，舍入模式模式为四舍五入，符合大多数计算场景
        System.out.println(NumberUtil.div("2", "20000", 2));
        int div3 = NumberUtil.div("1505", "1503",2).multiply(BigDecimal.valueOf(100)).intValue();
        System.out.println(div3+"%");  // 33%

    }

    //判断字符编码
    public static String checkEncoding(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[3];
        try {
            int i = in.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        if (b[0] == -1 && b[1] == -2) {
            return "UTF-16";
        } else if (b[0] == -2 && b[1] == -1) {
            return "Unicode";
        } else if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
            return "UTF-8";
        } else {
            return "GBK";
        }
    }


    public List<FileMessage> getRarList(String rarFileName) throws IOException {
        FileInputStream inputStream = null;
        Archive archive = null;
        try {
            inputStream = new FileInputStream(rarFileName);
            archive = new Archive(inputStream);
            FileHeader fileHeader = archive.nextFileHeader();
            List<FileMessage> list = new ArrayList<>();
            while (fileHeader != null) {
                if (fileHeader.isDirectory()) {
                    continue;
                }
                list.add(new FileMessage(fileHeader.getFileNameString(), fileHeader.getFullUnpackSize()));
                fileHeader = archive.nextFileHeader();
            }
            return list;
        } catch (Exception e) {
            System.out.println(" getRarFileList error:" + e.getMessage());
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (archive != null) {
                archive.close();
            }
        }
    }




}
