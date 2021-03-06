package com.majchrowski.piotr.inz;

import android.database.Cursor;
import android.test.AndroidTestCase;

import org.junit.Test;

public class TestSumIncomeOfMonth extends AndroidTestCase {
    DatabaseHelper myHelper;
    Double testValue1, testValue2, testValue3;
    String testDate1, testDate2, testDate3;
    Double value;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myHelper = new DatabaseHelper(mContext);
        myHelper.open();
        myHelper.clearDatabase();
        testValue1=101.0;
        testValue2=99.99;
        testValue3=1.1;
        testDate1 ="2018-11-22";
        testDate2 ="2018-11-01";
        testDate3 ="2018-11-30";
    }

    @Override
    public void tearDown() throws Exception {
        myHelper.close();
        super.tearDown();
    }
    @Test
    public void testSumIncomeOfMonth() {

        myHelper.addEntry("test1", testValue1, testDate1, 1, 1 );
        myHelper.addEntry("test2", testValue2, testDate2, 1, 2 );
        myHelper.addEntry("test3", testValue3, testDate3, 1, 3 );
        Cursor c = myHelper.getSumIncomeOfMonth(0);
        if (c.moveToFirst()) value = c.getDouble(0);

        assertEquals(testValue1+testValue2+testValue3, value);
    }
    @Test
    public void testSumOutcomeOfMonth() {

        myHelper.addEntry("test1", -testValue1, testDate1, 2, 1 );
        myHelper.addEntry("test2", -testValue2, testDate2, 2, 2 );
        myHelper.addEntry("test3", -testValue3, testDate3, 2, 3 );
        Cursor c = myHelper.getSumOutcomeOfMonth(0);
        if (c.moveToFirst()) value = c.getDouble(0);

        assertEquals( -testValue1-testValue2-testValue3, value);
    }
}
