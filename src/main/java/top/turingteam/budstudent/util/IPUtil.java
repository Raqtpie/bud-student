package top.turingteam.budstudent.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author Raqtpie
 */
@Slf4j
public class IPUtil {
    private static final String DB_PATH = "ip2region.xdb";

    private static final String LOCALHOST_IPV4 = "127.0.0.1";

    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private static final String UNKNOWN = "未知";

    private static final String UNKNOWN_EN = "unknown";

    private static final Integer REGION_ARRAY_FORMAL_LENGTH = 5;

    /**
     * 获取请求的ip地址
     * @param request 请求
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || UNKNOWN_EN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN_EN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN_EN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN_EN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN_EN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 根据ip地址获取城市
     * @param ip ip地址
     * @return 城市
     */
    public static String getCity(String ip) {
        if (LOCALHOST_IPV4.equals(ip) || LOCALHOST_IPV6.equals(ip)) {
            return "本地访问";
        }

        Searcher searcher;
        try {
            InputStream resourceAsStream = IPUtil.class.getClassLoader().getResourceAsStream(DB_PATH);
            if (resourceAsStream == null) {
                log.error("failed to load ip2region db file");
                return UNKNOWN;
            }
            searcher = Searcher.newWithBuffer(resourceAsStream.readAllBytes());
        } catch (IOException e) {
            log.info("failed to create searcher : ",e);
            return UNKNOWN;
        }

        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            log.info("region: {}, ioCount: {}, took: {} μs", region, searcher.getIOCount(), cost);
            String[] split = region.split("\\|");
            if (split.length == REGION_ARRAY_FORMAL_LENGTH) {
                return split[2] + split[3];
            } else {
                return UNKNOWN;
            }
        } catch (Exception e) {
            log.error("failed to search region", e);
            return UNKNOWN;
        } finally {
            try {
                searcher.close();
            } catch (IOException e) {
                log.error("failed to close searcher", e);
            }
        }
    }
}
