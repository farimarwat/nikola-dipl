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

package gov.nasa.jpf.jvm;

/**
 * a MementoRestorer that uses the default mementos
 */
public class DefaultMementoRestorer extends MementoRestorer {

  public Memento<KernelState> getMemento(KernelState ks) {
    return ks.getMemento();
  }

  public Memento<ThreadList> getMemento(ThreadList tlist) {
    return tlist.getMemento();
  }

  public Memento<ThreadInfo> getMemento(ThreadInfo ti) {
    return ti.getMemento();
  }

  public Memento<Heap> getMemento(DynamicArea da) {
    return da.getMemento();
  }

  public Memento<StaticArea> getMemento(StaticArea sa) {
    return sa.getMemento();
  }

  public Memento<ElementInfo> getMemento(DynamicElementInfo ei) {
    return ei.getMemento();
  }

  public Memento<ElementInfo> getMemento(StaticElementInfo ei) {
    return ei.getMemento();
  }

  public Memento<Heap> getMemento(SparseClusterArrayHeap sca){
    return sca.getMemento();
  }
}
