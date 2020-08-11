package cucn.epoch.dubbo.tccapi;

import org.mengyun.tcctransaction.api.Compensable;

/**
 * @program: tcc
 * @description: SERVICE
 * @author: zxb
 * @create: 2020-08-11 14:24
 **/
public interface SetServceAPI {

    //扣减库存
    @Compensable
    void decrStock(String stockName);


    //生成订单号
    @Compensable
    String initOrderId(String stockName);

}
