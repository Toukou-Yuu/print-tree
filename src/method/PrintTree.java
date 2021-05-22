package method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author 058gyx
 */
@Component
public class PrintTree {
    @Value("0")
    private int tabCount;

    /**
     * 打印进程树；
     * 首先遍历进程数组将每个进程的孩子下标添加到父亲的索引数组中；
     * 再找到祖先进程的索引；
     * 最后从祖先进程开始递归寻找并输出所有进程的子进程；
     *
     * @param pcbList 所有进程所在数组
     */
    public void print(ArrayList<Pcb> pcbList) {
        for (int i = 0; i < pcbList.size(); i++) {
            for (int j = 0; j < pcbList.size(); j++) {
                if (pcbList.get(i).getPid().equals(pcbList.get(j).getPpid())) {
                    pcbList.get(i).getChildrenIndex().add(j);
                }
            }
        }
        int ancientIndex = 0;
        for (int i = 0; i < pcbList.size(); i++) {
            if (pcbList.get(i).getPid().equals("1")) {
                ancientIndex = i;
                System.out.println(pcbList.get(i).getName());
            }
        }
        this.findAndPrintChildren(pcbList, ancientIndex);
    }

    /**
     * 递归寻找某个进程的所有子进程并输出；
     * 进程不存在孩子时，递归返回；
     * 按照递归层数输出制表符：
     * 每进入一层递归，制表符数量+1；
     * 每退出一层递归，制表符数量-1
     *
     * @param pcbList 所有进程所在的数组
     * @param index   进程所在数组的下标
     */
    private void findAndPrintChildren(ArrayList<Pcb> pcbList, int index) {
        if (pcbList.get(index).getChildrenIndex().size() == 0) {
            return;
        }

        tabCount++;
        for (int i = 0; i < pcbList.get(index).getChildrenIndex().size(); i++) {
            for (int j = 0; j < tabCount - 1; j++) {
                System.out.print("---|--");
            }
            System.out.println("---"  + tabCount + "级->" + pcbList.get(pcbList.get(index).getChildrenIndex().get(i)).getName()
//                    + "\n" + printTab(tabCount) + "----Pid："
//                    + pcbList.get(pcbList.get(index).getChildrenIndex().get(i)).getPid()
//                    + "\n" + printTab(tabCount) + "----PPid："
//                    + pcbList.get(pcbList.get(index).getChildrenIndex().get(i)).getPpid()
//                    + "\n" + printTab(tabCount) + "----State："
//                    + pcbList.get(pcbList.get(index).getChildrenIndex().get(i)).getState()
            );

            findAndPrintChildren(pcbList, pcbList.get(index).getChildrenIndex().get(i));
        }
        tabCount--;
    }

    /**
     * 输出制表符调整结构
     *
     * @param tabCount 制表符数量
     * @return 制表符
     */
    private String printTab(int tabCount) {
        return "----|".repeat(Math.max(0, tabCount)) +
                "--- ";
    }
}
