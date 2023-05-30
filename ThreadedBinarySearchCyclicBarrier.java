/*
Author: Jonathan Lee
Professor: Gita Faroughi
Sierra College/Sacramento State
Date: Nov 1 2022

This program will use two threads and peform a split binaray search basics of thread use
*/

import java.util.*;
import java.util.concurrent.CyclicBarrier; // use with the object instantiated as gate line 18
public class ThreadedBinarySearchCyclicBarrier
{
   public static void main (String args[]) throws Exception
   {
   
   
      CyclicBarrier gate = new CyclicBarrier(3);//must reach 3 gates before threads runs
      
      
      int[] num = {12, 15, 17, 19, 22, 26, 28, 30, 32, 34, 36, 38}; //array we are searching
      
      int num_first_half = num.length/2;//fist half of array
      
      /* !!Item we are finding in this example!! */
      int a = 38; //change me to any array number listed in line 21 binary search must be in order element that has 30 will show
      
      
      //First Thread Binary Search 1
      Thread thread1 = 
         new Thread()
         {
            public void run()
            {
               try
               {
                  gate.await();//CyclicBarrier 1 stop and wait for 3rd CyclicBarrier ref line 18
               }
               catch(Exception e)
               {
               }
               int first = 0;
               int last = num_first_half;
               if(a == num[first])
               {
                  System.out.println(first);
                  stop();
               }
               else if(a == num[last])
               {
                  System.out.println(last);//worst case
                  stop();
               }
               else if(a > num[last])
               {
                  stop();
               }
               while(first <= last)
               {
                  int middle = (first + last)/2;
                  if(a == num[middle])
                  {
                     System.out.println(middle);
                     stop();
                  }
                  else if (a > num[middle])
                  {
                     first = middle + 1;
                  }
                  else
                     last = middle -1;
               }
            }
         };
   
      //Second Thread Binary search 2
      Thread thread2 = 
         new Thread()
         {
            public void run()
            {
               try
               {
                  gate.await();//CyclicBarrier 2 stop and wait for 3rd CyclicBarrier to be reached ref line 18
               }
               catch(Exception e)
               {
               }
               int first = num_first_half;
               int last = num.length -1;
               if(a == num[first])
               {
                  System.out.println(first);
                  stop();
               }
               else if(a == num[last])
               {
                  System.out.println(last);//worst case
                  stop();
               }
               while(first <= last)
               {
                  int middle = (first + last)/2;
                  if(a == num[middle])
                  {
                     System.out.println(middle);
                     stop();;
                  }
                  else if (a > num[middle])
                  {
                     first = middle + 1;
                  }
                  else
                     last = middle - 1;   
               }
            }
         };
         
      thread1.start(); //first half, preps thread to run and hold CyclicBarrier 1  line 28-72 pauses at line 35                                     
      thread2.start(); //second half, preps thread to run and hold CyclicBarrier 2 line 75-115 pauses at line 82
      
      //print start time
      System.out.print("Start time in nanoseconds = ");
      System.out.println(System.nanoTime());
      
      /* When program realeased CyclicBarrier 3 reached */                                                    
      gate.await(); // last CyclicBarrier reacehed program now runs "concurrently" for all threads that are in holding pattern
      
      //print end time
      System.out.print("End time in nanoseconds = ");
      System.out.println(System.nanoTime());
   }
}


