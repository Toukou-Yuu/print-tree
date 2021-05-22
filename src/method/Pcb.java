package method;

import java.util.ArrayList;

/**
 * @author 058gyx
 */
public class Pcb {
    //所有进程所在的数组
    public static ArrayList<Pcb> pcbList = new ArrayList<>();
    //孩子索引
    private final ArrayList<Integer> childrenIndex;
    private String name;
    private String pid;
    private String ppid;
    private String state;

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
