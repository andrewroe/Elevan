/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.model;

import edu.model.TaskHandlerBean;
import edu.model.todoAppService;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author andrewroe
 */
public class TaskListApp {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner s = new Scanner(System.in);
        todoAppService service = todoAppService.instance();

        while (true) {
            System.out.println("===================================");

            try {
                //for (TaskHandlerBean task : service.getTasks()) {
                    //System.out.println(task);

                    System.out.println("What would you like to do?");
                    System.out.println(" A)add R)remove S)submit E)exit");
                    switch (s.nextLine().toLowerCase()) {
                        case "a":
                        case "add":
                            System.out.println("Enter description:");
                            String description = s.nextLine();
                            System.out.println("Enter instructor:");
                            String instructor = s.nextLine();
                            System.out.println("Enter duedate:");
                            String duedate = s.nextLine();
                            
                            //  !!! Do Later - service.add( description, instructor, duedate, false);
                            break;
                        case "r":
                        case "remove":
                            System.out.println("Enter ID of task:");
                            service.remove(s.nextInt());
                            s.nextLine();
                            break;
                        case "e":
                        case "exit":
                            System.out.println("Good bye!");
                            System.exit(0);
                            break;
                        case "s":
                        case "submit":
                            System.out.println("Enter ID of task:");
                            service.markSubmitted(s.nextInt());
                            s.nextLine();
                            break;

                        default:
                            break;
                    }

               // }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}