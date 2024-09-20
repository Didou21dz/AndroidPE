package jkas.androidpe.projectAnalyzer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jkas.androidpe.project.Project;
import jkas.androidpe.resourcesUtils.utils.ProjectsPathUtils;
import jkas.androidpe.resourcesUtils.utils.ResCodeUtils;
import jkas.codeUtil.CodeUtil;
import jkas.codeUtil.Files;
import jkas.codeUtil.XmlManager;
import org.w3c.dom.Element;
import rkb.datasaver.RKBDataAppSettings;

/**
 * @author JKas
 */
public class SearchingProjects {
    private Context C;
    private OnProjectFoundListener listener;
    private ArrayList<Pair<String, String>> listPathToAllProjects = new ArrayList<>();

    public SearchingProjects(Context c) {
        C = c;
    }

    public void load() {
        listPathToAllProjects.clear();
        for (var e : RKBDataAppSettings.xmlF.getElementsByTagName("path")) {
            String dir = Files.getExternalStorageDir() + File.separator + e.getTextContent();
            for (var path : Files.listDir(dir)) {
                listPathToAllProjects.add(new Pair<>(e.getTextContent(), path));
            }
        }
        if (listPathToAllProjects.size() > 0) loadProject(0);
    }

    private void loadProject(final int position) {
        if (position == listPathToAllProjects.size()) return;
        String dir = listPathToAllProjects.get(position).second;
        String buildGradle = dir + "/build.gradle";
        String settingsGradle = dir + "/settings.gradle";
        if (Files.isFile(buildGradle) || Files.isFile(buildGradle + ".kts")) {
            if (Files.isFile(settingsGradle) || Files.isFile(settingsGradle + ".kts")) {
                final Project p =
                        new Project(
                                listPathToAllProjects.get(position).first,
                                Files.getNameFromAbsolutePath(dir));
                p.setPackageName("..."); // default pkg

                // searching for package.
                final String manifest = dir + "/app" + ProjectsPathUtils.ANDROID_MANIFEST_PATH;
                String pkg = null;
                try {
                    if (Files.isFile(manifest)) {
                        final XmlManager xmlFile = new XmlManager(C);
                        if (xmlFile.initializeFromPath(manifest)) {
                            pkg = xmlFile.getElement("manifest", 0).getAttribute("package");
                            if (pkg == null || pkg.isEmpty()) {
                                p.setPackageName(tryFindPkg(p));
                            } else {
                                p.setPackageName(pkg);
                            }
                        }
                    }
                } catch (Exception f) {
                    // can not find pkg
                }

                ProjectView PV = new ProjectView(C, p);
                listener.onFound(PV.getView());
            }
        }

        if ((position + 1) == listPathToAllProjects.size()) listener.onFinish();
        else new Handler(Looper.getMainLooper()).postDelayed(() -> loadProject(position + 1), 6);
    }

    public static String tryFindPkg(Project p) {
        String pkg = p.getPackageName();
        String path2Gradle = p.getAbsolutePath() + "/app/build.gradle";
        if (!Files.isFile(path2Gradle)) path2Gradle += ".kts";
        if (!Files.isFile(path2Gradle)) return pkg;

        String gradleCode = Files.readFile(path2Gradle);
        if (gradleCode == null) return pkg;

        String namespace = "namespace \\'.*\\'";
        String namespaceKts = "namespace \\\".*\\\"";
        String applicationId = "applicationId \\'.*\\'";
        String applicationIdKts = "applicationId \\\".*\\\"";

        Pattern pattern = Pattern.compile(namespace);
        Matcher matcher = pattern.matcher(gradleCode);
        if (matcher.find()) {
            pkg = matcher.group().split("\\'")[1];
        }

        pattern = Pattern.compile(namespaceKts);
        matcher = pattern.matcher(gradleCode);
        if (matcher.find()) {
            pkg = matcher.group().split("\\\"")[1];
        }

        pattern = Pattern.compile(applicationId);
        matcher = pattern.matcher(gradleCode);
        if (matcher.find()) {
            pkg = matcher.group().split("\\'")[1];
        }

        pattern = Pattern.compile(applicationIdKts);
        matcher = pattern.matcher(gradleCode);
        if (matcher.find()) {
            pkg = matcher.group().split("\\\"")[1];
        }

        if (!ResCodeUtils.isAValidePackageName(pkg)) pkg = "...";

        return pkg;
    }

    public void setOnProjectFound(OnProjectFoundListener listener) {
        this.listener = listener;
    }

    public interface OnProjectFoundListener {
        public void onFound(View v);

        public void onFinish();
    }
}
