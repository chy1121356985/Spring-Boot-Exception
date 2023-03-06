package com.renderg.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asset {
    public static String getFileContent1(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String job_id = "1118699";
        Integer user_id = 5885;
        Integer user_group = Math.round(user_id.intValue() / 500) * 500;
        String cpu_path="assets_201";

        String fileContent1 = getFileContent1("\\\\10.6.4.28\\"+cpu_path+"\\data_store\\assets\\" + user_group + "\\" + user_id + "\\cfg\\" + job_id + "\\info.cfg");
        JSONObject jsonObject = new JSONObject(fileContent1);
        JSONArray assets = jsonObject.getJSONArray("Assets");
        String filename = null;
        ArrayList<String> pathList = new ArrayList<>();
        for (int i = 0; i < assets.size(); i++) {
            if (i == 0) {
                String remoteName = new JSONObject(assets.get(i)).get("RemoteName").toString();
                System.out.println(remoteName);
                int i1 = remoteName.lastIndexOf(".");
                int i2 = remoteName.lastIndexOf(".", i1 - 1);
                filename = remoteName.substring(0, i2);


                String z = "\\\\10.6.4.28\\"+cpu_path+"\\data_store\\assets\\" + user_group + "\\" + user_id + "\\cfg\\" + job_id + "\\" + remoteName;
                pathList.add(z);
            } else if (i == 1) {
                Object remoteName = new JSONObject(assets.get(i)).get("RemoteName");
                String cfg = "\\\\10.6.4.28\\"+cpu_path+"\\data_store\\assets\\" + user_group + "\\" + user_id + "\\cfg\\" + remoteName;
                String ini = "\\\\10.6.4.28\\"+cpu_path+"\\data_store\\assets\\" + user_group + "\\" + user_id + "\\cfg\\" + job_id + "\\plugin.ini";
                pathList.add(cfg);
                pathList.add(ini);
            } else {
                String assetPath = new JSONObject(assets.get(i)).get("RemoteName").toString();

                String path = "\\\\10.6.4.28\\"+cpu_path+"\\data_store\\assets\\" + user_group + "\\" + user_id + "\\input\\" + assetPath;

                int index = path.indexOf(user_id + "\\input");
                String s1 = user_id + "";
                int i1 = index + s1.length() + 7;
                System.out.println(i1);
                String max = path.substring(i1);
                int max_index = max.indexOf("\\");
                String max_path = max.substring(0, max_index);
                System.out.println(max_path);
                System.out.println(max_path.contains("max_"));
                if (max_path.contains("max_")) {
                    String dish = max_path.substring(max_path.indexOf("_") + 1);
                    String Dish = dish.toUpperCase();
                    String s = path.replaceAll(max_path, Dish);
                    pathList.add(s);
                    System.out.println(path);
                } else {
                    pathList.add(path);
                }
            }
        }
        //new File("D:\\Max\\Max2014\\脚本拷过来的\\"+filename).mkdirs();
        for (String s : pathList) {
            File from_file = new File(s);
            new File("\\\\assets6.renderg.com\\assets_201\\data_stort");
            File to_file =new File("\\\\assets6.renderg.com\\assets_201\\data_store\\assets\\1500\\1706\\input\\G\\抓的场景\\"+filename);
            //File to_file = new File("D:\\Max\\Max2014\\脚本拷过来的\\" + filename);
            try {
                //判断目标是否为目录
                if (from_file.isDirectory()) {
                    asset.copy(from_file, to_file);
                } else {
                    //否则为文件
                    asset.copyFile(from_file, to_file);
                    System.out.println("复制成功" + from_file);
                }
            } catch (Exception Exception) {
                System.out.println("复制失败" + from_file);
                Exception.printStackTrace();
            }
        }
    }

    public static boolean copy(File src, File dstDir) {
        //文件不存在直接返回
        if (!src.exists()) {
            return false;
        }
        //文件价不存在 进行下一步操作
        if (!dstDir.exists()) {
            //新建文件夹
            dstDir.mkdirs();
        }
        //是否是文件
        if (src.isFile()) {
            //调用复制文件
            copyFile(src, dstDir);
        } else {
            //获取文件夹名
            String oldSrcName = src.getName();
            int srcNumber = 0;
            //拷贝地址+复制文件夹名 (一个新的路径)
            File newSrcDir = new File(dstDir, oldSrcName);
            //判断路径是否是文件
            while (newSrcDir.exists()) {
                srcNumber++;
                String newSrcName = oldSrcName + "-copy" + srcNumber;
                newSrcDir = new File(dstDir, newSrcName);
            }
            //新建复制路径文件夹
            newSrcDir.mkdirs();
            for (File srcSub : src.listFiles()) {
                // 递归复制源文件夹下子文件和文件夹
                copy(srcSub, newSrcDir);
            }
        }
        return true;
    }

    public static boolean copyFile(File srcFile, File dstDir) {
        //文件不存在或地址是文件夹  不处理
        if (!srcFile.exists() || srcFile.isDirectory()) {
            return false;
        }
        //粘贴地址不存在 创建文件夹
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        //获取文件名
        String oldFileName = srcFile.getName();
        //\w+匹配数字和字母下划线的多个字符
        String regular = "\\.\\w+";
        Pattern suffixPattern = Pattern.compile(regular);
        Matcher matcher = suffixPattern.matcher(oldFileName);

        String nameBody;
        String suffix;
        if (matcher.find()) {
            //找到起始索引的位置 截取文件名.前面的
            nameBody = oldFileName.substring(0, matcher.start());
            //截取文件名
            suffix = oldFileName.substring(matcher.start());
        } else {
            nameBody = oldFileName;
            suffix = "";
        }
        int fileNumber = 0;
        //创建文件路径
        File newFile = new File(dstDir, oldFileName);
        //判断文件是否已存在
        while (newFile.exists()) {
            fileNumber++;
            String newFileName = nameBody + "-copy" + fileNumber + suffix;
            newFile = new File(dstDir, newFileName);
        }
        try {
            //创建输入流
            FileChannel fileIn = new FileInputStream(srcFile).getChannel();
            //创建输出流
            FileChannel fileOut = new FileOutputStream(newFile).getChannel();
            long size = fileIn.size();
            for (long left = size; left > 0; ) {
                left = left - fileIn.transferTo(size - left, left, fileOut);
            }

            fileIn.close();
            fileOut.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
