
import org.paboo.leaf.gen.Leaf;

import java.net.Socket;

public class client {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 9800);
        socket.getOutputStream().write(Leaf.leafReq.newBuilder().setServiceId(2).setNodeId(5).build().toByteArray());
        byte[] bytes = {};
        socket.getInputStream().read(bytes);
        System.out.println(Leaf.leafResp.parseFrom(bytes).getId());

    }
}