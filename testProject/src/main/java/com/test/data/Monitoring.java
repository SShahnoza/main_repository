package com.test.data;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class Monitoring {
    private WatchService watchService;
    private String currentPath = "";

    public void update(String path, SimpMessagingTemplate simpMessagingTemplate) {
        final ArrayList<Item> data = new ArrayList<>();
        currentPath = path;
        //System.out.println("CurrentPath: " + currentPath);

        while (currentPath.equals(path)){
            try {
                if (isChanged(path) && currentPath.equals(path)) {
                    data.clear();
                    data.addAll((new ListOfItems(path)).data());
                    simpMessagingTemplate.convertAndSend("/topic/files", data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isChanged(String path) throws IOException, InterruptedException {
        Path folder = Paths.get(path);
        watchService = FileSystems.getDefault().newWatchService();
        folder.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        boolean valid = true;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {

                    return true;
                }
                    if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
                        return true;
                                   }
                    if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
                        return true;
                    }
                valid = watchKey.reset();
            }
        }
        while (valid);
        return false;
    }
}
