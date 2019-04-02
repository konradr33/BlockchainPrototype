import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blockchain extends Thread {

    List<Block> blocks;
    List<Transaction> unconfirmedTransactions;
    CommunicationChannel channel;

    public Blockchain() {
        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(0));
        this.unconfirmedTransactions = new ArrayList<>();
        this.channel = new CommunicationChannel("blockchain", this);
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    Block block = new Block(blocks.size(), Collections.emptyList(), blocks.get(blocks.size() - 1).getHash());
                    blocks.add(block);
                }
                Thread.sleep(1000);
            }
            System.out.println(this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public StateHolder getStateHolder() {
        synchronized (this) {
            return new StateHolder(blocks, unconfirmedTransactions);
        }
    }

    public void setState(StateHolder state) {
        synchronized (this) {
            this.blocks.clear();
            this.blocks.addAll(state.blocks);
            this.unconfirmedTransactions.clear();
            this.unconfirmedTransactions.addAll(state.getUnconfirmedTransactions());
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Block block : blocks) {
            out.append(block.toString())
                    .append("\n--------\n");
        }
        return out.toString();
    }

    public void shutdown() {
        channel.disconnect();
    }
}
