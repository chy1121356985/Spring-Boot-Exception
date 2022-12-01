package auto;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.RuntimeUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Hutool  {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        double money=954256115223.22;
        System.out.println(Convert.digitToChinese(money));
        //月份
        System.out.println(Month.of(Calendar.NOVEMBER).getLastDay(false));
        Month.of(Calendar.JANUARY).getLastDay(false);
        System.out.println(DateUtil.date());
        System.out.println(DateUtil.date(Calendar.getInstance()));
        System.out.println(DateUtil.date(System.currentTimeMillis()));
        System.out.println(DateUtil.now());
        System.out.println(DateUtil.today());
        String dateStr = "2017-03-01";
        System.out.println(DateUtil.parse(dateStr,"yyyy-MM-dd"));
        System.out.println(timer.interval());
        System.out.println(timer.intervalRestart());
        System.out.println(12312312);
        System.out.println(timer.interval());
        String str = RuntimeUtil.execForStr("ping 10.12.6.15");
        System.out.println(str);

        String[] col= new String[]{"a","b","c","d","e"};
        List<String> colList = CollUtil.newArrayList(col);
        System.out.println(colList);







    }

/*
    // Retention注解决定MyAnnotation注解的生命周期
    @Retention(RetentionPolicy.RUNTIME)
// Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
    @Target({ ElementType.METHOD, ElementType.TYPE })
    public @interface AnnotationForTest {

        */
/**
         * 注解的默认属性值
         *
         * @return 属性值
         *//*

        String value();
    }
*/



}
