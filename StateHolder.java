import java.io.Serializable;
import java.util.List;

public class StateHolder implements Serializable {
    final List<Block> blocks;
    final List<Transaction> unconfirmedTransactions;

    public StateHolder(List<Block> blocks, List<Transaction> unconfirmedTransactions) {
        this.blocks = List.copyOf(blocks);
        this.unconfirmedTransactions = List.copyOf(unconfirmedTransactions);
    }

    public List<Transaction> getUnconfirmedTransactions() {
        return unconfirmedTransactions;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
//        out.append("blocks: \n")
//                .append(blocks)
//                .append("\nUnconfirmed transactions:\n")
//                .append(unconfirmedTransactions);
        out.append("StateHolder with " + this.blocks.size() + " blocks, and " + this.unconfirmedTransactions.size() + " transactions");
        return out.toString();
    }
}
