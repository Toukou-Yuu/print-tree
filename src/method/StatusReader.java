package method;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 058gyx
 */
@Component
public class StatusReader {
    /**
     * 筛选有效目录并递归寻找有效目录下所有的status文件
     * 寻找到status文件后，读取文本，递归退出一层
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
            if ((file.isDirectory() && isRightDir(file.getName())) || file.isFile()) {
                findAllStatus(file);
            }
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
     * @param pcb      进程对象
     * @param fileLine 读取的status文件中的一行字符串
     */
    private void checkKeyAndValue(Pcb pcb, String fileLine) {
        String[] temp = fileLine.split(":");
        String key = temp[0].trim();
        String value = temp[1].trim();
        switch (key) {
            case "Name", "Pid", "PPid", "State" -> setPcb(pcb, key, value);
        }
    }

    /**
     * 读取并检查文件中的字符串；
     * 当遇到status文件时，该方法被调用；
     * 其中判断了status文件是否有用；
     * 每调用一次该函数，就会有一个pcb被加入pcbList中
     *
     * @param path 需要读取的文件路径
     * @throws IOException 可能不存在该路径
     */
    private void read(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Pcb pcb = new Pcb();

        for (int i = 0; i < 7; i++) {
            String line = bufferedReader.readLine();
            if (line.length() == 0) {
                continue;
            }
            if (line.split(":").length != 2) {
                return;
            }
            checkKeyAndValue(pcb, line);
        }
        Pcb.pcbList.add(pcb);
        bufferedReader.close();
    }

    /**
     * 利用正则表达式筛选只包含数字的目录
     *
     * @param dirName 目录名称
     * @return 目录名称与正则表达式的匹配结果
     */
    private boolean isRightDir(String dirName) {
        Pattern pattern = Pattern.compile("[1-9][0-9]*");
        Matcher matcher = pattern.matcher(dirName);
        return matcher.matches();
    }
}