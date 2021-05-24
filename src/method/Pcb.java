package method;

import java.util.ArrayList;

/**
 * @author 058gyx
 */
class Pcb {
    private final ArrayList<Integer> childrenIndexList;
    private String name;
    private String pid;
    private String ppid;
    private String state;

    /**
     * 无参构造函数
     */
    public Pcb() {
        childrenIndexList = new ArrayList<>();
        name = null;
        pid = null;
        ppid = null;
        state = null;
    }

    /**
     * 传入一个孩子数组的下标，得到该子进程在进程数组中的位置，再返回该子进程
     *
     * @param indexInChildrenList 孩子数组下标
     * @return 子进程
     */
    public Pcb getChildInPcbList(int indexInChildrenList) {
        int childLocation = childrenIndexList.get(indexInChildrenList);
        return Main.pcbList.get(childLocation);
    }

    /**
     * 传入子进程在孩子数组中的下标，找到该子进程在进程数组中的位置并获得其对象，
     * 最后返回进程名称
     *
     * @param indexInChildrenList 子进程在孩子数组中的下标
     * @return 该子进程的名称
     */
    public String getChildName(int indexInChildrenList) {
        return getChildInPcbList(indexInChildrenList).getField("Name");
    }

    /**
     * 传入子进程在孩子数组中的下标，找到该子进程在进程数组中的位置并获得其对象，
     * 最后返回进程Pid
     *
     * @param indexInChildrenList 子进程在孩子数组中的下标
     * @return 该子进程的Pid
     */
    public String getChildPid(int indexInChildrenList) {
        return getChildInPcbList(indexInChildrenList).getField("Pid");
    }

    /**
     * 传入子进程在孩子数组中的下标，找到该子进程在进程数组中的位置并获得其对象，
     * 最后返回进程Ppid
     *
     * @param indexInChildrenList 子进程在孩子数组中的下标
     * @return 该子进程的Ppid
     */
    public String getChildPpid(int indexInChildrenList) {
        return getChildInPcbList(indexInChildrenList).getField("PPid");
    }

    /**
     * 传入子进程在孩子数组中的下标，找到该子进程在进程数组中的位置并获得其对象，
     * 最后返回进程State
     *
     * @param indexInChildrenList 子进程在孩子数组中的下标
     * @return 该子进程的Ppid
     */
    public String getChildState(int indexInChildrenList) {
        return getChildInPcbList(indexInChildrenList).getField("State");
    }

    /**
     * 向父进程的孩子数组中添加子进程在进程数组中的索引
     *
     * @param location 该子进程在进程数组中的位置
     */
    public void addChildIndex(int location) {
        childrenIndexList.add(location);
    }

    /**
     * 传入孩子数组中的下标，返回该子进程在进程数组中的下标
     *
     * @param indexInChildrenList 孩子数组中的下标
     * @return 子进程在进程数组中的下标
     */
    public int getChildIndexInPcbList(int indexInChildrenList) {
        return childrenIndexList.get(indexInChildrenList);
    }

    /**
     * 传入父进程在进程数组中的下标，得到该父进程的孩子数组，再返回其孩子数组的长度
     *
     * @return 子进程数量
     */
    public int getChildrenListSize() {
        return childrenIndexList.size();
    }

    /**
     * 传入需要获取的属性，返回该对象的属性值
     *
     * @param filed 需要获取的属性名
     * @return Pcb对象的某一个属性值
     */
    public String getField(String filed) {
        switch (filed) {
            case "Name" -> {
                return name;
            }
            case "Pid" -> {
                return pid;
            }
            case "PPid" -> {
                return ppid;
            }
            case "State" -> {
                return state;
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * 设置属性名和对应的属性值
     *
     * @param filed 需要设置的属性名
     * @param value 对应属性需要设置的属性值
     */
    public void setField(String filed, String value) {
        switch (filed) {
            case "Name" -> this.name = value;
            case "Pid" -> this.pid = value;
            case "PPid" -> this.ppid = value;
            case "State" -> this.state = value;
        }
    }
}
