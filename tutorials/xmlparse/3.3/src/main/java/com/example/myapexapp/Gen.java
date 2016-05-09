package com.example.myapexapp;

import java.util.concurrent.ThreadLocalRandom;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * Operator that emits random Employee XML records
 */
public class Gen extends BaseOperator implements InputOperator
{
  // number of tuples to emit per window
  private int numTuples = 5;
  private transient int count = 0;

  public final transient DefaultOutputPort<String> output
    = new DefaultOutputPort<String>();

  private static final String[] names = {"Joe Sixpack", "Claude Clueless", "Adam Apple"};
  private static final String[] ids = {"10", "20", "30", "40"};
  private static final String[] dates = {"2010-02-15", "1990-04-10", "1980-11-11"};
  private static final String[] cities = {"Boston", "Detroit", "Santiago"};
  private static final String[] countries = {"Chile", "Mexico", "Canada"};

  private static final String template =
    "<Employee>" +
    "<name>%s</name>" +
    "<id>%s</id>" +
    "<date>%s</date>" +
    "<address><city>%s</city><country>%s</country></address>" +
    "</Employee>";


  @Override
  public void beginWindow(long windowId)
  {
    count = 0;
  }

  @Override
  public void emitTuples()
  {
    if (count < numTuples) {
      ++count;
      ThreadLocalRandom r = ThreadLocalRandom.current();
      String name    = names[    r.nextInt(0, names.length)];
      String id      = ids[      r.nextInt(0, ids.length)];
      String date    = dates[    r.nextInt(0, dates.length)];
      String city    = cities[   r.nextInt(0, cities.length)];
      String country = countries[r.nextInt(0, countries.length)];

      String tuple = String.format(template, name, id, date, city, country);
      output.emit(tuple);
    }
  }

  public int getNumTuples()
  {
    return numTuples;
  }

  /**
   * Sets the number of tuples to be emitted every window.
   * @param numTuples number of tuples
   */
  public void setNumTuples(int numTuples)
  {
    this.numTuples = numTuples;
  }
}
