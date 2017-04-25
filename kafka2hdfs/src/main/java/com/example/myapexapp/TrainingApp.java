package com.example.myapexapp;

import org.apache.apex.malhar.kafka.AbstractKafkaInputOperator;
import org.apache.apex.malhar.kafka.KafkaSinglePortInputOperator;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.contrib.parser.CsvParser;


@ApplicationAnnotation(name="SampleApp")
public class TrainingApp implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // create operators and add them to DAG
    KafkaSinglePortInputOperator in = dag.addOperator("kafkaIn", new KafkaSinglePortInputOperator());
    CsvParser parser = dag.addOperator("csvParser", new CsvParser());
    LineOutputOperator out = dag.addOperator("fileOut", new LineOutputOperator());

    // configure operators
    in.setInitialOffset(AbstractKafkaInputOperator.InitialOffset.EARLIEST.name());

    // add streams
    dag.addStream("toParser", in.outputPort, parser.in);
    dag.addStream("toFile", parser.out, out.input);
  }
}
