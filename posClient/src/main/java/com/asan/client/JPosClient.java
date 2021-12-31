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
                System.out.println("mux is null");
            } else {
                System.out.println("success to read mux");
            }
            //sender
            JposUtility jposUtility = new JposUtility();
            // ISOMsg isoMsg = jposUtility.unpackMessage("0200313082200000020000000400000000000000111312532012345630300301");
            ISOMsg isoMsg = jposUtility.unpackMessage("30323030463233434534433132384531393830363030303030303030313030303030303131363938333235343430303130383635353433313030303030303030303030303030303030363239313335313136303030383336313335313136303632393030303030363239373337353336343032313030303430393538313637323031313239393833323534343030313038363535343D323030333132303030303030303335363438313335393235303031313036323130303030303030303031313330303956455249464F4E45205558205445535420202020202054656872616E20202020202020544852495230313330303020202020303130303030333634443444434346443936353131353846313032303130313030303030303030303030313231343030303030303031303831303645303030303030304330303030303030303030304330303030303030303030303035393149523736303136303030303030303030303731363936303737342020202020202020303030303030303030303030303030303030303030303030323032313036323930303032303935383136373230303035313532313142334534353831393644");
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
