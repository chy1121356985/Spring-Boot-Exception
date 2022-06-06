package auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
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

        String str = "C:\\Users\\11213\\AppData\\Roaming\\RGClient\\RGClientServer.exe.log";
        String str2 = "C:\\Users\\11213\\AppData\\Roaming\\RGClient\\output";
        File file = new File(str);
        String name = file.getName();
        Matcher matcher = Pattern.compile("\\.\\w+").matcher(name);
        String nameBody = null;
        String nameEnd = null;
        int fileNumber = 0;
        if (matcher.find()) {
            nameBody = name.substring(0, matcher.start());
            nameEnd = name.substring(matcher.start());
        }
        File outfile = new File(str2, name);
        while (outfile.exists()) {
            String newFile = nameBody + "-copy" + fileNumber + nameEnd;
            outfile = new File(str2, newFile);
        }


        try {
            FileChannel intputFile = new FileInputStream(file).getChannel();
            FileChannel outputFile = new FileOutputStream(outfile).getChannel();
            intputFile.transferTo(0, intputFile.size(), outputFile);
            intputFile.close();
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] split = str.split("\\.");

        for (String s : split) {
            System.out.println(s);
        }


    }
}
