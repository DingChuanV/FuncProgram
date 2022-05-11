package com.uin.lastday_stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 统计每天所有页面的UV（访问的用户数），PV（只要有访问就算一次
 *
 * @author wanglufei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog {
    /**
     * 用户唯一标识
     */
    private Long uid;
    /**
     * 页面唯一标识
     */
    private String pageID;
    /**
     * 访问日期
     */
    private LocalDate time;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Result {
    // UV 访问的用户数
    private Long uv;
    // PV 只要有访问就算一次
    private Long pv;
    // 页面唯一标识
    private String pageID;
    // 统计日期
    private LocalDate time;
}
