package frozen.utils;

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
