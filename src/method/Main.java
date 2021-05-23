package method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 058gyx
 * @version Spring
 */
public class Main {
    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");

    public static ArrayList<Pcb> pcbList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File srcFile = context.getBean(File.class);
        context.getBean(StatusReader.class).findAllStatus(srcFile);
        context.getBean(PrintTree.class).print();
    }
}
