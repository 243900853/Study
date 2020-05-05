import com.App;
import com.xiaobi.bean.A;
import com.xiaobi.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = App.class)
public class SpringBootTest {
    @Autowired
    Student student;

    @Value("${pass}")
    String pass;
    //#{}这个就是SpEL表达式
    @Value("#{${a}+${b}}")
    String c;

    @Value("#{${booleanA}? true : false}")
    String booleanA;

    @Value("#{1+2}")
    String d;

    @Value("#{T(java.lang.Math).random()}")
    double dou;

    @Autowired
    A aa;

    @Test
    public void test(){
        System.out.println(student);
        System.out.println(pass);
        System.out.println(c);
        System.out.println(d);
        System.out.println(booleanA);
        System.out.println(dou);
    }
}
