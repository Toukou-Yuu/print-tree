package method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 058gyx
 * @version Spring
 */
@Component
class PrintTree {
    @Value("0")
    private int depth;

    /**
     * 打印进程树；
     * 首先遍历进程数组将每个进程的孩子下标添加到父亲的索引数组中；
     * 再找到祖先进程的索引；
     * 最后从祖先进程开始递归寻找并输出所有进程的子进程；
     */
    public void print() {
        //遍历数组，寻找每个进程的所有孩子的下标并添加到父亲的孩子数组中
        for (int i = 0; i < Main.pcbList.size(); i++) {
            for (int j = 0; j < Main.pcbList.size(); j++) {
                if (getPcb(i).getField("Pid").equals(Main.pcbList.get(j).getField("PPid"))) {
                    getPcb(i).addChildIndex(j);
                }
            }
        }
        int ancientIndex = 0;
        //对祖先进程特判；找到祖先进程索引后输出立即信息
        for (Pcb pcb : Main.pcbList) {
            if (pcb.getField("Pid").equals("1")) {
                System.out.println(pcb.getField("Name"));
                System.out.println("-Pid：" + pcb.getField("Pid"));
                System.out.println("-PPid：" + pcb.getField("PPid"));
                System.out.println("-State：" + pcb.getField("State"));
                break;
            }
            ancientIndex++;
        }

        printChildren(ancientIndex);
    }

    /**
     * 递归寻找某个进程的所有子进程并输出；
     * 进程不存在子进程时，递归返回；
     * 按照递归层数输出分隔符：
     * 每进入一层递归，多输出一组分隔符；
     * 每退出一层递归，少输出一组分隔符
     *
     * @param parentIndex 父进程在进程数组的下标
     */
    private void printChildren(int parentIndex) {
        if (getPcb(parentIndex).getChildrenListSize() == 0) {
            return;
        }
        depth++;
        //递归并遍历输出该进程的所有子进程
        for (int i = 0; i < getPcb(parentIndex).getChildrenListSize(); i++) {
            printPcbInfo(parentIndex, i);
            printChildren(getPcb(parentIndex).getChildIndexInPcbList(i));
        }
        depth--;
    }

    /**
     * 利用子进程在父进程孩子数组中的下标，得到该子进程在进程数组中的位置；
     * 得到位置后输出该子进程信息
     *
     * @param parentIndex 父进程在进程数组中的下标
     * @param childIndex  子进程在父进程孩子数组中的下标
     */
    private void printPcbInfo(int parentIndex, int childIndex) {
        System.out.print(printLine(depth - 1));
        System.out.println(depth + "级->" + getPcb(parentIndex).getChildName(childIndex));
        System.out.print(printLine(depth));
        System.out.println("Pid：" + getPcb(parentIndex).getChildPid(childIndex));
        System.out.print(printLine(depth));
        System.out.println("PPid：" + getPcb(parentIndex).getChildPpid(childIndex));
        System.out.print(printLine(depth));
        System.out.println("State：" + getPcb(parentIndex).getChildState(childIndex));
    }

    /**
     * 输出分隔符调整结构
     *
     * @return 分隔符字符串
     */
    private String printLine(int depth) {
        return "----|".repeat(Math.max(0, depth)) + "----";
    }

    /**
     * 传入所需要的进程在进程数组中的下标，返回该进程对象
     *
     * @param indexInPcbList 进程在进程数组中的下标
     * @return 该进程对象
     */
    private Pcb getPcb(int indexInPcbList) {
        return Main.pcbList.get(indexInPcbList);
    }
}
