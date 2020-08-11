package cucn.epoch.dubbo.tccconsumer.transaction;

import cucn.epoch.dubbo.tccapi.SetServceAPI;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: tcc
 * @description: impl
 * @author: zhaol
 * @create: 2020-08-11 15:47
 **/
@Component
public class SetServiceConsumer {
    @Resource
    SetServceAPI setServceAPI;

    @Compensable(confirmMethod = "confirmBuy", cancelMethod = "cancelBuy", asyncConfirm = true)
    public void buy(String stockName) {
        System.out.println("用户成功支付了" + stockName + "商品,现在开始等待订单状态");
        //开始事务
        // 扣库存
        setServceAPI.decrStock(stockName);
        //生成订单号
        setServceAPI.initOrderId(stockName);
    }

    public void confirmBuy(String stockName) {
        System.out.println("用户购买" + stockName + "成功");
    }

    public void cancelBuy(String stockName) {
        System.out.println("用户购买" + stockName + "失败");
    }

}