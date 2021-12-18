package client;

import com.asan.client.packager.CustomPackager;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
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

          ISOMsg reply = mux.request(createReqMsg(), 10 * 1000);
           // System.out.println(new String(reply.pack()));
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }

    public static ISOMsg createReqMsg() {

        ISOMsg m = new ISOMsg();
       // m.setHeader("ISO011000017".getBytes());
        m.setPackager(new CustomPackager());
        //todo : ....
        try {
//            Date now = new Date();
         //   m.setMTI("200");
//            m.set("3", "000000");
//            m.set(new ISOAmount(4, 840, new BigDecimal(10.00)));
//            m.set("7", ISODate.formatDate(now, "MMddHHmmss"));
//            m.set("11", "1111");
//            m.set("12", ISODate.formatDate(now, "yyyyMMddHHmmss"));
//            m.set("22", "KEY.UNK.ECO.APP");
//            m.set("26", "5999");
//            m.set("27", "00100000000000001000000000000000");
//            m.set("32", "00000000001");
//            m.set("41", "59991515");
//            m.set("42", "888000003518");
//            m.set("43.2", "Shegda Electronics");
//            m.set("43.4", "Richardson");
//            m.set("43.5", "TX");
//            m.set("43.6", "63105");
//            m.set("43.7", "USA");
//            m.set("49.1", "1");
//            m.set("49.3", "121 First Street");
//            m.set("49.4", "85284");
//            m.set("104.4", "1");


       //     m.setMTI("0200");
//            m.set(128, new byte[]{0x1, 0x2, 0x3, 0x4, 0x1, 0x2, 0x3, 0x4});
//            m.set(2, "539983______0077");
//            m.set(7, "100000000");
//            m.set(11, "100000");

            m.setMTI ("200");
           //  m.set(1,"1112");
           m.set (3, "000000");
           // m.set (41, "00000001");
           // m.set (70, "301");
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return m;
    }
}
