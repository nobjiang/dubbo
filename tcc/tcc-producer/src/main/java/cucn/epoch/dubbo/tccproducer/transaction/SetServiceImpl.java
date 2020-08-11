package cucn.epoch.dubbo.tccproducer.transaction;

import com.alibaba.dubbo.config.annotation.Service;
import cucn.epoch.dubbo.tccapi.SetServceAPI;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: tcc
 * @description: impl
 * @author: zhaol
 * @create: 2020-08-11 14:49
 **/
@Component
@Service(interfaceClass = SetServceAPI.class)
public class SetServiceImpl implements SetServceAPI {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Compensable(confirmMethod = "confirmstock", cancelMethod = "cancelstock", transactionContextEditor = DubboTransactionContextEditor.class)
    public void decrStock(String stockName) {
        System.out.println("开始扣减库存");
        stringRedisTemplate.opsForValue().increment(stockName, -1);
        System.out.println("当前库存："+stringRedisTemplate.opsForValue().get(stockName));
        throw new RuntimeException("TCC事务异常");
    }

    public void confirmstock(String stockName) {
        System.out.println("商品" + stockName + "成功扣减了一个库存");
    }

    public void cancelstock(String stockName) {
        System.out.println("商品" + stockName + "事务异常，回退库存");
        stringRedisTemplate.opsForValue().increment(stockName, 1);
        System.out.println("回退后当前库存："+stringRedisTemplate.opsForValue().get(stockName));
        System.out.println("商品" + stockName + "回退成功");

    }


    @Override
    @Compensable(confirmMethod = "confirmOrderID", cancelMethod = "cancelOrderID", transactionContextEditor = DubboTransactionContextEditor.class)
    public String initOrderId(String stockName) {
        System.out.println("开始生成订单号");
        String s = UUID.randomUUID().toString();
        return null;
    }

    public String confirmOrderID(String stockName) {
        System.out.println("商品" + stockName + "成功生成了一个订单号" );
        return null;
    }
    public String cancelOrderID(String stockName) {
        System.out.println("商品" + stockName + "事务异常，订单状态更新为取消" );
        return null;
    }

}