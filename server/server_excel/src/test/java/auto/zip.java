package auto;

import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class zip {

    /**
     * 解压
     *
     * @param zipPath
     *            要解压的zip文件路径
     * @param targetPath
     *            存放解压后文件的目录
     * @param charset
     *            字符编码，解决中文名称乱码
     * @param propertyChangeListener
     *            进度通知
     * @throws Exception
     */

    public static void unzip(String zipPath, String targetPath, String charset, PropertyChangeListener propertyChangeListener) throws Exception {
        long totalSize = new File(zipPath).length();// 总大小
        long readSize = 0;
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipPath), Charset.forName(charset))) {
            SevenZFile sevenZFile = new SevenZFile(new File(""));
            for (ZipEntry zipItem = zipInput.getNextEntry(); zipItem != null; zipItem = zipInput.getNextEntry()) {
                if (!zipItem.isDirectory()) {
                    File file = new File(targetPath, zipItem.getName());
                    if (!file.exists()) {
                        new File(file.getParent()).mkdirs();// 创建此文件的上级目录
                    }
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        byte[] b = new byte[1024];
                        for (int len = zipInput.read(b); len > 0; len = zipInput.read(b)) {
                            out.write(b, 0, len);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println(readSize);
                    Integer oldValue = (int) ((readSize * 1.0 / totalSize) * 100);// 已解压的字节大小占总字节的大小的百分比
                    readSize += zipItem.getCompressedSize();// 累加字节长度
                    Integer newValue = (int) ((readSize * 1.0 / totalSize) * 100);// 已解压的字节大小占总字节的大小的百分比
                    if (propertyChangeListener != null) {// 通知调用者解压进度发生改变
                        propertyChangeListener.propertyChange(new PropertyChangeEvent(zipPath, "progress", oldValue, newValue));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        try {
//            unzip("C:\\Users\\tangzhichao\\Desktop\\65534英语国标音标TS28-60005", "C:\\Users\\tangzhichao\\Desktop\\test.zip", "GBK", new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent e) {
//                    System.out.println(">>>Source:" + e.getSource());
//                    System.out.println(">>>NewValue:" + e.getNewValue());
//                }
//            });
            String zip_path1 = "D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\新建文件夹.zip";
            String unzip_path1 = "D:\\mnt\\wlcb\\assets\\3000\\3118\\input\\新建文件夹\\11";
            unzip(zip_path1, unzip_path1, "GBK", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    System.out.println("<<<Source:" + e.getSource());
                    System.out.println("<<<NewValue:" + e.getNewValue());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
