package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrdersCustomer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T18:20:48")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, String> cvv;
    public static volatile SingularAttribute<Payment, Integer> balance;
    public static volatile SingularAttribute<Payment, String> expireMonth;
    public static volatile SingularAttribute<Payment, OrdersCustomer> ordernumber;
    public static volatile SingularAttribute<Payment, String> cardnumber;
    public static volatile SingularAttribute<Payment, String> cardholder;
    public static volatile SingularAttribute<Payment, String> expireYear;

}