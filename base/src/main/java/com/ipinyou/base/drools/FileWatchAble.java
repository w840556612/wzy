package com.ipinyou.base.drools;

import java.io.File;
import java.nio.file.WatchEvent;

public interface FileWatchAble {
	public void fileChanged(File file, WatchEvent.Kind<?> changeKind, String ruleFilePath);
}
