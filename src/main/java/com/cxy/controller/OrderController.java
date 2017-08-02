package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.OrderFrom;
import com.cxy.entity.User;
import com.cxy.service.IorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/8/2.
 */
@Controller
@RequestMapping("/api")
public class OrderController {
    @Autowired
    IorderService orderService;
    /**生成
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/{lid}",method = RequestMethod.GET)
    public String createOrder(@PathVariable int lid, OrderFrom order, HttpServletRequest request){
        User user=UserTools.getCurrentUser(request);
        order.setSubscriberId(user.getId().intValue());
        order.setLineInfoId(lid);
         order=orderService.createOrder(order);
        return null;
    }
    /**取消
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.DELETE)
    public String cancelOrder(@PathVariable String orderId){
        return null;
    }
    /**获取单个
     * @param userId
     * @return
     */
    @RequestMapping(value = "/order/resource/{userId}",method = RequestMethod.GET)
    public OrderFrom getOrder(@PathVariable String userId){
        return null;
    }
    /**获取多个
     * @param userId
     * @return
     */
    @RequestMapping(value = "/orders/{userId}",method = RequestMethod.GET)
    public List<OrderFrom> getOrders(@PathVariable String userId){
        return null;
    }
    /**确认
     * @param userId
     * @return
     */
    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.PUT)
    public OrderFrom updateOrder(@PathVariable String userId){
        return null;
    }
    /**结束
     * @param userId
     * @return
     */
    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.PATCH)
    public OrderFrom finishOrder(@PathVariable String userId){
        return null;
    }
}
