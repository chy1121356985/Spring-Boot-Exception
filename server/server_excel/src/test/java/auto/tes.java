package auto;

import com.renderg.pojo.DiskStr;
import com.renderg.pojo.DiskStrMB;

import java.util.*;

public class tes {


    public static void main(String[] args) {

        ArrayList<DiskStr> diskStrArrayList = new ArrayList<>();
        ArrayList<DiskStr> diskStrArrayLists = new ArrayList<>();
        DiskStr diskStr1 = new DiskStr("1", "62.031 MB");
        DiskStr diskStr2 = new DiskStr("2", "622.031 MB");
        DiskStr diskStr3 = new DiskStr("3", "91.632 GB");
        DiskStr diskStr4 = new DiskStr("4", "900.632 GB");
        DiskStr diskStr5 = new DiskStr("5", "0 Bytes");
        DiskStr diskStr6 = new DiskStr("6", "210.632 GB");
        DiskStr diskStr7 = new DiskStr("7", "80 MB");
        DiskStr diskStr8 = new DiskStr("8", "");
        DiskStr diskStr9 = new DiskStr("9", "500 Bytes");
        DiskStr diskStr10 = new DiskStr("10", "9 Bytes");
        DiskStr diskStr11 = new DiskStr("11", "60 Bytes");
        DiskStr diskStr12 = new DiskStr("12", "200 Bytes");
        DiskStr diskStr13 = new DiskStr("13", "459 Bytes");
        diskStrArrayList.add(diskStr1);
        diskStrArrayList.add(diskStr2);
        diskStrArrayList.add(diskStr3);
        diskStrArrayList.add(diskStr4);
        diskStrArrayList.add(diskStr5);
        diskStrArrayList.add(diskStr6);
        diskStrArrayList.add(diskStr7);
        diskStrArrayList.add(diskStr8);
        diskStrArrayList.add(diskStr9);
        diskStrArrayList.add(diskStr10);
        diskStrArrayList.add(diskStr11);
        diskStrArrayList.add(diskStr12);
        diskStrArrayList.add(diskStr13);


//        Collections.sort(diskStrArrayList, new Comparator<DiskStr>() {
//            @Override
//            public int compare(DiskStr o1, DiskStr o2) {
//                if (o1.getDiskStr().compareTo(o2.getDiskStr()) >= 1) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });

        List<DiskStr> diskStrBytes = new ArrayList<>();
        List<DiskStr> diskStrMB = new ArrayList<>();
        List<DiskStr> diskStrGB = new ArrayList<>();
        ArrayList<DiskStr> diskStrNull = new ArrayList<>();


        for (DiskStr diskStr : diskStrArrayList) {
            if (diskStr.getDiskStr().contains("Bytes")) {
                diskStrBytes.add(diskStr);
            } else if (diskStr.getDiskStr().length() == 0) {
                diskStrNull.add(diskStr);
            } else if (diskStr.getDiskStr().contains("MB")) {
                diskStrMB.add(diskStr);
            } else if (diskStr.getDiskStr().contains("GB")) {
                diskStrGB.add(diskStr);
            }
        }

        Collections.sort(diskStrBytes);
        Collections.sort(diskStrMB);
        Collections.sort(diskStrGB);


        for (DiskStr diskStr : diskStrBytes) {
            diskStrArrayLists.add(diskStr);
        }

        for (DiskStr diskStr : diskStrMB) {
            diskStrArrayLists.add(diskStr);
        }

        for (DiskStr diskStr : diskStrGB) {
            diskStrArrayLists.add(diskStr);
        }
        for (DiskStr diskStr : diskStrArrayLists) {
            System.out.println(diskStr);
        }



    }
}
