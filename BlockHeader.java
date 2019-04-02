import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockHeader implements Serializable {

    final String prevBlockHeaderHash;
    final String timestamp;
    final String hashOfTransactions;

    public BlockHeader() {
        this.prevBlockHeaderHash = "";
        this.hashOfTransactions = "";
        this.timestamp = getTimestampString();
    }

    public BlockHeader(String prevBlockHeaderHash, String hashOfTransactions) {
        this.prevBlockHeaderHash = prevBlockHeaderHash;
        this.hashOfTransactions = hashOfTransactions;
        this.timestamp = getTimestampString();
    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("prevBlockHeaderHash: ")
                .append(prevBlockHeaderHash)
                .append("\ntimestamp: ")
                .append(timestamp)
                .append("\nhashOfTransactions: ")
                .append(hashOfTransactions);
        return out.toString();
    }

    private String getTimestampString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
