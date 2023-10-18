package company.lesson02;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            System.out.println("IP-Address google.com: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}
