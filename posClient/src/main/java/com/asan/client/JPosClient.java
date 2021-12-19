package com.asan.client;

import com.asan.client.isomsg.JposUtility;
import com.asan.client.packager.CustomPackager;
import org.jpos.iso.*;
import org.jpos.q2.Q2;
import org.jpos.util.NameRegistrar;


public class JPosClient {
    public static void startQ2() {
        Q2 q2 = new Q2("src/main/deploy/");
        q2.start();
        System.out.println("Starting Deployment");
    }

    public static void main(String[] args) {
        startQ2();
        // give some delay before sent req, cz q2 deployment is async
        ISOUtil.sleep(5000);

        // Process send and receive data
        try {
            MUX mux = (MUX) NameRegistrar.getIfExists("mux.client-mux");
            if (mux == null) {
                System.out.println("koo");
            } else {
                System.out.println("ssiiii");
            }
            //sender
            JposUtility  jposUtility=new JposUtility();
            ISOMsg isoMsg = jposUtility.unpackMessage("0200313082200000020000000400000000000000111312532012345630300301");
            mux.request(isoMsg, 10 * 1000);
           // mux.request(createReqMsg(), 10 * 1000);
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }

    private static ISOMsg createReqMsg() {

        ISOMsg m = new ISOMsg();
        m.setPackager(new CustomPackager());
        //todo : ....
        try {
            m.setMTI("200");
            m.set(1, "1112");
            m.set(3, "000000");
            m.set(41, "00000001");
            m.set(70, "301");
            m.set(42, "888000003518");
            m.set(43, "Shegda Electronics");
            m.set(44, "Richardson");
            m.set(45, "63105");
            m.set(46, "USA");
            m.set(47, "1");
            m.set(48, "121 First Street");
            m.set(49, "852");
            m.set(50, "1");
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return m;
    }
}
