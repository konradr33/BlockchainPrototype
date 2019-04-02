import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CommunicationReceiver extends ReceiverAdapter {

    Blockchain blockchain;

    public CommunicationReceiver(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void receive(Message msg) {
        super.receive(msg);
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        System.out.println("Get state");
        synchronized (blockchain) {
            StateHolder state = blockchain.getStateHolder();
            output.write(Util.objectToByteBuffer(state));
            System.out.println(state);
        }
    }

    @Override
    public void setState(InputStream input) throws Exception {
        System.out.println("Set state");
        synchronized (blockchain) {
            DataInputStream data = new DataInputStream(input);
            StateHolder state = Util.objectFromStream(data);
            System.out.println(state);
            blockchain.setState(state);
        }
    }
}
