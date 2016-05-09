Sample application demonstrating use of **XmlParser** operator in Malhar 3.2.

There are 3 operators:

<table>
  <tbody>
  <th>Name</th><th>Class</th><th>Description</th>
  <tr><td>gen</td><td>Gen</td><td>Generates random Employee records in XML format</td></tr>
  <tr><td>parser</td><td>XmlParser</td><td>Receives XML records on input port, converts them to Employee objects and writes them to output port as strings</td></tr>
  <tr><td>console</td><td>ConsoleOutputOperator</td><td>Writes input strings to console</td></tr>
  </tbody>
</table>

In addition to the operators, we have an `Employee` class for the input
records which is configured in `Application.java` as the value of the
`TUPLE_CLASS` attribute of the output port of the parser. All other properties
of the operators are configured in `META-INF/properties.xml`.

The application package can be built in the usual way with:
```
mvn clean package -DskipTests
```
which should create the package file `target/xmlparse-1.0-SNAPSHOT.apa`. This
file can be uploaded and launched on any Hadoop cluster.

The unit test in `ApplicationTest.testApplication()` runs the entire application
for five seconds in local mode and terminates (it does not require a cluster).
This test can be run directly in your IDE or on the command line using the maven
command:
```
mvn -Dtest=com.example.myapexapp.ApplicationTest#testApplication test
```

Please see `com.datatorrent.contrib.schema.parser.XmlParserTest` in Malhar
for additional unit tests for the **XmlParser** class.
