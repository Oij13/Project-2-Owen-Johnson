package edu.bsu.cs;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeBSTTest {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        String inputFilePath = "company.csv";
        try {
            File file = new File(inputFilePath);
            Scanner scn = new Scanner(file);
            String header = scn.nextLine();
            System.out.println(header);
            while (scn.hasNext()) {
                String employeeRecord = scn.nextLine();
                String[] s = employeeRecord.split(",");
                Employee empObject = new Employee(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
                employeeList.add(empObject);
            }
            scn.close();
            System.out.println(employeeList.get(0));

            EmployeeBST operation = new EmployeeBST();
            Node root = operation.constructBalancedBST(employeeList);
            operation.levelOrderTraversal(root);

            Node retireEmployee = operation.searchRetire(root);
            if (retireEmployee != null) {
                int retiredId = retireEmployee.getData().getId();
                JOptionPane.showMessageDialog(null,
                        "The retired employee is: " + retireEmployee.getData());

                root = operation.removeRetire(root, retireEmployee.getData());

                if (!operation.search(root, retiredId)) {
                    JOptionPane.showMessageDialog(null,
                            "Retired employee was successfully removed.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Retired employee was not removed.");
                }
            }

            Employee newHire = new Employee(100001,"London","Hodge",27);
            root = operation.insertNewHire(root,newHire);

            System.out.println("Inorder Traversal:\n\n\n");
            operation.inorderTraversal(root);

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}
