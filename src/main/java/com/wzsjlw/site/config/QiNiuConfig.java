package com.wzsjlw.site.config;

import com.qiniu.common.Zone;
import lombok.Data;

import java.util.Properties;

/**
 * 七牛云配置
 *
 * @author: ll
 * @version: 1.0 2019-08-22
 * @see:
 * @since:
 */
@Data
public class QiNiuConfig {
    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 安全密钥
     */
    private String secretKey;

    /**
     * 存储空间名称
     */
    private String bucket;

    /**
     * 地区
     */
    private Zone zone;

    /**
     *
     */
    private String domainOfBucket;

    /**
     *
     */
    private long expireInSeconds;

    private static QiNiuConfig instance = new QiNiuConfig();

    private QiNiuConfig() {
        String zone0 = "zone0";
        String zone1 = "zone1";
        String zone2 = "zone2";
        String zoneNa0 = "zoneNa0";
        String zoneAs0 = "zoneAs0";

        Properties properties = new Properties();
        accessKey = properties.getProperty("qiniu.access-key");
        secretKey = properties.getProperty("qiniu.secret-key");
        bucket = properties.getProperty("qiniu.bucket");
        String zoneName = properties.getProperty("qiniu.zone");
        domainOfBucket = properties.getProperty("qiniu.domain-of-bucket");
        expireInSeconds = Long.parseLong(properties.getProperty("qiniu.expire-in-seconds"));

        try {
            if (zone0.equals(zoneName)) {
                zone = Zone.zone0();
            } else if (zone1.equals(zoneName)) {
                zone = Zone.zone1();
            } else if (zone2.equals(zoneName)) {
                zone = Zone.zone2();
            } else if (zoneNa0.equals(zoneName)) {
                zone = Zone.zoneNa0();
            } else if (zoneAs0.equals(zoneName)) {
                zone = Zone.zoneAs0();
            } else {
                throw new Exception("Zone对象匹配符错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QiNiuConfig getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(QiNiuConfig.getInstance().accessKey);
    }
}
