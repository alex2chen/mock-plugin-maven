package com.github.middleware.mock;


import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.MocoJsonRunner;
import com.github.dreamhead.moco.Runner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * 运行插件
 */
@Mojo(name = "run", threadSafe = true)
public class RunMojo extends AbstractExecMojo {
    @Parameter(property = "configFile", required = true)
    private String configFile;
    @Parameter(property = "port", defaultValue = "8082")
    private Integer port;

    public void execute() throws MojoExecutionException, MojoFailureException {
        printCutOffRule();
        checkParams(this.configFile, this.port);
        Runner mockRunner = null;
        try {
            HttpServer server = MocoJsonRunner.jsonHttpServer(this.port, Moco.file(this.configFile));
            mockRunner = Runner.runner(server);
        } catch (Exception ex) {
            throw new MojoExecutionException("init mock occure error", ex);
        }
        mockRunner.start();
        getLog().info("localhost:" + port + " started.");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            mockRunner.stop();
        }
    }
}
