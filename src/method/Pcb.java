package method;

import java.util.ArrayList;

/**
 * @author 058gyx
 */
public class Pcb {
    //所有进程所在的数组
    public static ArrayList<Pcb> pcbList = new ArrayList<>();
    //进程名
    private String name;
    //进程pid
    private String pid;
    //该进程的父进程pid
    private String ppid;
    //该进程的状态
    private String state;
    //该进程所有的孩子进程在进程数组中的索引
    private final ArrayList<Integer> childrenIndex;

    /**
     * @return 该进程的孩子索引数组
     */
    public ArrayList<Integer> getChildrenIndex() {
        return childrenIndex;
    }

    public Pcb() {
        childrenIndex = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPid() {
        return pid;
    }

    public String getPpid() {
        return ppid;
    }

    public String getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPpid(String ppid) {
        this.ppid = ppid;
    }

    public void setState(String state) {
        this.state = state;
    }
}
