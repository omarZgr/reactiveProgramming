package org.operator;

import org.courseUtil.Util;
import org.operator.helper.OrderService;
import org.operator.helper.UserService;

public class Lec01FlatMap {

    public static void main(String[] args) {

        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.subscriber());

    }
}
