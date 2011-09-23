package frozen.utils;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;

public class StormUtils {

    public static void runTopologyLocally(StormTopology topology) {
        Config conf = new Config();
        conf.setDebug(true);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("test-frozen-hail", conf, topology);
        Utils.sleep(10000);
        cluster.killTopology("test-frozen-hail");
        cluster.shutdown();
    }

}
