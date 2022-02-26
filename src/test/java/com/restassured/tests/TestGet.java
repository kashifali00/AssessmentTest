package com.restassured.tests;

import com.restassured.helpers.ServiceHelper;
import com.restassured.models.Datum;
import com.restassured.models.PojoEmployee;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestGet {

    ServiceHelper serviceHelper;
    PojoEmployee pojoEmployee;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void serviceHelper(){
        serviceHelper = new ServiceHelper();
        pojoEmployee = new PojoEmployee();

    }

    @Parameters({"id"})
    @Test
    public void testGetEmployees(int id){
        List<PojoEmployee> employees = serviceHelper.getAllEmployees(id);
        softAssert.assertNotNull(employees);
        System.out.println(employees.get(0).getData().get(16).getEmployeeName());

    }
}
