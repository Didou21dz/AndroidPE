package jkas.androidpe.project;


/**
 * @author JKas
 */
public class JavaModule extends ModuleProject {
    private String packageName;
    private String versionCode;
    private String versionName;

    public JavaModule(String name, String path, String projectDir) {
        super(name, path, projectDir);
    }
}
