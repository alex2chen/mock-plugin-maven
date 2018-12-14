package com.kxtx.mock;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.io.InputStream;

/**
 * 帮助信息
 */
@Mojo(name = "help", threadSafe = true)
public class HelpMojo extends AbstractExecMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        printCutOffRule();
        getLog().warn("please open readme.md!");
        InputStream inputSteam = HelpMojo.class.getResourceAsStream("/readme.md");
        try {
            getLog().info(IOUtils.toString(inputSteam));
        } catch (IOException ex) {
            getLog().error("文件[readme.md]读取失败.");
        } finally {
            IOUtils.closeQuietly(inputSteam);
        }


    }
}
