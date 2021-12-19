package com.asan;

import org.jpos.iso.*;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;

import java.io.IOException;

public class JposServerTest implements ISORequestListener {
    public static void main(String[] args) {
        Q2 q2 = new Q2("src/main/deploy/");
        q2.start();
    }

    public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
        try {
                isoMsg.set(98,"serverPos");
            // send request to server B
            MUX mux = (MUX) NameRegistrar.getIfExists("mux.clientmux");
            ISOMsg reply = mux.request(isoMsg, 100 * 1000);
            if (reply != null) {
                System.out.println(new String(reply.pack()));
                reply.set(124, "RESPONSE FROM SERVER A");
                isoSrc.send(reply);
            }
        } catch (ISOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
