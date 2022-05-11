package com.uin.lastday_stream;

import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计每天所有页面的UV（访问的用户数），PV（只要有访问就算一次
 */
public class Main {
    public static void main(String[] args) {
        List<AccessLog> accessLogList = new ArrayList<>();
        accessLogList.add(new AccessLog(1L, "123", LocalDate.now()));
        accessLogList.add(new AccessLog(2L, "12", LocalDate.now()));
        accessLogList.add(new AccessLog(3L, "12", LocalDate.now()));
        accessLogList.add(new AccessLog(4L, "123", LocalDate.now()));

        List<Result> resultList = new ArrayList<>();

        // 首先将数据处理成按时间分组的数据
        Map<LocalDate, List<AccessLog>> dateListMap = accessLogList.stream()
                .collect(Collectors.groupingBy(AccessLog::getTime));

        // 遍历每一天数据
        dateListMap.forEach(((localDate, timeList) -> {
            //在按页面ID分组，这样就得到了每天每个页面的所有访问日志，接下来就是统计
            Map<String, List<AccessLog>> listMap = timeList.stream()
                    .collect(Collectors.groupingBy(AccessLog::getPageID));
            //在遍历页面分组信息
            listMap.forEach((pageId, pageTimeList) -> {
                Result result = new Result();
                result.setPageID(pageId);
                result.setTime(localDate);
                result.setPv(pageTimeList.stream()
                        .map(AccessLog::getUid).distinct().count());
                result.setUv((long) pageTimeList.size());
                resultList.add(result);
            });
        }));
        //输出结果
        resultList.forEach(System.out::println);
    }
}
