package org.hotAndCold.assignment;

import org.courseUtil.Util;

public class Start {


    public static void main(String[] args) throws InterruptedException {

        OrderService orderService = new OrderService() ;
        RevenueService revenueService = new RevenueService() ;
        InventoryService inventoryService = new InventoryService() ;

        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream().subscribe(Util.subscriber("inventory"));
        revenueService.revenueStream().subscribe(Util.subscriber("revenue"));

        Thread.sleep(60000);
    }
}
