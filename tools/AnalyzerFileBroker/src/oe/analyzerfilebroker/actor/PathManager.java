package oe.analyzerfilebroker.actor;

import oe.analyzerfilebroker.StringLocalization;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class PathManager {

    private static Path queue;
    private static final String QUEUE = "." + File.separator + "transmissionQueue";
    private static FileShipper fileShipper;

    public static Path getTransmissionQueuePath(){
        return queue;
    }
    public static void ensureQueueDirectory() {
        queue = Paths.get(QUEUE);

        boolean create = Files.notExists(queue, LinkOption.NOFOLLOW_LINKS);

        if( create){
            try {
                Files.createDirectory(queue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File getFileFromQueue(String timeFileName) {
        Path filePath = Paths.get( QUEUE + File.separator + timeFileName);
        return Files.notExists(filePath, LinkOption.NOFOLLOW_LINKS) ? null : filePath.toFile();
    }

    public static void createFileInQueue(String timeFileName) {
        try {
            Files.createFile(Paths.get( QUEUE + File.separator + timeFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> getFilesFromAnalyzer(String sourceDirName) {
        File directory = new File(sourceDirName);
        return Arrays.asList(directory.listFiles());
    }

    public static void copyToQueue(File file, String newFileName) {
        try {
            Files.copy(Paths.get(file.getPath()), Paths.get( QUEUE + File.separator + newFileName), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void touchFile(File file) {
        try {
            Files.setLastModifiedTime( Paths.get(file.getPath()), FileTime.fromMillis(System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> getFilesFromQueue(){
        File directory = new File(QUEUE);
        File[] files = directory.listFiles();
        if( files == null){
            files =  new File[]{};
        }
        return Arrays.asList(files);
    }
    public static void flushQueue(String[] specialQueueFiles) {
        /*
        This is not correct, it will send ALL files, not just the ones for the named analyzer and it does not look to
        be thread safe.  This method is called from several threads.

         */
        List<String> list = Arrays.asList(specialQueueFiles);
        List<File> queueFiles = getFilesFromQueue();

        for( File file : queueFiles){
            boolean isSpecial = false;
            for( String specialName : list){
                if( file.getName().contains(specialName)){
                    isSpecial = true;
                    break;
                }
            }
            if( !isSpecial){
                    switch (fileShipper.sendFile(file)){
                        case HttpStatus.SC_NOT_FOUND:
                            System.out.println(file.getName() + " " + StringLocalization.instance().getStringForKey("error.server.not.found"));
                            break;
                        case HttpStatus.SC_BAD_REQUEST:
                            System.out.println(file.getName() + " "  + StringLocalization.instance().getStringForKey("error.file.confusing"));
                            break;
                        case HttpStatus.SC_OK:
                            System.out.println(file.getName() + " " + StringLocalization.instance().getStringForKey("success.file.sent"));
                        case HttpStatus.SC_FORBIDDEN:
                            System.out.println(StringLocalization.instance().getStringForKey("error.credentials.invalid"));
                    }
            }
        }
    }

    public static void setFileShipper(FileShipper fileShipper) {
        PathManager.fileShipper = fileShipper;
    }
}
