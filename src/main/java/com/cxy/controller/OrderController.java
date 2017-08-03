package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.common.WarningEnum;
import com.cxy.entity.OrderFrom;
import com.cxy.entity.User;
import com.cxy.service.IorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
     * @param lid
     * @return
     */
    @RequestMapping(value = "/order/{lid}",method = RequestMethod.GET)
    public String createOrder(@PathVariable int lid, ModelMap modelMap, HttpServletRequest request){
        OrderFrom order=new OrderFrom();
        User user=UserTools.getCurrentUser(request);
        order.setSubscriberId(user.getId().intValue());
        order.setOrderStatus(0);
        List<OrderFrom> list;
        list=orderService.findOrderForListBySubScribeId(order);
        //如果已经预约，只直接展示已有订单
        if (list!=null && list.size()==1){
            modelMap.put("myOrder",list.get(0));
            return "myOrderInfo";
        }
        //如果有多个，说明系统漏洞，不可能有多个未完成订单
        if (list!=null && list.size()>1){
            modelMap.put("myOrder",list.get(0));
            modelMap.put("warningMsg", WarningEnum.unfinished_order.getMsg());
            return "error";
        }
        order.setSubscriberName(user.getUserName());
        order.setSubscriberMobile(user.getMobile());
        order=orderService.createOrder(lid,order);
        modelMap.put("myOrder",order);
        return "myOrderInfo";
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public String getOrders(HttpServletRequest request,ModelMap modelMap){
        User user=UserTools.getCurrentUser(request);
        modelMap.put("orders",orderService.findOrderForListByuserId(user.getId().intValue()));
        return "myOrderList";
    }
    /**确认
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.PUT)
    public String updateOrder(@PathVariable String orderId,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        User user=UserTools.getCurrentUser(request);
        int CurrentUserId=user.getId().intValue();
        OrderFrom orderFrom=orderService.findOrder(CurrentUserId);
        if (orderFrom.getPublisherId().equals(CurrentUserId)){
            orderFrom.setOrderStatus(1);
            //执行更新
            orderService.updateOrder(orderFrom);
            jsonObject.put("message",WarningEnum.update_success);
        }
        jsonObject.put("message",WarningEnum.no_privilege);
        return jsonObject.toJSONString();
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
