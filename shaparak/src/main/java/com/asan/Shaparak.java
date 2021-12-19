package com.asan;
import org.jpos.iso.*;
import org.jpos.q2.Q2;

import java.io.IOException;

public class Shaparak implements ISORequestListener {
    public static void main(String[] args) {
        Q2 q2 = new Q2("src/main/deploy/");
        q2.start();
    }

    public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
        try {
                ISOMsg reply = (ISOMsg) isoMsg.clone();
                reply.setResponseMTI();
                reply.set(39, "00");
                reply.set(125,"transaction done!");
                isoSrc.send(reply);
                return true;
        } catch (ISOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
