/**
  * Tests for the scripts in the example/scripts directory
  */

package water.sparkling.scripts

import org.apache.spark.repl.h2o.{CodeResults, H2OInterpreter}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


/**
  * To run this test successfully we need to download the citibike-nyc data to
  * examples/bigdata/laptop/citibike-nyc directory
  */
@RunWith(classOf[JUnitRunner])
class ScriptStrata2015 extends ScriptsTestHelper {
  override protected def beforeAll(): Unit = {
    sparkConf = defaultConf.setMaster("local-cluster[3,2,2096]")
      .set("spark.driver.memory", "2G")
      .set("spark.executor.memory", "2G")
    super.beforeAll()
  }

  test("strata2015.script.scala") {
    val result = launchScript("strata2015.script.scala")
    var msg = "Problem during interpreting the script!"
    if (result.codeExecutionStatus == CodeResults.Exception) {
      msg = "Exception occurred during the execution. One possible cause could be missing necessary citibike-nyc data files in examples/bigdata/laptop/citibike-nyc/ folder."
    }
    assert(result.codeExecutionStatus == CodeResults.Success, msg)
  }
}
