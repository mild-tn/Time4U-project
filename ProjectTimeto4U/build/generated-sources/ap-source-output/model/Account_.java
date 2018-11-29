package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;
import model.Register;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T14:55:37")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T15:05:04")
>>>>>>> effcc2ddd5e69d477c291e9931f9818f2e78b6ec
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Integer> accountId;
    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, Register> registerId;
    public static volatile ListAttribute<Account, Customer> customerList;
    public static volatile SingularAttribute<Account, String> email;

}