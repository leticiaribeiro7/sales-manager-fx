package tests;

import org.junit.platform.suite.api.*;


@Suite
@SelectClasses({
        UserDAOTest.class,
        OrderDAOTest.class,
        SupplierDAOTest.class,
        SaleDAOTest.class,
        ProductDAOTest.class,
        ClientDAOTest.class
})

public class AllTests {}
