package frozen.hail;

import frozen.utils.StormUtils;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;

// Deps to remove over time
import backtype.storm.testing.TestWordSpout;

public class HailTopology {

    public static class HailBolt implements IRichBolt {
        OutputCollector _collector;

        @Override
        public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
            _collector = collector;
        }

        @Override
        public void execute(Tuple tuple) {
            _collector.emit(tuple, new Values(7));
            _collector.ack(tuple);
        }

        @Override
        public void cleanup() {
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("severity"));
        }

    }

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout(1, new TestWordSpout(), 10);
        builder.setBolt(2, new HailBolt(), 3)
            .shuffleGrouping(1);
        builder.setBolt(3, new HailBolt(), 2)
            .shuffleGrouping(2);

        // Java-level code needs to change between submitting the
        // job locally and to a cluster?  Really, storm?  Really?
        StormUtils.runTopologyLocally(builder.createTopology());
    }
}
