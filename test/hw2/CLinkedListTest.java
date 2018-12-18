/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

import java.util.Iterator;
import java.util.ListIterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Cole
 */
public class CLinkedListTest {

    private CLinkedList empty;
    private CLinkedList sizeTen;
    private CLinkedList sizeOneHundred;
    private CLinkedList sizeNine;
    private CLinkedList sizeNinetyNine;
    private CLinkedList sizeOne;
    int[] testDataTen = new int[10];
    int[] testDataOneHundred = new int[100];
    int[] testSetData1 = new int[10];
    int[] testSetData2 = new int[100];
    int singleElement = 1;
    static final int DIMTEN = 10;
    static final int DIMHUNDRED = 100;

    public CLinkedListTest() {
    }

    @Before
    public void setUp() {
        empty = new CLinkedList();
        sizeTen = new CLinkedList();
        sizeOneHundred = new CLinkedList();
        sizeOne = new CLinkedList();
        sizeOne.add(0, 3);

        for (int i = 0; i < DIMTEN; ++i) {
            testDataTen[i] = i;
            sizeTen.add(i, testDataTen[i]);
        }

        for (int i = 0; i < DIMHUNDRED; ++i) {
            testDataOneHundred[i] = i;
            sizeOneHundred.add(i, testDataOneHundred[i]);
        }

    }

    /**
     * Test of size method, of class CLinkedList.
     */
    @Test
    public void testSize() {

        System.out.println("Testing the size() method:");
        assertEquals("Check size --> sizeTen: ", new Integer(10), (Integer) sizeTen.size());
        assertEquals("Check size --> sizeOneHundred: ", new Integer(100), (Integer) sizeOneHundred.size());
        assertEquals("Check size --> empty: ", new Integer(0), (Integer) empty.size());

        sizeOneHundred.remove(0);
        sizeTen.remove(0);

        assertEquals("Check size --> sizeNine: ", new Integer(9), (Integer) sizeTen.size());
        assertEquals("Check size --> sizeNinetyNine: ", new Integer(99), (Integer) sizeOneHundred.size());
        assertEquals("Check size --> sizeOne: ", new Integer(1), (Integer) sizeOne.size());
    }

    /**
     * Test of get method, of class CLinkedList.
     */
    @Test
    public void testGet() {

        System.out.println("Testing the get() method");
        assertEquals("Check get: 0", new Integer(0), (Integer) sizeTen.get(0));
        assertEquals("Check get: 50", new Integer(50), (Integer) sizeOneHundred.get(50));
        assertEquals("Check get: null", null, empty.get(0));
        assertEquals("Check get: 10", new Integer(10), (Integer) sizeOneHundred.get(10));
        assertEquals("Check get: 20", new Integer(20), (Integer) sizeOneHundred.get(20));
        assertEquals("Check get: 4", new Integer(4), (Integer) sizeTen.get(4));
        assertEquals("Check get: 3", new Integer(3), (Integer) sizeTen.get(3));
        assertEquals("Check get: 8", new Integer(8), (Integer) sizeTen.get(8));
        assertEquals("Check get: 99", new Integer(99), (Integer) sizeOneHundred.get(99));
    }

    /**
     * Test of add method, of class CLinkedList.
     */
    @Test
    public void testAdd() {
        System.out.println("Testing the add() method");

        sizeTen.add(0, 11);
        sizeTen.add(5, 12);
        sizeTen.add(10, 13);
        sizeTen.add(3, 14);

        assertEquals("Check add: 11", new Integer(11), (Integer) sizeTen.get(0));
        assertEquals("Check add: 12", new Integer(3), (Integer) sizeTen.get(5));
        assertEquals("Check add: 13", new Integer(7), (Integer) sizeTen.get(10));
        assertEquals("Check add: 14", new Integer(14), (Integer) sizeTen.get(3));
        
        empty.add(0, 1);
        empty.add(0, 2);
        empty.add(0, 3);
        empty.add(0, 4);
        
        assertEquals("Check add: 1", new Integer(1), (Integer) sizeTen.get(0));
        assertEquals("Check add: 2", new Integer(2), (Integer) sizeTen.get(0));
        assertEquals("Check add: 3", new Integer(3), (Integer) sizeTen.get(0));
        assertEquals("Check add: 4", new Integer(4), (Integer) sizeTen.get(0));

    }

    /**
     * Test of set method, of class CLinkedList.
     */
    @Test
    public void testSet() {
        System.out.println("Testing the set() method");

        int[] setValues = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        int[] expectedReturns = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
        int setValuesIndex = 0;

        for (int i = 0; i <= 90; i += 10) {
            assertEquals("Checking old value: " + sizeOneHundred.get(i),
                    new Integer(i), sizeOneHundred.set(i, setValues[setValuesIndex]));
            ++setValuesIndex;
        }

        setValuesIndex = 0;

        for (int i = 0; i <= 90; i += 10) {
            assertEquals("Checking new value: " + sizeOneHundred.get(i),
                    new Integer(setValues[setValuesIndex]), sizeOneHundred.get(i));
            ++setValuesIndex;
        }

    }

    /**
     * Test of remove method, of class CLinkedList.
     */
    @Test
    public void testRemove() {
        System.out.println("Testing the remove() method");
        int[] removedData = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
        int index = 0;

        for (int i = 0; i <= 90; i += 10) {
            assertEquals("Checking removed data: " + i, removedData[index] + index, sizeOneHundred.remove(i));
            ++index;
        }
    }

    /**
     * Test of clear method, of class CLinkedList.
     */
    @Test
    public void testClear() {
        System.out.println("Testing the clear method");
        sizeTen.clear();
        sizeOneHundred.clear();

        assertEquals(0, sizeTen.size());
        assertEquals(0, sizeOneHundred.size());
        assertEquals(null, sizeTen.get(0));
        assertEquals(null, sizeOneHundred.get(0));

        sizeTen.add(0, 3);
        sizeTen.add(0, 10);
        sizeTen.add(0, 100);
        sizeOneHundred.add(0, 100);
        sizeOneHundred.add(0, 200);
        sizeOneHundred.add(0, 300);

        sizeTen.clear();
        sizeOneHundred.clear();

        assertEquals(0, sizeTen.size());
        assertEquals(0, sizeOneHundred.size());
        assertEquals(null, sizeTen.get(0));
        assertEquals(null, sizeOneHundred.get(0));

    }

    /**
     * Test of isEmpty method, of class CLinkedList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("Testing the isEmpty method");

        assertEquals(true, empty.isEmpty());
        assertEquals(false, sizeTen.isEmpty());
        assertEquals(false, sizeOneHundred.isEmpty());

        sizeTen.clear();
        sizeOneHundred.clear();

        assertEquals(true, sizeTen.isEmpty());
        assertEquals(true, sizeOneHundred.isEmpty());

    }

    /**
     * Test of QQQiterator method, of class CLinkedList.
     */
    @Test
    public void testQQQiterator() {
        System.out.println("QQQiterator");
        CLinkedList instance = new CLinkedList();
        Iterator expResult = null;
        Iterator result = instance.QQQiterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of QQQlistIterator method, of class CLinkedList.
     */
    @Test
    public void testQQQlistIterator() {
        System.out.println("QQQlistIterator");
        CLinkedList instance = new CLinkedList();
        ListIterator expResult = null;
        ListIterator result = instance.QQQlistIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
