package com.ipinyou.base.drools;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsCheckService implements FileWatchAble {
	private StatelessKnowledgeSession ksession = null;
	
	public void setRuleFilePath(String ruleFilePath) throws Exception {
		URL fileURl = this.getClass().getClassLoader().getResource(ruleFilePath);
		File file = null;
		try {
			if( fileURl == null ){
				file = new File(ruleFilePath);
			}else{
				file = new File(fileURl.toURI());
			}
			
			ruleFilePath = file.getAbsolutePath();
			
			if( file.isDirectory() ){
				throw new Exception(" rule file can not be specified a directory");
			}
			
			load( file, ruleFilePath );
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		Path dir = file.toPath();
		try {
			RuleFileWatcher ruleFileWatcher = new RuleFileWatcher(dir, true, this);
			ruleFileWatcher.setDaemon(true);
			ruleFileWatcher.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(File file, String ruleFilePath) {
		if( !file.exists() ){
			return ;
		}
		if (file.isFile()) {
			try {
				initKnowledgeBase(file, ruleFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ;
		}
	}

	private void initKnowledgeBase(File file, String ruleFilePath) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// ResourceFactory.n
		kbuilder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		ksession = kbase.newStatelessKnowledgeSession();
		
		return ;
	}

	public void check(Object checkeeModel) {
		if( ksession == null ){
			return;
		}
		ksession.execute(checkeeModel);
	}

	@Override
	public void fileChanged(File file, Kind<?> changeKind, String ruleFilePath) {
		if( !file.getName().endsWith("drl") ){
			return ;
		}
		if( changeKind == StandardWatchEventKinds.ENTRY_DELETE ){
			ksession = null;
			return ;
		}
		
		try {
			this.initKnowledgeBase(file, ruleFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
