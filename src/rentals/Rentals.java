/*
 * This is phase one of the java project
 */
package rentals;

import java.util.*;
import java.sql.*;

public class Rentals {

    static String isadmin ="";

    static boolean login(String user, String pass) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from users where pass='" + pass + "' and username='" + user + "'");
            if (res.next()) {
                isadmin =res.getString("type");
                System.out.println("Welcome back " + res.getString("fname"));
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    static boolean adduser(String username, String fname, String lname, String phone, String address, String email, int active, int type, String license, String pass) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("INSERT INTO `rentals`.`users` ( `username`, `fname`, `lname`, `phone`, `address`, `email`, `active`, `type`, `license`, `pass`) VALUES ('" + username + "','" + fname + "','" + lname + "','" + phone + "','" + address + "','" + email + "','" + active + "','" + type + "','" + license + "','" + pass + "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean removeuser(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM `rentals`.`users` WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean removecar(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM `rentals`.`cars` WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean removecat(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM `rentals`.`category` WHERE (`cat` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean removeorder(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM `rentals`.`orders` WHERE (`oid` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean addcar(int type, int id, String vin, int platenum, int seats, String maker, String model, int year, int odometer, int rented, int available,int branch) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("INSERT INTO `rentals`.`cars` (`type`, `id`, `vin`, `platenum`, `seats`, `maker`, `model`, `year`, `odometer`, `rented`, `available`,'branch') VALUES ('" + type + "','" + id + "','" + vin + "','" + platenum + "','" + seats + "','" + model + "','" + maker + "','" + year + "','" + odometer + "','" + rented + "','" + available +"','"+branch+ "');");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean addorder(int oid, int uid, int cid, int length, int odometer, int distance) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from rentals.cars where id='" + cid + "';");
            res.next();
            String t = res.getString("type");
            res = stm.executeQuery("select * from rentals.category where cat='" + t + "';");
            res.next();
            int d = res.getInt("dayprice");
            double k = res.getDouble("kmprice");
            double cost = k * distance + d * length;
            stm.executeUpdate("UPDATE `rentals`.`cars` SET odometer='" + odometer + "' WHERE id = '" + cid + "';");
            stm.executeUpdate("INSERT INTO `rentals`.`orders` (`oid`, `uid`, `cid`, `length`, `odometer`, `distance`, `cost`) VALUES ('" + oid + "','" + uid + "','" + cid + "','" + length + "','" + odometer + "','" + distance + "','" + cost + "');");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean modifyuser(int id, String username, String fname, String lname, String phone, String address, String email, int active, int type, String license, String pass) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE `rentals`.`users` SET username='" + username + "', fname= '" + fname + "',lname='" + lname + "' WHERE id = '" + id + "';");
            stm.executeUpdate("UPDATE `rentals`.`users` SET phone='" + phone + "', address= '" + address + "',email='" + email + "' WHERE id = '" + id + "';");
            stm.executeUpdate("UPDATE `rentals`.`users` SET active='" + active + "', type= '" + type + "',license='" + license + "',pass='" + pass + "' WHERE id = '" + id + "';");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean modifycar(int id, int type, String vin, int platenum, int seats, String maker, String model, int year, int odometer, int rented, int available,int branch) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE `rentals`.`cars` SET type='" + type + "', vin= '" + vin + "',platenum='" + platenum +  "',branch='" + branch +"' WHERE id = '" + id + "';");
            stm.executeUpdate("UPDATE `rentals`.`cars` SET seats='" + seats + "', maker= '" + maker + "',model='" + model + "' WHERE id = '" + id + "';");
            stm.executeUpdate("UPDATE `rentals`.`cars` SET year='" + year + "', odometer= '" + odometer + "',rented='" + rented + "',available='" + available + "' WHERE id = '" + id + "';");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean modifycategory(int cat, String name, double kmprice, int dayprice) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE rentals.category SET cat='" + cat + "', name= '" + name + "',kmprice='" + kmprice + "',dayprice='" + dayprice + "' WHERE cat = '" + cat + "';");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean confirm(int oid) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE rentals.orders SET confirmed='" + 1 + "' WHERE oid = '" + oid + "';");
            ResultSet res = stm.executeQuery("select * from rentals.orders where oid='" + oid + "';");
            res.next();
            String cid = res.getString("cid");
            stm.executeUpdate("UPDATE rentals.cars SET rented='" + 1 + "' WHERE id = '" + cid + "';");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static boolean modifyorder(int oid, int uid, int cid, int length, int odometer, int distance) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from rentals.cars where id='" + cid + "';");
            res.next();
            String t = res.getString("type");
            res = stm.executeQuery("select * from rentals.category where cat='" + t + "';");
            res.next();
            int d = res.getInt("dayprice");
            double k = res.getDouble("kmprice");
            double cost = k * distance + d * length;
            stm.executeUpdate("UPDATE `rentals`.`cars` SET odometer='" + odometer + "' WHERE id = '" + cid + "';");
            stm.executeUpdate("UPDATE `rentals`.`orders` SET cid='" + cid + "', uid= '" + uid + "',length='" + length + "' WHERE oid = '" + oid + "';");
            stm.executeUpdate("UPDATE `rentals`.`orders` SET odometer='" + odometer + "', distance= '" + distance + "',cost='" + cost + "' WHERE oid = '" + oid + "';");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c=0;
        System.out.println("1)login\n2)register");
        c=scanner.nextInt();
        scanner.nextLine();
        
        if(c==1){
        System.out.println("enter username and password");
        String user = scanner.nextLine();
        String pass = scanner.nextLine();
        while (!login(user, pass)) {
            System.out.println("wrong username/password");
            System.out.println("enter username and password");
            user = scanner.nextLine();
            pass = scanner.nextLine();
        }}
        if(c==2){
             System.out.println("enter username,first name,last name,phone number,address,email,active user,license number,password");
                    
                    String username = scanner.nextLine();
                    String fname = scanner.nextLine();
                    String lname = scanner.nextLine();
                    String phone = scanner.nextLine();
                    String address = scanner.nextLine();
                    String email = scanner.nextLine();
                    int active = scanner.nextInt();
                    scanner.nextLine();
                    String license = scanner.nextLine();
                    String pass = scanner.nextLine();
                    if (adduser(username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        System.out.println("user created successfully");

                    } else {
                        System.out.println("user not created");
                    }
        }

        int sel = 0;
        if ("0".equals(isadmin)) {
            System.out.println("welcome admin!");

            while (sel != 13) {
                System.out.println("Select command: \n1-add user\n2-remove user\n3-modify user\n4-add car\n5-modify car\n6-modify category\n7-add order\n8-modify order\n9-confirm order payment\n10-delete car\n11-delete order\n12-delete category\n13-exit");
                sel = scanner.nextInt();
                if (sel == 1) {
                    System.out.println("enter username,first name,last name,phone number,address,email,active user,type(0=admin,1=employee,2=user),license number,password");
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    String fname = scanner.nextLine();
                    String lname = scanner.nextLine();
                    String phone = scanner.nextLine();
                    String address = scanner.nextLine();
                    String email = scanner.nextLine();
                    int active = scanner.nextInt();
                    scanner.nextLine();
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    String license = scanner.nextLine();
                    String pass = scanner.nextLine();
                    if (adduser(username, fname, lname, phone, address, email, active, type, license, pass)) {
                        System.out.println("user created successfully");

                    } else {
                        System.out.println("user not created");
                    }
                }
                if (sel == 2) {
                    System.out.println("enter id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removeuser(id)) {
                        System.out.println("user emoved successfully");
                    } else {
                        System.out.println("user not removed");
                    }
                }
                if (sel == 3) {
                    System.out.println("enter id,username,first name,last name,phone number,address,email,active user,type,license number,password");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    String fname = scanner.nextLine();
                    String lname = scanner.nextLine();
                    String phone = scanner.nextLine();
                    String address = scanner.nextLine();
                    String email = scanner.nextLine();
                    int active = scanner.nextInt();
                    scanner.nextLine();
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    String license = scanner.nextLine();
                   String pass = scanner.nextLine();
                    if (modifyuser(id, username, fname, lname, phone, address, email, active, type, license, pass)) {
                        System.out.println("user updated successfully");

                    } else {
                        System.out.println("user not updated");
                    }
                }
                if (sel == 4) {
                    System.out.println("enter type,id,vin,plate number,number of seats,model name,maker name,year,odometer rading,rented,available,branch");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    int platenum = scanner.nextInt();
                    scanner.nextLine();
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    String maker = scanner.nextLine();
                    String model = scanner.nextLine();
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int rented = scanner.nextInt();
                    scanner.nextLine();
                    int available = scanner.nextInt();
                    scanner.nextLine();
                    int branch = scanner.nextInt();
                    scanner.nextLine();

                    if (addcar(type, id, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        System.out.println("car created successfully");

                    } else {
                        System.out.println("car not created");
                    }
                }
                if (sel == 5) {
                    System.out.println("enter id,type,vin,plate number,number of seats,model name,maker name,year,odometer rading,rented,available,branch");

                    int id = scanner.nextInt();
                    scanner.nextLine();
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    int platenum = scanner.nextInt();
                    scanner.nextLine();
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    String maker = scanner.nextLine();
                    String model = scanner.nextLine();
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int rented = scanner.nextInt();
                    scanner.nextLine();
                    int available = scanner.nextInt();
                    scanner.nextLine();
                    int branch = scanner.nextInt();
                    scanner.nextLine();

                    if (modifycar(id, type, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        System.out.println("car updated successfully");

                    } else {
                        System.out.println("car not updated");
                    }
                }
                if (sel == 6) {
                    System.out.println("enter category number,name,price per km,price per day");
                    int cat = scanner.nextInt();
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    double kmprice = scanner.nextDouble();
                    scanner.nextLine();
                    int dayprice = scanner.nextInt();
                    scanner.nextLine();
                    if (modifycategory(cat, name, kmprice, dayprice)) {
                        System.out.println("category updated successfully");
                    } else {
                        System.out.println("category not updated");
                    }
                }
                if (sel == 7) {
                    System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int distance = scanner.nextInt();
                    scanner.nextLine();
                    if (addorder(oid, uid, cid, length, odometer, distance)) {
                        System.out.println("order created successfully");

                    } else {
                        System.out.println("order not created");
                    }
                }
                if (sel == 8) {
                    System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int distance = scanner.nextInt();
                    scanner.nextLine();
                    if (modifyorder(oid, uid, cid, length, odometer, distance)) {
                        System.out.println("order updated successfully");

                    } else {
                        System.out.println("order not updated");
                    }
                }
                if (sel == 9) {
                    System.out.println("enter order id");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    if (confirm(oid)) {
                        System.out.println("order confirmed successfully");

                    } else {
                        System.out.println("order not confirmed");
                    }
                }
                if (sel == 10) {
                    System.out.println("enter car id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removecar(id)) {
                        System.out.println("car emoved successfully");
                    } else {
                        System.out.println("car not removed");
                    }
                }
                if (sel == 11) {
                    System.out.println("enter order id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removeorder(id)) {
                        System.out.println("order emoved successfully");
                    } else {
                        System.out.println("order not removed");
                    }
                }
                if (sel == 12) {
                    System.out.println("enter category number");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removecat(id)) {
                        System.out.println("category removed successfully");
                    } else {
                        System.out.println("category not removed");
                    }
                }
            }
        }
         if ("1".equals(isadmin)) {
            System.out.println("welcome admin!");

            while (sel != 13) {
                System.out.println("Select command: \n1-add user\n2-remove user\n3-modify user\n4-add car\n5-modify car\n6-modify category\n7-add order\n8-modify order\n9-confirm order payment\n10-delete car\n11-delete order\n12-delete category\n13-exit");
                sel = scanner.nextInt();
                if (sel == 1) {
                    System.out.println("enter username,first name,last name,phone number,address,email,active user,license number,password");
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    String fname = scanner.nextLine();
                    String lname = scanner.nextLine();
                    String phone = scanner.nextLine();
                    String address = scanner.nextLine();
                    String email = scanner.nextLine();
                    int active = scanner.nextInt();
                    scanner.nextLine();
         
                    String license = scanner.nextLine();
                    String pass = scanner.nextLine();
                    if (adduser(username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        System.out.println("user created successfully");

                    } else {
                        System.out.println("user not created");
                    }
                }
                if (sel == 2) {
                    System.out.println("enter id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if(id==2){
                    if (removeuser(id)) {
                        System.out.println("user emoved successfully");
                    } else {
                        System.out.println("user not removed");
                    }}
                    else{
                        System.out.print("cannot delete any employee or admin");
                    }
                }
                if (sel == 3) {
                    System.out.println("enter id,username,first name,last name,phone number,address,email,active user,license number,password");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    String fname = scanner.nextLine();
                    String lname = scanner.nextLine();
                    String phone = scanner.nextLine();
                    String address = scanner.nextLine();
                    String email = scanner.nextLine();
                    int active = scanner.nextInt();
                    scanner.nextLine();
                
                    String license = scanner.nextLine();
                   String pass = scanner.nextLine();
                    if (modifyuser(id, username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        System.out.println("user updated successfully");

                    } else {
                        System.out.println("user not updated");
                    }
                }
                if (sel == 4) {
                    System.out.println("enter type,id,vin,plate number,number of seats,model name,maker name,year,odometer rading,rented,available,branch");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    int platenum = scanner.nextInt();
                    scanner.nextLine();
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    String maker = scanner.nextLine();
                    String model = scanner.nextLine();
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int rented = scanner.nextInt();
                    scanner.nextLine();
                    int available = scanner.nextInt();
                    scanner.nextLine();
                     int branch = scanner.nextInt();
                    scanner.nextLine();

                    if (addcar(type, id, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        System.out.println("car created successfully");

                    } else {
                        System.out.println("car not created");
                    }
                }
                if (sel == 5) {
                    System.out.println("enter id,type,vin,plate number,number of seats,model name,maker name,year,odometer rading,rented,available,branch");

                    int id = scanner.nextInt();
                    scanner.nextLine();
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    int platenum = scanner.nextInt();
                    scanner.nextLine();
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    String maker = scanner.nextLine();
                    String model = scanner.nextLine();
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int rented = scanner.nextInt();
                    scanner.nextLine();
                    int available = scanner.nextInt();
                    scanner.nextLine();
                    int branch = scanner.nextInt();
                    scanner.nextLine();

                    if (modifycar(id, type, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        System.out.println("car updated successfully");

                    } else {
                        System.out.println("car not updated");
                    }
                }
                if (sel == 6) {
                    System.out.println("enter category number,name,price per km,price per day");
                    int cat = scanner.nextInt();
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    double kmprice = scanner.nextDouble();
                    scanner.nextLine();
                    int dayprice = scanner.nextInt();
                    scanner.nextLine();
                    if (modifycategory(cat, name, kmprice, dayprice)) {
                        System.out.println("category updated successfully");
                    } else {
                        System.out.println("category not updated");
                    }
                }
                if (sel == 7) {
                    System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int distance = scanner.nextInt();
                    scanner.nextLine();
                    if (addorder(oid, uid, cid, length, odometer, distance)) {
                        System.out.println("order created successfully");

                    } else {
                        System.out.println("order not created");
                    }
                }
                if (sel == 8) {
                    System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int odometer = scanner.nextInt();
                    scanner.nextLine();
                    int distance = scanner.nextInt();
                    scanner.nextLine();
                    if (modifyorder(oid, uid, cid, length, odometer, distance)) {
                        System.out.println("order updated successfully");

                    } else {
                        System.out.println("order not updated");
                    }
                }
                if (sel == 9) {
                    System.out.println("enter order id");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    if (confirm(oid)) {
                        System.out.println("order confirmed successfully");

                    } else {
                        System.out.println("order not confirmed");
                    }
                }
                if (sel == 10) {
                    System.out.println("enter car id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removecar(id)) {
                        System.out.println("car emoved successfully");
                    } else {
                        System.out.println("car not removed");
                    }
                }
                if (sel == 11) {
                    System.out.println("enter order id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removeorder(id)) {
                        System.out.println("order emoved successfully");
                    } else {
                        System.out.println("order not removed");
                    }
                }
                if (sel == 12) {
                    System.out.println("enter category number");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (removecat(id)) {
                        System.out.println("category removed successfully");
                    } else {
                        System.out.println("category not removed");
                    }
                }
            }
        }
         if("2".equals(isadmin)){
             System.out.println("Select command: \n1-navigate cars\n2-book order\n3checkout\n4-exit");
             //naviagte cars
             //book(check if car is available)
             //checkout
         }
    }

}
