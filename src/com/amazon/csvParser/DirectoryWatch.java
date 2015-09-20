package com.amazon.csvParser;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * This program demonstrates how to use the Watch Service API to monitor change
 * events for a specific directory.
 * @author Sriram
 *
 */
public class DirectoryWatch {

	public static void main(String[] args) {
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("D:\\monitor");
			dir.register(watcher, ENTRY_CREATE);

			System.out.println("Watch Service registered for dir: " + dir.getFileName());

			while (true) {
				WatchKey key;
				try {
					key = watcher.take();
				} catch (InterruptedException ex) {
					return;
				}

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path fileName = ev.context();
					File csvFile = fileName.toFile();
					System.out.println(kind.name() + ": " + fileName);
					if(kind==ENTRY_CREATE) {
						System.out.println("Receivede the file");
						if(csvFile.getName().endsWith("csv")) {
							System.out.println("Its a valid csv file");
							//ProcessFile.processFile(csvFile);
						}
					}

				}

				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
