package com.example.myapexapp;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;

// in Malhar 3.3.X
import com.datatorrent.lib.parser.Parser;
import com.datatorrent.lib.parser.XmlParser;

// in Malhar 3.2.X release
//import com.datatorrent.contrib.schema.parser.Parser;
//import com.datatorrent.contrib.schema.parser.XmlParser;

import com.datatorrent.api.Context.PortContext;
import com.datatorrent.lib.io.ConsoleOutputOperator;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="MyFirstApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    Gen gen = dag.addOperator("generator", new Gen());

    // configure parser
    XmlParser parser = dag.addOperator("parser", new XmlParser());
    parser.setClazz(Employee.class);

    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("input", gen.output, parser.in).setLocality(Locality.CONTAINER_LOCAL);
    dag.addStream("data", parser.parsedOutput, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
