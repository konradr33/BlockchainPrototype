import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.protocols.*;
import org.jgroups.protocols.pbcast.*;
import org.jgroups.stack.Protocol;
import org.jgroups.util.Util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommunicationChannel {

    JChannel channel;
    String cluster_name;

    public CommunicationChannel(String cluster_name, Blockchain blockchain) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.cluster_name = cluster_name;

        try {
            channel = new JChannel(getProtocolStack()).name(cluster_name);
            channel.setDiscardOwnMessages(true);
            channel.setReceiver(new CommunicationReceiver(blockchain));
            channel.connect(cluster_name);
            channel.getState(null, 0);
        } catch (Exception e) {
            System.out.println("Could not create blockchain's channel");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void send(CommunicationMessage msg) {
        try {
            Message message = new Message(null, Util.objectToByteBuffer(msg));
            channel.send(message);
        } catch (Exception e) {
            System.out.println("Error occurred while sending Message");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (channel.getView().getMembers().size() > 1)
            channel.disconnect();
        else
            channel.close();
    }

    private Protocol[] getProtocolStack() {
        Protocol[] protocolStack = new Protocol[0];
        try {
            protocolStack = new Protocol[]{
                    new UDP().setValue("mcast_group_addr", InetAddress.getByName("230.100.200.26")),
                    new PING(),
                    new MERGE3(),
                    new FD_SOCK(),
                    new FD_ALL(),
                    new VERIFY_SUSPECT(),
                    new BARRIER(),
                    new NAKACK2(),
                    new UNICAST3(),
                    new STABLE(),
                    new GMS(),
                    new UFC(),
                    new MFC(),
                    new FRAG2(),
                    new STATE(),
                    new SEQUENCER(),
                    new FLUSH()};
        } catch (UnknownHostException e) {
            System.out.println("Error occurred in getProtocolStack method");
            e.printStackTrace();
            System.exit(-1);
        }
        return protocolStack;
    }
}
