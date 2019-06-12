package com.cn.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;

//@Configuration
public class FileConfiguration {

    private static final String INPUT_DIR = "G:/temp/input";

    private static final String OUTPUT_DIR = "G:/temp/output";

    @Bean
    public MessageSource<File> sourceDirectory(){
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(INPUT_DIR));
        fileReadingMessageSource.setAutoCreateDirectory(true);
        return fileReadingMessageSource;
    }



    @Bean
    public MessageHandler targetDirectory() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }


    @Bean
    public GenericSelector<File> onlyJpgs() {
        return new GenericSelector<File>() {

            @Override
            public boolean accept(File source) {
                return source.getName().endsWith(".JPG");
            }
        };
    }



    @Bean
    public IntegrationFlow fileMove(){
        return IntegrationFlows.from(sourceDirectory(),s-> s.poller(Pollers.fixedDelay(4000))).filter(onlyJpgs()).handle(targetDirectory()).get();
    }


}
