//
// Copyright (C) 2010 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//

package gov.nasa.jpf.util;

import gov.nasa.jpf.util.test.TestJPF;

import org.junit.Test;

/**
 * unit test for IntTable
 */
public class IntTableTest extends TestJPF {

  @Test
  public void testBasic(){
    IntTable<String> tbl = new IntTable<String>();

    assert tbl.size() == 0;

    for (int i=0; i<300; i++){
      String key = "averylonginttablekey-" + i;
      tbl.add(key, i);

      IntTable.Entry<String> e = tbl.get(key);
      assert e.val == i;
    }

    assert tbl.size() == 300;
  }

  @Test
  public void testClone(){
    IntTable<String> tbl = new IntTable<String>(3); // make it small so that we rehash

    tbl.add("1", 1);
    tbl.add("2", 2);
    tbl.add("3", 3);

    IntTable<String> t1 = tbl.clone();

    for (int i=10; i<20; i++){
      t1.add(Integer.toString(i), i);
    }

    assert tbl.size() == 3;
    System.out.println("-- original table");
    for (IntTable.Entry<String> e : tbl){
      assert Integer.parseInt(e.key) == e.val;
      System.out.println(e);
    }

    assert t1.size() == 13;
    System.out.println("-- cloned+modified table");
    for (IntTable.Entry<String> e : t1){
      assert Integer.parseInt(e.key) == e.val;
      System.out.println(e);
    }
  }
}
