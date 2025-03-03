package com.dailyDeals.dailyDeals_v6.LoggingAspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
public class CustomLogger  {

    static Logger logger = LoggerFactory.getLogger("CustomLog");
    public static void setInfoMessage(String msg) {
        CustomLogger.logger.info(msg);
    }

    public static void setErrorMessage(String msg, Exception exception) {
        CustomLogger.logger.error(msg, exception);
    }

    public static void setDebugMessage(String msg) {
        CustomLogger.logger.debug(msg);
    }

}
