import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Block implements Serializable {
    final Integer id;
    final BlockHeader header;
    final List<Transaction> transactions;

    public Block(Integer id) {
        this.id = id;
        this.transactions = Collections.emptyList();
        this.header = new BlockHeader();
    }

    public Block(Integer id, List<Transaction> transactions, String prevBlockHash) {
        this.id = id;
        this.transactions = transactions;
        this.header = new BlockHeader(prevBlockHash, Hashing.hash(transactions.toString()));
    }

    public String getHash() {
        return Hashing.hash(header.toString());
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nBlockId: ")
                .append(id)
                .append("\n")
                .append(header.toString())
                .append("\ntransactions:\n")
                .append(transactions.toString());
        return out.toString();
    }
}
