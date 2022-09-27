package auto;

import com.renderg.pojo.DiskStr;

import java.util.ArrayList;
import java.util.List;

public class leng {
    public static void main(String[] args) {
        List<DiskStr> diskStrList = new ArrayList<>();
        DiskStr diskStr = new DiskStr();
        diskStr.setDiskStr("");
        diskStrList.add(diskStr);
        System.out.println(diskStrList.size());
        for (DiskStr str : diskStrList) {
            System.out.println(str.getId()+"00");
            System.out.println(str.getDiskStr()+"00");
        }


    }
}
