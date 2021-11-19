package ua.com.alevel.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BinTree {

    private static void genMenuGraph() {
        System.out.println("1. Добавить новый узел.");
        System.out.println("2. Найти максмальную глубину.");
        System.out.println("X. В главное меню. ");
    }

    private static int longestConsecutive(TreeNode graphRoot) {

        if (graphRoot == null) {
            return 0;
        }
        int max = 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Queue<Integer> l = new LinkedList<Integer>();
        q.add(graphRoot);
        l.add(1);
        while (q.size() > 0) {
            TreeNode node = q.poll();
            int length;
            length = l.poll();
            max = Math.max(max, length);
            if (node.left != null) {
                if (node.left.value == node.value + 1) {
                    l.add(length + 1);
                } else {
                    l.add(1);
                }
                q.add(node.left);
            }
            if (node.right != null) {
                if (node.right.value == node.value + 1) {
                    l.add(length + 1);
                } else {
                    l.add(1);
                }
                q.add(node.right);
            }
        }
        return max;
    }

    private static void insert(TreeNode node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                System.out.println("  Добавлен узел '" + value + "' слева " + node.value);
                node.left = new TreeNode(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                System.out.println("  Добавлен узел '" + value + "' справа " + node.value);
                node.right = new TreeNode(value);
            }
        }
    }

    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        TreeNode nodeRoot;
        do {
            try {
                System.out.println("Введите корень графа: ");
                nodeRoot = new TreeNode(Integer.parseInt(reader.readLine()));
                System.out.println("===================");
                break;
            } catch (NumberFormatException n) {
                System.out.println("Ошибка. Неправильный ввод.");
            }
        } while (true);
        do {
            genMenuGraph();
            input = reader.readLine();
            switch (input) {
                case "1" -> {
                    do {
                        try {
                            System.out.print("Введите значение узла: ");
                            insert(nodeRoot, Integer.parseInt(reader.readLine()));
                            System.out.println();
                            break;
                        } catch (NumberFormatException n) {
                            System.out.println("Ошибка. Неправильный ввод.");
                        }
                    } while (true);
                }
                case "2" -> System.out.println("Максмальная глубина:" + longestConsecutive(nodeRoot));
            }
        } while (!input.equals("X"));
    }
}