package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrderDetailPK;
import model.OrdersCustomer;
import model.Product;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T14:55:37")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T15:05:04")
>>>>>>> effcc2ddd5e69d477c291e9931f9818f2e78b6ec
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Product> product;
    public static volatile SingularAttribute<OrderDetail, OrdersCustomer> ordersCustomer;
    public static volatile SingularAttribute<OrderDetail, Integer> quantityordered;
    public static volatile SingularAttribute<OrderDetail, OrderDetailPK> orderDetailPK;

}