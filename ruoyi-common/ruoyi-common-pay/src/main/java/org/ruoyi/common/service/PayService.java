package org.ruoyi.common.service;

/**
 * 支付服务
 *
 * @author: wangle
 * @date: 2023/7/3
 */
public interface PayService {

    /**
     * 获取支付地址
     *
     * @Date 2023/7/3
     * @param orderNo
     * @param name
     * @param money
     * @param clientIp
     * @return String
     **/
    String getPayUrl(String orderNo, String name, double money, String clientIp);

}
