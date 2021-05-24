package method;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 058gyx
 */
public class Main {

    public static ArrayList<Pcb> pcbList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File srcFile = new File("123456");
        new StatusReader().findAllStatus(srcFile);
        new PrintTree().print();
    }
}
