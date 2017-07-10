package com.teamnet.examples;

import junit.framework.TestCase;
import junit.framework.Test;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Assert;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alexandra.Gaman on 7/10/2017.
 */
public class MyUnitTest extends TestCase {

    @org.junit.Test
    public void testConcatenate(){
        MyUnit unit = new MyUnit();
        String result = unit.concatenate("one","two");
        assertEquals("onetwo", result);
    }

    @org.junit.Test
    public void testConcatNulls(){
        MyUnit unit = new MyUnit();
        String result = unit.concatenate(null,null);
        assertEquals(result, null);

        result = unit.concatenate(null,"sadsad");
        assertNotNull(result);

        result = unit.concatenate("one",null);
        assertNotNull(result);
    }

    @org.junit.Test
    public void testtestGetBoolean(){
        MyUnit unit = new MyUnit();
        assertNotNull(unit.getBoolean());
        assertTrue((Boolean)unit.getBoolean() instanceof Boolean);
        MatcherAssert.assertThat(123, CoreMatchers.is(123));
        MatcherAssert.assertThat("a", CoreMatchers.not(CoreMatchers.is("b")));
    }


}
