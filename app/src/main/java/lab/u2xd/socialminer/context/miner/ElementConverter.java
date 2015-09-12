package lab.u2xd.socialminer.context.miner;

import lab.u2xd.socialminer.context.objects.ContextData;

/**
 * Created by ysb on 2015-09-11.
 */
public class ElementConverter {

    public static final int RAWTYPE_BASIC = 0;
    public static final int RAWTYPE_CALL_LOG = 1;
    public static final int RAWTYPE_SMS_LOG = 2;

    public ElementConverter() {

    }

    public ContextData convert(String[][] PhoneLog, int RawDataType) {
        switch (RawDataType) {
            case 0:
                break;
            default:
                break;
        }
        return null;
    }
}
