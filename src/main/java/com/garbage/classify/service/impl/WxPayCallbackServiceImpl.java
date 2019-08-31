package com.garbage.classify.service.impl;

import com.garbage.classify.model.dto.WechatAppletGolfPayInfo;
import com.garbage.classify.service.inf.WxPayCallbackService;
import com.garbage.classify.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Service
@Slf4j
public class WxPayCallbackServiceImpl implements WxPayCallbackService {

    @Override
    public void payCallback(HttpServletRequest request, HttpServletResponse response) {
        log.info("微信回调接口方法 start");
        log.info("微信回调接口 操作逻辑 start");
        String inputLine = "";
        String notityXml = "";
        try {
            while((inputLine = request.getReader().readLine()) != null){
                notityXml += inputLine;
            }
            //关闭流
            request.getReader().close();
            log.info("微信回调内容信息："+notityXml);
            //解析成Map
            Map<String,String> map = XmlUtils.doXMLParse(notityXml);
            //判断 支付是否成功
            if("SUCCESS".equals(map.get("result_code"))){
                log.info("微信回调返回是否支付成功：是");
                //获得 返回的商户订单号
                String outTradeNo = map.get("out_trade_no");
                log.info("微信回调返回商户订单号："+outTradeNo);
                //访问DB
                WechatAppletGolfPayInfo payInfo = appletGolfPayInfoMapper.selectByPrimaryKey(outTradeNo);
                log.info("微信回调 根据订单号查询订单状态："+payInfo.getPayStatus());
                if("0".equals(payInfo.getPayStatus())){
                    //修改支付状态
                    payInfo.setPayStatus("1");
                    //更新Bean
                    int sqlRow = appletGolfPayInfoMapper.updateByPrimaryKey(payInfo);
                    //判断 是否更新成功
                    if(sqlRow == 1){
                        log.info("微信回调  订单号："+outTradeNo +",修改状态成功");
                        //封装 返回值
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("<xml>");
                        buffer.append("<return_code>SUCCESS</return_code>");
                        buffer.append("<return_msg>OK</return_msg>");
                        buffer.append("</xml>");

                        //给微信服务器返回 成功标示 否则会一直询问 咱们服务器 是否回调成功
                        PrintWriter writer = response.getWriter();
                        //返回
                        writer.print(buffer.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
