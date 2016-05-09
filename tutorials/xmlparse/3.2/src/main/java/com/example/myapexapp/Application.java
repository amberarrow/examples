package com.example.myapexapp;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;

// in Malhar 3.3.X
//import com.datatorrent.lib.parser.Parser;
//import com.datatorrent.lib.parser.XmlParser;

// in Malhar 3.2.X
import com.datatorrent.contrib.schema.parser.Parser;
import com.datatorrent.contrib.schema.parser.XmlParser;

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

    // properties can be set either in code as shown below or in XML properties files;
    // the latter overwrite the former when both are present
    //
    //parser.setAlias("Employee");
    //parser.setDateFormats("yyyy-MM-dd");

    dag.setOutputPortAttribute(parser.out, PortContext.TUPLE_CLASS, Employee.class);

    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("input", gen.output, parser.in).setLocality(Locality.CONTAINER_LOCAL);
    dag.addStream("data", parser.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
