package com.ipinyou.base.drools;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RuleFileWatcher extends Thread {

    private final WatchService watcher;
    private final FileWatchAble pathNotifier;
    private final Map<WatchKey,Path> keys;
    private final Map<Path,Set<File>> folders = new HashMap<Path,Set<File>>();
    private final boolean recursive;
    private boolean trace = false;
    private String originPath = null;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
    	if( !dir.toFile().exists() ){
    		return ;
    	}
    	if( dir.toFile().isFile() ){
    		dir = dir.getParent();
    	}
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        
        keys.put(key, dir);
        addFiles2Folder(dir);
    }
    
    private void addFiles2Folder(Path path){
    	File file = path.toFile();
    	if( !file.isDirectory() ){
    		generateIfNull(path.getParent()).add(file);
    		return;
        }
    	
    	File[] subFiles = file.listFiles();
    	if( subFiles == null ){
    		return ;
    	}
    	for( File subFile : subFiles ){
    		if( subFile.isDirectory() ){
    			continue;
    		}
    		generateIfNull(path).add(subFile);
    	}
    }
    
    private void notifyNofier(Path path, WatchEvent.Kind<?> changeKind){
    	if( this.pathNotifier == null ){
    		logNullNotifier(path, changeKind);
    		return ;
    	}
    	if( ENTRY_DELETE != changeKind ){
    		this.pathNotifier.fileChanged(path.toFile(), changeKind, originPath);
    		return ;
    	}
    	Set<File> files = folders.get(path);
    	if( files == null ){
    		File file = path.toFile();
    		
    		folders.get(path.getParent()).remove(file);
    		this.pathNotifier.fileChanged(file, changeKind, originPath);
    		return ;
    	}
    	
        for( File f : files ){
        	folders.get(path).remove(f);
        	this.pathNotifier.fileChanged(f, changeKind, originPath);
        }
    }
    
    private void logNullNotifier(Path path, WatchEvent.Kind<?> changeKind){
    	if( ENTRY_DELETE != changeKind ){
//    		this.pathNotifier.pathChanged(path, changeKind);
    		System.out.println( "Hei.you got some to notice.93." + changeKind + path );
    		return ;
    	}
    	Set<File> files = folders.get(path);
    	if( files == null ){
    		folders.get(path.getParent()).remove(path.toFile());
    		
    		System.out.println( "Hei.you got some to notice.97." + changeKind + path );
    		return ;
    	}
    	
    	if( files != null ){
        	for( File f : files ){
        		folders.get(path).remove(f);
        		System.out.println( "Hei.you got some to notice.104." + changeKind + f.toPath() );
        	}
    	}
    }
    
    private Set<File> generateIfNull(Path key){
    	Set<File> files = folders.get(key);
    	if( files == null ){
    		files = new HashSet<File>();
    		folders.put(key, files);
    	}
    	return files;
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
//            	System.out.println( dir );
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    RuleFileWatcher(Path dir, boolean recursive, FileWatchAble pathNotifier) throws IOException {
    	originPath = dir.toString();
//    	Path dirPath = d
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;
        this.pathNotifier = pathNotifier;
        if (recursive) {
            System.out.format("Scanning %s ...\n", dir);
            if( dir.toFile().isDirectory() ){
            	registerAll(dir);
            }else{
            	register(dir);
            }
            System.out.println("Done.");
        } else {
            
        }

        // enable trace after initial registration
        this.trace = true;
    }

    /**
     * Process all events for keys queued to the watcher
     */
    @Override
    public void run() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }
                
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
                
                // print out event
                System.out.format("%s: %s\n", event.kind().name(), child);
//                if (kind == ENTRY_DELETE) {
                	notifyNofier( child, kind );
//                }
                
                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
//            	System.out.println( "del.." + keys.get(key) );
            	Path delPath = keys.get(key);
            	notifyNofier( delPath, ENTRY_DELETE );
            	
                keys.remove(key);
                
                
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        // parse arguments
//        if (args.length == 0 || args.length > 2)
//            usage();
//        boolean recursive = false;
//        int dirArg = 0;
//        if (args[0].equals("-r")) {
//            if (args.length < 2)
//                usage();
//            recursive = true;
//            dirArg++;
//        }

    	boolean recursive = true;
        // register directory and process its events
        Path dir = Paths.get("/Users/koutosei/Documents/workspace-pinyou/Test-Drool/src/main/rules/");
        Thread watchDirThread = new RuleFileWatcher(dir, recursive, null);
        watchDirThread.start();
        
//        int i1 = (3).();
    }
}
