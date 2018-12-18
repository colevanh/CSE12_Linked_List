package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import static org.hamcrest.CoreMatchers.is;


/**
 *  Name: John Cole Van Hoogenstyn
 *  Account: cs12srd
 *  PID: A12572409
 *  UCSD email: jvanhoog@ucsd.edu
 *  Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 * */

/*
 * You should modify the information above to add your name 
 * 
 * Finally, when your tester is complete, you will rename it CLinkedListTester.java 
 * (renaming LinkedList to CLinkedList everywhere in the file) so that you can
 * use it to test your CLinkedList and MyListIterator classes.
 */
public class LinkedListTester
{
  private LinkedList<Integer> empty ;
  private LinkedList<Integer> one ;
  private LinkedList<Integer> several ;
  private LinkedList<String>  slist ;
  static final int DIM = 5;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new LinkedList<Integer>();
    one = new LinkedList<Integer>();
    one.add(0,new Integer(0));
    
    several = new LinkedList<Integer>() ;
    
    for (int i = DIM; i > 0; i--)
    {
      several.add(0,new Integer(i));
    }
    
    slist = new LinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
  }
  
  /** Test if heads of the lists are correct 
  * LinkedLists to check: empty, one, several, sList
  * Use assertEquals on all four
  */
  @Test
  public void testGetHead()
  {
    assertEquals("Check Head: one", new Integer(0), one.get(0)) ;
    assertEquals("Check Head: several", new Integer(1), several.get(0)) ;
    assertEquals("Check Head: slist", new String("First"), slist.get(0));
    assertEquals("Check Head: empty", null, empty.get(0));
    
  }
  
  /** Test if size of lists are correct 
  * LinkedLists to check: empty, one, several, sList
  * User assertEquals on all four
  */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size", 0, empty.size()) ;
    assertEquals("Check One Size", 1, one.size()) ;
    assertEquals("Check Several Size", DIM, several.size()) ;
    assertEquals("Check sList Size", 2, slist.size()) ; 
  }
  
  /** Test setting a specific entry 
  * LinkedLists to check: empty, one, several, sList
  * Use assertEquals on all four
  */
  
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value: sList", "Final", slist.get(1));
    
    one.set(0, 100);
    assertEquals("Setting specific value: one", (Integer)100, one.get(0));
    
    several.set(1, 100);
    assertEquals("Setting specific value: several", (Integer)100, several.get(1));
    
    empty.set(0, 100);
    assertEquals("Setting specific value: empty", (Integer)100, empty.get(0));
    
  }
  
  /** Test isEmpty 
  * LinkedLists to check: empty, one, several, sList
  * Use assertEquals on all four
  */
  
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
    assertTrue("sList is not empty", !slist.isEmpty()) ;
  }

  /** Test out of bounds exception on get 
  * LinkedLists to check: empty, one, several, sList
  * Use assertThat on all of them 
  */

  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      one.get(2);
      several.get(3);
      slist.get(2);
      
      fail("IndexOutOfBoundsException should be thrown");  
    }
    catch(IndexOutOfBoundsException e)
    {
    	assertThat(e.getMessage(), is("Index: 0, Size: 0")) ;
    	assertThat(e.getMessage(), is("Index: 2, Size: 2")) ;
    	assertThat(e.getMessage(), is("Index: 3, Size: 2")) ;
    	assertThat(e.getMessage(), is("Index: 2, Size: 2")) ; 
    }
  }

  
  /** Test iterator on empty list and several list 
  *   LinkedLists to check: empty, one, several, sList
  *   Use assertEquals
  */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
}
