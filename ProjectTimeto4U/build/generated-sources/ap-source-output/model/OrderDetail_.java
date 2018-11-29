package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrderDetailPK;
import model.OrdersCustomer;
import model.Product;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T15:26:28")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Product> product;
    public static volatile SingularAttribute<OrderDetail, OrdersCustomer> ordersCustomer;
    public static volatile SingularAttribute<OrderDetail, Integer> quantityordered;
    public static volatile SingularAttribute<OrderDetail, OrderDetailPK> orderDetailPK;

}