
import org.paboo.leaf.gen.Leaf;

import java.net.Socket;

public class client {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 9800);
        byte[] req =  Leaf.leafReq.newBuilder().setServiceId(2).setNodeId(5).build().toByteArray();
        System.out.println(req);
        socket.getOutputStream().write(req);
        byte[] bytes = {};
        socket.getInputStream().read(bytes);
        System.out.println(Leaf.leafResp.parseFrom(bytes).getId());

    }
}