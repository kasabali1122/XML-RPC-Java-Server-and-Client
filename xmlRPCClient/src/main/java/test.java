import java.io.IOException;
import java.net.*;
import java.util.*;
import org.apache.xmlrpc.*;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


public class test {

    static String host = "localhost";
    static Integer port = 3535;
    public static void main (String [] args) {

        try {

            String url = "http://" + host + ":" + port;
            XmlRpcClient client = new XmlRpcClient();
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(url));
            client.setConfig(config);

            Vector params = new Vector();

            params.add(new Integer(17));
            params.add(new Integer(13));
            Object result = client.execute("sample.sum", params);

            int sum = ((Integer) result).intValue();
            System.out.println("The sum is: "+ sum);

            // Connection Validators
            Isvalid();
            isValidHTTP();
        } catch (Exception exception) {
            System.err.println("JavaClient: " + exception);
        }
    }
    public static void Isvalid()
    {
        Socket socket = new Socket();


        SocketAddress address = new InetSocketAddress(host,port);
        try {
            socket.connect(address, 30000);
            if (socket.isConnected()) {
               System.out.println("Connected");
            }
        } catch (IOException e) {
            System.out.println("Not connected : " + e.getMessage() );
            e.printStackTrace();
        }
    }
    public static void isValidHTTP()
    {
        boolean result = true;
        HttpURLConnection urlConn = null;
        try {

            URL url = new URL("http://" + host + ":" + port);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            System.out.println("Connected");

        } catch (IOException e) {
            System.out.println("Not connected Ozan: " +e.getMessage());
            result = false;
        }
        return ;
    }
}
