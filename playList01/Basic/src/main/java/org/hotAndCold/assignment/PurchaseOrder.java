package org.hotAndCold.assignment;


import lombok.Data;
import lombok.ToString;
import org.courseUtil.Util;

@Data
@ToString
public class PurchaseOrder {

    private  String  item ;
    private float  price ;
    private String category ;
    private int quantity ;

    public PurchaseOrder()
    {

        this.item = Util.faker().commerce().productName() ;
        this.price = Util.faker().random().nextInt(5,215) ;
        this.category = Util.faker().commerce().department() ;
        this.quantity = Util.faker().random().nextInt(1,50) ;
    }

}
