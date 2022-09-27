package auto;


import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class test2 {

    /**
     * rar文件解压(不支持有密码的压缩包)
     *
     * @param destPath 解压保存路径
     */
    public static void unPackRar(File file, String destPath) throws IOException {
        int a = 0;
//        try (Archive archive = new Archive(new FileInputStream(rarFile))) {
//            if (null != archive) {
//                FileHeader fileHeader = archive.nextFileHeader();
//                //File file = null;
//                while (null != fileHeader) {
//                        a++;
//                    fileHeader = archive.nextFileHeader();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("解压失败");
//        }


//        SevenZFile sevenZFile = null;
//        try {
//            sevenZFile = new SevenZFile(file);
//            while (sevenZFile.getNextEntry() != null) {
//                a++;
//            }
//            a--;
//            System.out.println(a);
//        } catch (Exception e) {
//            System.out.println(e);
//        }


//        try (FileInputStream fileInputStream = new FileInputStream(file);
//             GZIPInputStream iStream = new GZIPInputStream(fileInputStream);
//             ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream("tar", iStream);
//             BufferedInputStream bis = new BufferedInputStream(in)) {
//            TarArchiveEntry entry;
//            while ((entry = (TarArchiveEntry) in.getNextEntry()) != null) {
//                a++;
//            }
//
//            System.out.println(a);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try (FileInputStream inputStream = new FileInputStream(file);
             TarArchiveInputStream iStream = new TarArchiveInputStream(inputStream);
             BufferedInputStream bis = new BufferedInputStream(iStream)) {
            TarArchiveEntry entry;
            while ((entry = (TarArchiveEntry) iStream.getNextEntry()) != null) {
                a++;
                System.out.println("1");
            }
            a--;
            System.out.println(a);
        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws Exception {
        unPackRar(new File("D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\11\\新建文件夹\\fumefx_4.1.0_vray_3.20.03_multiscatter_1.2.0.12\\新建文件夹\\新建文件夹 (2)\\新建文件夹.tar"),
                "D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\11\\新建文件夹\\fumefx_4.1.0_vray_3.20.03_multiscatter_1.2.0.12\\新建文件夹\\新建文件夹 (2)");


        String str = "abcd.dsds.sadfasd.sd.225kl.ss";
        int i = str.indexOf(".");
        int i1 = str.lastIndexOf(".");
        System.out.println(i);
        System.out.println(str.substring(i1+1));


        String s="D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\C\\新建文件夹\\c4d\\新建文件夹\\hole_mixer.jpg";
        File file = new File(s);
        System.out.println(file.getParentFile().mkdirs());






    }
}
