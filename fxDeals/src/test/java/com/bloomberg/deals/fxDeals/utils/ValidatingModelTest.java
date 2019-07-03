package com.bloomberg.deals.fxDeals.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatingModelTest {

    @Autowired
    private ValidatingModel validatingModel;

    @Test
   public void testValidatingDealAmount(){
       boolean result = validatingModel.validatingDealAmount("50");
       assertEquals(true,result);
   }



}