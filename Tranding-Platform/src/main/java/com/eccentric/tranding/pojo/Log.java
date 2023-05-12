/*
 * author:eccentric
 * time:2023/5/12
 */
package com.eccentric.tranding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Log {
    //日志标识
    private Integer logId;
    //日志标题
    private String title;
    //访问的接口
    private String url;
    //操作的用户标识
    private Integer userId;
    //执行的时间
    private LocalDateTime executionTime;
}
