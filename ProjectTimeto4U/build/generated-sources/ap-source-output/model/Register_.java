package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Account;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T14:55:37")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T15:05:04")
>>>>>>> effcc2ddd5e69d477c291e9931f9818f2e78b6ec
@StaticMetamodel(Register.class)
public class Register_ { 

    public static volatile SingularAttribute<Register, Date> activatedate;
    public static volatile SingularAttribute<Register, String> password;
    public static volatile SingularAttribute<Register, Date> registergdate;
    public static volatile SingularAttribute<Register, Integer> registerId;
    public static volatile ListAttribute<Register, Account> accountList;
    public static volatile SingularAttribute<Register, String> email;
    public static volatile SingularAttribute<Register, String> activatekey;

}