package com.github.middleware.mock;

import com.google.common.base.Strings;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;

/**
 * Created by netuser on 17-5-27.
 */
@Deprecated
public abstract class AbstractExecMojo extends AbstractMojo {
    protected void printCutOffRule() {
        getLog().info("-------------------------------------");
    }

    private boolean existsFile(String filePath) {
        //File file = new File(filePath);
        //return file != null && file.exists();
        return !Strings.isNullOrEmpty(filePath);
    }

    protected void checkParams(String configFile, Integer port) throws MojoExecutionException {
        if (!existsFile(configFile)) {
            throw new MojoExecutionException("mock config file not exists!");
        }
        if (port == null || port < 1) {
            throw new MojoExecutionException("mockServer port invalid number!");
        }
    }
}
