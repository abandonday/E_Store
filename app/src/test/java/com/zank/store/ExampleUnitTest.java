package com.zank.store;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        List<String> list = new ArrayList<String>();

        list.add("1-1456153289-3-1");

        list.add("1-1456156245-3-2");

        list.add("4561432174-3-1");
        list.add("4564131321-1-1");
        list.add("4564134561-2-1");
        list.add("4564134561-2-2");
        list.add("4561432174-3-2");
        list.add("4564564561-510-2");
        list.add("4654163136-2-1");
        list.add("4654163136-3-1");
        list.add("7456452114-3-2");

        List<String> barCode = new ArrayList<String>(); // 所有条码号
        List<String> rBarCode = new ArrayList<String>(); // 所有返单条码号

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).split("-").length == 3) { // 条码号
                barCode.add(list.get(i));
            }
            if (list.get(i).split("-").length == 4) { // 返单条码号
                rBarCode.add(list.get(i));
            }
        }
        while (barCode.size() != 0) { // 条码号查重
            List<String> cbarCode = new ArrayList<String>(); // 某一条码号集合
            String str = barCode.get(0).split("-")[0];
            for (String code : barCode) { // 根据单号遍历出所有的条码号
                if (code.contains(str)) {
                    cbarCode.add(code);
                }
            }
            barCode.removeAll(cbarCode); // 删除队列中的数据
            int count = 1;
            for (String s : cbarCode) {
                int sp = Integer.parseInt(s.split("-")[1]);
                if (sp > count) {
                    count = sp;
                }
            }
            if (count > cbarCode.size()) {
                for (String s : cbarCode) {
                    System.out.println("缺件：" + s + " 数量："
                            + (count - cbarCode.size()));
                }
            }
            System.out.println("---------------------------------------");
        }

        while (rBarCode.size() != 0) { // 返单条码号查重
            List<String> crBarCode = new ArrayList<String>(); // 某一返单条码号集合
            String str = rBarCode.get(0).split("-")[0] + "-"
                    + rBarCode.get(0).split("-")[1];
            for (String code : rBarCode) { // 根据单号遍历出所有的条码号
                if (code.contains(str)) {
                    crBarCode.add(code);
                }
            }
            rBarCode.removeAll(crBarCode); // 删除队列中的数据
            int count = 1;

            for (String s : crBarCode) {
                int sp = Integer.parseInt(s.split("-")[2]);
                if (sp > count) {
                    count = sp;
                }
            }
            if (count > crBarCode.size()) {
                for (String s : crBarCode) {
                    System.out.println("缺件：" + s + " 数量："
                            + (count - crBarCode.size()));
                }
            }

            System.out.println("---------------------------------------");
        }
    }
}