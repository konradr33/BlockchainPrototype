public class CommunicationMessage {

    final MessageType type;
    final Block block;
    final Transaction transaction;

    public CommunicationMessage(Block block) {
        this.type = MessageType.NEW_BLOCK;
        this.block = block;
        this.transaction = null;
    }

    public CommunicationMessage(Transaction transaction) {
        this.type = MessageType.ADD_NEW_TRANSACTION;
        this.block = null;
        this.transaction = transaction;
    }

    public enum MessageType {
        ADD_NEW_TRANSACTION,
        NEW_BLOCK;
    }
}
