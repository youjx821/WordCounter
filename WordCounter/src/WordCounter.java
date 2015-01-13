import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
 
 
 
public class WordCounter {
 
    private JFrame frmWordcounter;
    private JTable table;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WordCounter window = new WordCounter();
                    window.frmWordcounter.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the application.
     */
    public WordCounter() {
        initialize();
    }
 
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmWordcounter = new JFrame();
        frmWordcounter.setTitle("WordCounter");
        frmWordcounter.setBounds(100, 100, 800, 600);
        frmWordcounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JMenuBar menuBar = new JMenuBar();
        frmWordcounter.setJMenuBar(menuBar);
 
        JMenu mnNewMenu = new JMenu("文件");
        menuBar.add(mnNewMenu);
 
        JMenuItem mntmNewMenuItem = new JMenuItem("退出");
        mnNewMenu.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
 
        frmWordcounter.getContentPane().setLayout(null);
 
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 400, 541);
        frmWordcounter.getContentPane().add(scrollPane);
 
        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);
 
        JButton button = new JButton("分析");
        button.setBounds(407, 241, 70, 40);
        frmWordcounter.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String str = textArea.getText();
                if(str != null || !"".equals(str)){
                    List<Map.Entry<String, Integer>> list = func(str);
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();//获得表格模型
                    dtm.setRowCount(0);//清空表格数据
                    for(Map.Entry<String, Integer> mapEntry : list){
                        System.out.println(mapEntry.toString());
                        dtm.addRow(new Object[]{mapEntry.getKey(), mapEntry.getValue()});
                    }
                }
            }
        });
 
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(484, 0, 300, 541);
        frmWordcounter.getContentPane().add(scrollPane_1);
 
        String[] columnNames = {"单词", "出现次数"};
//        String[][] tableValues = {{"A1", "B1"}, {"A2", "B2"}, {"A3", "B3"}};
        DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
        table = new JTable(dtm);
        scrollPane_1.setViewportView(table);
 
    }
 
    public List<Map.Entry<String, Integer>> func(String str){
        String[] strs = str.split("[^a-zA-Z]");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String s : strs){
            if(!"".equals(s)){//s不能为空
                if(map.keySet().contains(s)){
                    map.put(s, map.get(s)+1);
                }else{
                    map.put(s, 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(!o2.getValue().equals(o1.getValue())){
                    return o2.getValue() - o1.getValue();
                }else{
                    return o1.getKey().compareToIgnoreCase(o2.getKey());//忽略大小写
                }
            }
        });
        return list;
    }
 
}