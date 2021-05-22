import method.Pcb;
import method.PrintTree;
import method.StatusReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * @author 058gyx
 */
public class Main {

    public static ApplicationContext context =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");

    public static void main(String[] args) throws IOException {
        File srcFile = context.getBean(File.class);
        PrintTree printTree = context.getBean(PrintTree.class);
        StatusReader statusReader = context.getBean(StatusReader.class);

        statusReader.findAllStatus(srcFile);
        System.out.println("进程数量：" + Pcb.pcbList.size());
        printTree.print(Pcb.pcbList);
    }
}
