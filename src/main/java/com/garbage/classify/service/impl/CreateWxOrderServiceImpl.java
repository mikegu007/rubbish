package com.garbage.classify.service.impl;

import com.garbage.classify.constant.ErrConstant;
import com.garbage.classify.dao.TmOrderMapper;
import com.garbage.classify.model.dto.WechatAppletGolfPayInfo;
import com.garbage.classify.model.exception.ZyTechException;
import com.garbage.classify.model.po.TmOrder;
import com.garbage.classify.service.inf.CreateWxOrderService;
import com.garbage.classify.utils.MD5Util;
import com.garbage.classify.utils.ToolUtil;
import com.garbage.classify.utils.WebUtils;
import com.garbage.classify.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Service
@Slf4j
public class CreateWxOrderServiceImpl implements CreateWxOrderService {

    /**
     * 生成预支付接口地址
     */
    private static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    @Value("${rubbish.wx.apply.id}")
    private String APPLYID;

    @Value("${rubbish.wx.mch.id}")
    private String MCHID;

    @Value("${rubbish.wx.pay.key}")
    private String KEY;

    @Autowired
    private TmOrderMapper tmOrderMapper;

    @Override
    public String createUnifiedOrder(HttpServletRequest request, String amount, String openid, String orderNo) {
        log.info("微信 统一下单 接口调用 金额[{}] openId[{}] orderNo[{}]", amount, openid, orderNo);
        //接口调用总金额单位为分换算一下(测试金额改成1,单位为分则是0.01,根据自己业务场景判断是转换成float类型还是int类型)
//        String amountFen = "1";
        //创建hashmap(用户获得签名)
        SortedMap<String, String> paraMap = new TreeMap<String, String>();
        //设置body变量 (支付成功显示在微信支付 商品详情中)
        String body = "订单支付成功";
        //设置随机字符串
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        //设置请求参数(小程序ID)
        paraMap.put("appid", APPLYID);
        //设置请求参数(商户号)
        paraMap.put("mch_id", MCHID);
        //设置请求参数(随机字符串)
        paraMap.put("nonce_str", nonceStr);
        //设置请求参数(商品描述)
        paraMap.put("body", body);
        //设置请求参数(商户订单号)
        paraMap.put("out_trade_no", orderNo);
        //设置请求参数(总金额)
        paraMap.put("total_fee",amount);
        //设置请求参数(终端IP)
        paraMap.put("spbill_create_ip", WebUtils.getIpAddress(request));
        //设置请求参数(通知地址)
        paraMap.put("notify_url", new WebUtils().getBasePath() + "/wx/payCallback");
        //设置请求参数(交易类型)
        paraMap.put("trade_type", "JSAPI");
        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
        paraMap.put("openid", openid);
        try {
            //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            String stringA = formatUrlMap(paraMap, false, false);
            //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
            String sign = MD5Util.StringInMd5(stringA + "&key=" + KEY).toUpperCase();
//            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secret_key = new SecretKeySpec(KEY.getBytes(), "HmacSHA256");
//            sha256_HMAC.init(secret_key);
//            //  utf-8 : 解决中文加密不一致问题,必须指定编码格式
//            String signString = byteArrayToHexString(sha256_HMAC.doFinal(sign.getBytes("utf-8"))).toUpperCase();
            //将参数 编写XML格式
            StringBuffer paramBuffer = new StringBuffer();
            paramBuffer.append("<xml>");
            paramBuffer.append("<appid>" + APPLYID + "</appid>");
            paramBuffer.append("<mch_id>" + MCHID + "</mch_id>");
            paramBuffer.append("<nonce_str>" + paraMap.get("nonce_str") + "</nonce_str>");
            paramBuffer.append("<sign>" + sign + "</sign>");
            paramBuffer.append("<body>" + body + "</body>");
            paramBuffer.append("<out_trade_no>" + paraMap.get("out_trade_no") + "</out_trade_no>");
            paramBuffer.append("<total_fee>" + paraMap.get("total_fee") + "</total_fee>");
            paramBuffer.append("<spbill_create_ip>" + paraMap.get("spbill_create_ip") + "</spbill_create_ip>");
            paramBuffer.append("<notify_url>" + paraMap.get("notify_url") + "</notify_url>");
            paramBuffer.append("<trade_type>" + paraMap.get("trade_type") + "</trade_type>");
            paramBuffer.append("<openid>" + paraMap.get("openid") + "</openid>");
            paramBuffer.append("</xml>");

            //发送请求(POST)(获得数据包ID)(这有个注意的地方 如果不转码成ISO8859-1则会告诉你body不是UTF8编码 就算你改成UTF8编码也一样不好使 所以修改成ISO8859-1)
            Map<String, String> map = XmlUtils.doXMLParse(getRemotePortData(URL, new String(paramBuffer.toString().getBytes(), "ISO8859-1")));
            //应该创建 支付表数据
            if (map != null) {
                log.info("微信 统一下单 接口调用成功 并且新增支付信息成功");
                return map.get("prepay_id");
            }
        } catch (UnsupportedEncodingException e) {
            log.info("微信 统一下单 异常：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            log.info("微信 统一下单 异常：" + e.getMessage());
            e.printStackTrace();
        }
        log.info("微信 统一下单 失败");
        return null;
    }


    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    private static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    /**
     * 方法名: getRemotePortData
     * 描述: 发送远程请求 获得代码示例
     * 参数：  @param urls 访问路径
     * 参数：  @param param 访问参数-字符串拼接格式, 例：port_d=10002&port_g=10007&country_a=
     * 创建人: Xia ZhengWei
     * 创建时间: 2017年3月6日 下午3:20:32
     * 版本号: v1.0
     * 返回类型: String
     */
    private String getRemotePortData(String urls, String param) {
        log.info("港距查询抓取数据----开始抓取外网港距数据");
        try {
            URL url = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            conn.setConnectTimeout(30000);
            // 设置读取超时时间
            conn.setReadTimeout(30000);
            conn.setRequestMethod("POST");
            if (StringUtils.isNotBlank(param)) {
                conn.setRequestProperty("Origin", "https://sirius.searates.com");// 主要参数
                conn.setRequestProperty("Referer", "https://sirius.searates.com/cn/port?A=ChIJP1j2OhRahjURNsllbOuKc3Y&D=567&G=16959&shipment=1&container=20st&weight=1&product=0&request=&weightcargo=1&");
                conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");// 主要参数
            }
            // 需要输出
            conn.setDoInput(true);
            // 需要输入
            conn.setDoOutput(true);
            // 设置是否使用缓存
            conn.setUseCaches(false);
            // 设置请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            conn.setRequestProperty("Charset", "UTF-8");

            if (StringUtils.isNotBlank(param)) {
                // 建立输入流，向指向的URL传入参数
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(param);
                dos.flush();
                dos.close();
            }
            // 输出返回结果
            InputStream input = conn.getInputStream();
            int resLen = 0;
            byte[] res = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((resLen = input.read(res)) != -1) {
                sb.append(new String(res, 0, resLen));
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.info("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
        }
        log.info("港距查询抓取数据----抓取外网港距数据失败, 返回空字符串");
        return "";
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

}
