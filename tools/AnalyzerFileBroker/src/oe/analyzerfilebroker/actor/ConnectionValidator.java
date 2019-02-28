package oe.analyzerfilebroker.actor;

import oe.analyzerfilebroker.StringLocalization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.http.HttpStatus;

public class ConnectionValidator {
    private static final String PING_FILE = "ping.txt";


    public boolean validateConnection(FileShipper shipper) {
        Path queue = PathManager.getTransmissionQueuePath();

        File ping = new File(queue.toString(), PING_FILE);
        try {
            ping.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        ping.deleteOnExit();

        int statusCode = shipper.sendFile(ping, true);
        switch (statusCode) {
            case HttpStatus.SC_OK:
                return true;
            case HttpStatus.SC_NOT_FOUND:
                System.out.println(StringLocalization.instance().getStringForKey("error.server.not.found"));
                return false;
            case HttpStatus.SC_FORBIDDEN:
                System.out.println(StringLocalization.instance().getStringForKey("error.credentials.invalid"));
                return false;
            default:
                System.out.println("error.unknown" + " " + statusCode);
                return false;
        }

    }
}
