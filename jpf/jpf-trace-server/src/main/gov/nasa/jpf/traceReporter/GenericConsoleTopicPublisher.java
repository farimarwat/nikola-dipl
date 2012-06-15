//
// Copyright (C) 2010 Igor Andjelkovic (igor.andjelkovic@gmail.com).
// All Rights Reserved.
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
package gov.nasa.jpf.traceReporter;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.report.Reporter;
import gov.nasa.jpf.traceServer.printer.GenericConsoleTracePrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Redirects {@link gov.nasa.jpf.traceServer.printer.GenericConsoleTracePrinter
 * GenericConsoleTracePrinter}'s output to the Shell's trace report panel.
 * 
 * @author Igor Andjelkovic
 * 
 */
public class GenericConsoleTopicPublisher extends GenericConsoleTracePrinter
    implements TopicPublisher {

  private LinkedHashMap<String, Topic> topics;
  private StringWriter output;

  private String curTopic;

  public GenericConsoleTopicPublisher(Config config, Reporter reporter) {
    super(config, reporter);
    topics = new LinkedHashMap<String, Topic>();
  }

  protected void setTopics() {
    setTopics("consoleTracePrinter");
  }

  public Map<String, Topic> getResults() {
    return topics;
  }

  protected void openChannel() {
    output = new StringWriter();
    out = new PrintWriter(output);
  }

  protected void closeChannel() {
    try {
      out.close();
      output.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void publishTopicStart(String topic) {
    if (topic != null) {
      StringBuffer buff = output.getBuffer();
      if (buff.length() > 0) {
        topics.put("genericConsole " + curTopic, new Topic(buff.toString()));
        buff.setLength(0); // reset the output buffer
      }
    }
    curTopic = topic;
  }

  protected void publishTrace() {
    publishTopicStart("trace " + reporter.getLastErrorId());
    super.publishTrace();
  }

  public void publishEpilog() {
    publishTopicStart("");
  }

}