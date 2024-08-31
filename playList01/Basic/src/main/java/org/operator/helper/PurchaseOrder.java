package org.operator.helper;


import lombok.Data;
import lombok.ToString;
import org.flux.courseutil.Util;

@Data
@ToString
public class PurchaseOrder {

    private String item ;
    private String price ;
    private int userId ;

    public PurchaseOrder(int userID)
    {
        this.item = Util.faker().commerce().productName() ;
        this.price=Util.faker().commerce().price() ;
        this.userId  = userID ;
    }
}
