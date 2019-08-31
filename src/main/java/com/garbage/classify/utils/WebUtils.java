package com.garbage.classify.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    @Value("${rubbish.wx.ready.pay.url}")
    private String wxReadyPayUrl;

    /**
     * @Title: getIpAddress
     * @Description: 获取客户端真实IP地址
     * @author yihj
     * @param @param request
     * @param @param response
     * @param @return    参数
     * @return String    返回类型
     * @throws
     */
    public static String getIpAddress(HttpServletRequest request) {
        // 避免反向代理不能获取真实地址, 取X-Forwarded-For中第一个非unknown的有效IP字符串
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取项目域名
     * @return
     */
    public String getBasePath() {
        return wxReadyPayUrl;
    }
}
