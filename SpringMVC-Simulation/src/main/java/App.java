import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;

public class App {
    //这种方式启动不需要web.xml
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        //tomcat.addContext 只会去实例化context的资源目录，并不会加载web生命周期，简单理解为加载项目所有文件，不加载webapp目录下的所有文件
        Context context = tomcat.addContext("/",System.getProperty("java.io.tmpdir"));
        context.addLifecycleListener((LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());
        tomcat.start();
        tomcat.getServer().await();
    }
}
