package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;
import model.OrderDetail;
import model.Payment;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T02:09:33")
@StaticMetamodel(OrdersCustomer.class)
public class OrdersCustomer_ { 

    public static volatile SingularAttribute<OrdersCustomer, Customer> customernumber;
    public static volatile ListAttribute<OrdersCustomer, OrderDetail> orderDetailList;
    public static volatile SingularAttribute<OrdersCustomer, Integer> totalprice;
    public static volatile SingularAttribute<OrdersCustomer, Integer> ordernumber;
    public static volatile SingularAttribute<OrdersCustomer, Date> shippeddate;
    public static volatile SingularAttribute<OrdersCustomer, Date> requireddate;
    public static volatile SingularAttribute<OrdersCustomer, String> status;
    public static volatile ListAttribute<OrdersCustomer, Payment> paymentList;

}