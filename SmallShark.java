import java.util.InputMismatchException;
import java.util.Scanner;

public class SmallShark {
    //This is an Accounting System.
    private int amount;// 总金额
    private final StringBuilder details = new StringBuilder();// 收支详情
    private final Scanner sc = new Scanner(System.in);
    private boolean isRunning;

    public SmallShark(){
        this.amount = 0;
        this.isRunning = true;
        run();
    }

    private void load(){
        System.out.println("-------欢迎来到小鲨鱼记账系统-------");
        System.out.println("1.收支明细");
        System.out.println("2.记录收入");
        System.out.println("3.记录支出");
        System.out.println("4.退出系统");
        System.out.println("请选择你要执行的功能：");
    }

    private void run(){
        try{
            while (isRunning){
                load();
                //调用 private int readInt().
                int input = readInt();
                switch (input){
                    case 1:
                        printDetails();
                        break;
                    case 2:
                        recordIncome();
                        break;
                    case 3:
                        recordExpenses();
                        break;
                    case 4:
                        exit();
                        break;
                    default:
                        System.out.println("您的输入有误，请重新输入：");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("系统发生错误：" + e.getMessage());
            e.printStackTrace();
        }finally {
            //关闭 Scanner.
            sc.close();
            System.out.println("系统资源已释放，再见！");
        }
    }

    private int readInt(){
        //使用循环，确保输入！
        while (true){
            try{
                int num = sc.nextInt();
                sc.nextLine();  // ✅ 这里清除换行符
                return num;
            }catch (InputMismatchException e){
                System.out.println("错误：输入的不是有效整数！");
                sc.next();  // 清除错误的输入
            }
        }
    }

    private void printDetails(){
        if(details.length() == 0){
            System.out.println("当前没有收支记录。");
        }else{
            System.out.print(details);
            //details 尾部有换行符。
        }
    }

    private void recordIncome(){
        System.out.println("请输入收入的金额（元）:");
        //调用私有方法 readInt().
        int income = readInt();
        amount += income;
        System.out.println("请记录收入来源：");
        String d = sc.nextLine(); //
        String detail = "收入：" + income + ",收入来源：" + d + ",剩余金额：" + amount +  "\n";
        details.append(detail);
    }

    private void recordExpenses(){
        System.out.println("请输入支出的金额（元）:");
        int expenses = readInt();
        amount -= expenses;
        System.out.println("请记录支出去向：");
        String d = sc.nextLine(); // 允许输入带空格的描述
        String detail = "支出：" + expenses + ",支出去向：" + d + ",剩余金额：" + amount +  "\n";
        details.append(detail);
    }

    private void exit(){
        System.out.println("您确定要退出系统吗(Y/N)？");
        String s = sc.nextLine().trim().toLowerCase();
        //trim() 去除去除字符串开头和结尾的空白字符。
        if (s.equals("y")) {
            System.out.println("即将退出小鲨鱼系统，欢迎下次使用");
            isRunning = false;
        } else {
            System.out.println("请继续使用小鲨鱼记账系统。");
        }
    }

    public static void main(String[] args) {
        SmallShark ss = new SmallShark();
    }
}
