package method;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 058gyx
 */
@Component
public class StatusReader {
    /**
     * 递归寻找该源文件下所有的status文件
     * 寻找到status文件后，读取文本
     *
     * @param srcFile 源文件
     * @throws IOException 文件可能不存在
     */
    public void findAllStatus(File srcFile) throws IOException {
        if (srcFile.isFile()) {
            if (srcFile.getName().equals("status")) {
                this.read(srcFile.getPath());
            }
            return;
        }

        File[] files = srcFile.listFiles();
        assert files != null;
        for (File file : files) {
            findAllStatus(file);
        }
    }

    /**
     * 为进程设置相应的属性
     * 当key为Name、Pid、PPid、State时为其赋值
     *
     * @param pcb   进程
     * @param key   pcb的属性
     * @param value pcb的属性值
     */
    private void setPcb(Pcb pcb, String key, String value) {
        switch (key) {
            case "Name" -> pcb.setName(value);
            case "Pid" -> pcb.setPid(value);
            case "PPid" -> pcb.setPpid(value);
            case "State" -> pcb.setState(value);
        }
    }

    /**
     * 检查传入的一行数据中是否是需要的数据
     * 如果是需要的数据，则设置Pcb属性
     *
     * @param pcb      pcb块
     * @param fileLine 读取的status文件中的一行字符串
     */
    private void checkKeyAndValue(Pcb pcb, String fileLine) {
        String[] temp = fileLine.split(":");
        String key = temp[0].trim();
        String value = temp[1].trim();
        if (key.equals("Name") || key.equals("Pid") || key.equals("PPid") || key.equals("State")) {
            setPcb(pcb, key, value);
        }
    }

    /**
     * 读取并检查文件中的字符串；
     * 当遇到status文件时，该方法被调用；
     * 每调用一次该函数，就会有一个pcb被加入pcbList中
     *
     * @param path 需要读取的文件路径
     * @throws IOException 可能不存在该路径
     */
    private void read(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Pcb pcbInfo = new Pcb();

        for (int i = 0; i < 7; i++) {
            String line = bufferedReader.readLine();
            checkKeyAndValue(pcbInfo, line);
        }
        Pcb.pcbList.add(pcbInfo);
        bufferedReader.close();
    }
}