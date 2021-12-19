package com.asan.client.isomsg;


import com.asan.client.packager.CustomPackager;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class JposUtility {
    private static final Logger logger = LoggerFactory.getLogger(JposUtility.class);



    public ISOMsg unpackMessage(String channelManagerMessage) {
        byte[] requestMessage = channelManagerMessage.getBytes();

        logger.info("incoming request message for unpacking is : " + channelManagerMessage);
        ISOMsg isoMsg = new ISOMsg();
        // set packager, change IranArghamPackager for the matching one.
        isoMsg.setPackager(new CustomPackager());
        //unpack the message using the packager
        try {

            isoMsg.unpack(EncodeToAscii(channelManagerMessage));
           // logISOMsg(isoMsg);
        } catch (Exception e) {
            logger.error("error while unpacking message : " + e.getMessage() + channelManagerMessage + Arrays.toString(e.getStackTrace()) );
        }
        return isoMsg;
    }

    public void logISOMsg(ISOMsg msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("----ISO MESSAGE-----\n");
        try {
            stringBuilder.append("    MTI is: ").append(msg.getMTI()).append("\n");
            for (int i = 1; i <= msg.getMaxField(); i++) {
                if (msg.hasField(i)) {
                    if (i == 128 || i == 52)//because of security reasons
                        stringBuilder.append("    Field-").append(i).append(" : ").append("****\n");
                    else {
                        stringBuilder.append("    Field-").append(i).append(" : ").append(msg.getString(i)).append("\n");
                    }
                }
            }
            logger.info(stringBuilder.toString());
        } catch (ISOException e) {
            logger.error("An error occurred in extraction additional data values. " + Arrays.toString(e.getStackTrace()));
        } finally {
            logger.info("--------------------");
        }

    }

    public static byte[] EncodeToAscii(String sValue) throws UnsupportedEncodingException {
        byte[] by8859 = sValue.getBytes("Cp1256");
        String tempValue = new String(by8859, "ISO8859_1");
        byte[] bValue = new byte[tempValue.length()];
        for (int y = 0; y < tempValue.length(); y++) {
            bValue[y] = (byte) ((int) tempValue.charAt(y));
        }
        return bValue;
    }

}