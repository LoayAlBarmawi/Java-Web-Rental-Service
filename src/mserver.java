import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mserver {
      private static ServerSocket server;

    private static int port = 9876;
     static String isadmin ="";
        static int userid=0;
    static boolean login(String user, String pass) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from users where pass='" + pass + "' and username='" + user + "'");
            if (res.next()) {
                isadmin =res.getString("type");
                userid =Integer.parseInt(res.getString("id"));
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
 static boolean removecustomer(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from users where id='" + id +  "'");
            res.next();
            if("1".equals(res.getString("type"))||"0".equals(res.getString("type"))){
                return false;
            }
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
            stm.executeUpdate("INSERT INTO `rentals`.`cars` (`type`, `id`, `vin`, `platenum`, `seats`, `maker`, `model`, `year`, `odometer`, `rented`, `available`,`branch`) VALUES ('" + type + "','" + id + "','" + vin + "','" + platenum + "','" + seats + "','" + model + "','" + maker + "','" + year + "','" + odometer + "','" + rented + "','" + available +"','"+branch+ "');");

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
            String r=res.getString("rented");
            if("1".equals(r)){
                return false;
            }
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
static boolean checkout(int oid) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE rentals.orders SET lefttopay='" + 0 + "' WHERE oid = '" + oid + "';");
            ResultSet res = stm.executeQuery("select * from rentals.orders where oid='" + oid + "';");
            res.next();
            String cid = res.getString("cid");
            stm.executeUpdate("UPDATE rentals.cars SET rented='" + 0 + "' WHERE id = '" + cid + "';");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
static boolean acceptdeposit(int oid,float pay) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
             ResultSet res = stm.executeQuery("select * from rentals.orders where oid='" + oid + "';");
            res.next();
            String left = res.getString("cost");
            stm.executeUpdate("UPDATE rentals.orders SET lefttopay='" + (Float.parseFloat(left)-pay) + "' WHERE oid = '" + oid + "';");

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
            String r=res.getString("rented");
            if("1".equals(r)){
                return false;
            }
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
    static String showcars(){
        String result="";
         try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from rentals.cars where rented=0 and available=1;");
            while(res.next()){
            result+=res.getString("seats")+" seats "+res.getString("maker")+" "+res.getString("model")+" "+res.getString("year")+"\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "no cars available";
   
        }
         return result;
    }
        static String searchusers(String info){
        String result="";
         try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from rentals.users where fname='"+info+"' or phone='"+info+"';");
            while(res.next()){
            result+="username: "+res.getString("username")+" full name:"+res.getString("fname")+" "+res.getString("lname")+" phone:"+res.getString("phone")+" email:"+res.getString("email")+" active user:"+res.getString("active")+" address:"+res.getString("address")+"\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "no users available";
   
        }
         return result;
    }
        static String orderhistory(String info){
        String result="";
         try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals", "root", "root@PSUT");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from rentals.orders where uid='"+info+"' or cid='"+info+"';");
            while(res.next()){
            result+="order id: "+res.getString("oid")+" user id:"+res.getString("uid")+" car id:"+res.getString("cid")+" cost:"+res.getString("cost")+" days:"+res.getString("length")+" distance (km):"+res.getString("distance")+"\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "no orders available";
   
        }
         return result;
    }
    public static void main(String[] args) throws IOException{
        
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server started");
       while (true){
        Socket con = ss.accept();        
        ClientHandler x;
            x = (new ClientHandler(con));
        Thread t = new Thread(x);        
        t.start();
        }
    }

 static class ClientHandler implements Runnable{
Socket con ;
 
    public ClientHandler(Socket socket) {
        this.con = socket;
    }

    @Override
    public void run() {
    try {
        execute();
    } catch (IOException ex) {
        Logger.getLogger(mserver.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(mserver.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

//    @Override
@SuppressWarnings("empty-statement")
    public void execute() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(con.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(con.getOutputStream());

            String message = (String) ois.readObject();

            System.out.println("Message Received: " + message);
            if(message.equals("1")){
                String user = (String) ois.readObject();
                    
                String pass = (String) ois.readObject();
                while(!login(user,pass)){
                    oos.writeObject("f");
                    user = (String) ois.readObject();
                    
                 pass = (String) ois.readObject();
                }
                 oos.writeObject("t");
            }
            if (message.equals("2")) {
                     System.out.println("Message Received: " + message);
                    String username = (String) ois.readObject();
                    String fname = (String) ois.readObject();
                    String lname = (String) ois.readObject();
                    String phone = (String) ois.readObject();
                    String address = (String) ois.readObject();
                    String email = (String) ois.readObject();

                    int active = Integer.parseInt((String)ois.readObject());
                
                    

                    String license = (String) ois.readObject();
                    String pass = (String) ois.readObject();
                    if (adduser(username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        oos.writeObject("user created successfully");

                    } else {
                        oos.writeObject("user not created");
                    }
                }
      oos.writeObject(isadmin);
           String sel=" ";
      while("0".equals(isadmin)){
          sel=(String) ois.readObject();  
          if("1".equals(sel)){  
                    String username = (String) ois.readObject();
                    String fname = (String) ois.readObject();
                    String lname = (String) ois.readObject();
                    String phone = (String) ois.readObject();
                    String address = (String) ois.readObject();
                    String email = (String) ois.readObject();

                    int active = Integer.parseInt((String)ois.readObject());
                
                    int type = Integer.parseInt((String)ois.readObject());

                    String license = (String) ois.readObject();
                    String pass = (String) ois.readObject();
                    if (adduser(username, fname, lname, phone, address, email, active, type, license, pass)) {
                        oos.writeObject("user created successfully");

                    } else {
                        oos.writeObject("user not created");
                    }}
                    if("2".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removeuser(id)) {
                        oos.writeObject("user removed successfully");

                    } else {
                        oos.writeObject("user not removed");
                    }
          }
                   if("3".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 String username = (String) ois.readObject();
                  String fname = (String) ois.readObject();
                 String lname = (String) ois.readObject();
                 String phone = (String) ois.readObject();
                 String address = (String) ois.readObject();
                 String email = (String) ois.readObject();
                 int active = Integer.parseInt((String)ois.readObject());
                 int type = Integer.parseInt((String)ois.readObject());
                 String license = (String) ois.readObject();
                 String pass = (String) ois.readObject();
                    if (modifyuser(id,username, fname, lname, phone, address, email, active, type, license, pass)) {
                        oos.writeObject("user updated successfully");

                    } else {
                        oos.writeObject("user not updated");
                    }
          }
           if("4".equals(sel)){
                 
                    int type = Integer.parseInt((String) ois.readObject());
      
                    int id = Integer.parseInt((String) ois.readObject());
                    String vin = (String) ois.readObject();
                    int platenum = Integer.parseInt((String) ois.readObject());
                   
                    int seats = Integer.parseInt((String) ois.readObject());
               
                    String maker = (String) ois.readObject();
                    String model = (String) ois.readObject();
                    int year = Integer.parseInt((String) ois.readObject());
                    int odometer = Integer.parseInt((String) ois.readObject());
                    int rented = Integer.parseInt((String) ois.readObject());
                  
                    int available = Integer.parseInt((String) ois.readObject());
                 
                     int branch = Integer.parseInt((String) ois.readObject());
                
                    if (addcar(type, id, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        oos.writeObject("car added successfully");

                    } else {
                        oos.writeObject("car not added");
                    }
          }
                     if("5".equals(sel)){
                     int id = Integer.parseInt((String) ois.readObject());
                    int type = Integer.parseInt((String) ois.readObject());
      
                
                    String vin = (String) ois.readObject();
                    int platenum = Integer.parseInt((String) ois.readObject());
                   
                    int seats = Integer.parseInt((String) ois.readObject());
               
                    String maker = (String) ois.readObject();
                    String model = (String) ois.readObject();
                    int year = Integer.parseInt((String) ois.readObject());
                    int odometer = Integer.parseInt((String) ois.readObject());
                    int rented = Integer.parseInt((String) ois.readObject());
                  
                    int available = Integer.parseInt((String) ois.readObject());
                 
                     int branch = Integer.parseInt((String) ois.readObject());
                
                    if (modifycar( id,type, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        oos.writeObject("car updated successfully");

                    } else {
                        oos.writeObject("car not updated");
                    }
          }
                                  if("6".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removecar(id)) {
                        oos.writeObject("car removed successfully");

                    } else {
                        oos.writeObject("car not removed");
                    }
          }
                        if("7".equals(sel)){
                 
                     int oid = Integer.parseInt((String) ois.readObject());
                    int uid = Integer.parseInt((String) ois.readObject());
      
                
                   
                    int cid = Integer.parseInt((String) ois.readObject());
                   
                    int length = Integer.parseInt((String) ois.readObject());
               
                  int odometer = Integer.parseInt((String) ois.readObject());
    
                    int distance = Integer.parseInt((String) ois.readObject());
                  

                
                    if (addorder(oid,uid,cid,length,odometer,distance)) {
                        oos.writeObject("order added successfully");

                    } else {
                        oos.writeObject("order not added");
                    }
          }
                             if("8".equals(sel)){
                   
                     int oid = Integer.parseInt((String) ois.readObject());
                    int uid = Integer.parseInt((String) ois.readObject());
      
                
                   
                    int cid = Integer.parseInt((String) ois.readObject());
                   
                    int length = Integer.parseInt((String) ois.readObject());
               
                  int odometer = Integer.parseInt((String) ois.readObject());
    
                    int distance = Integer.parseInt((String) ois.readObject());
                  

                
                    if (modifyorder(oid,uid,cid,length,odometer,distance)) {
                        oos.writeObject("order updated successfully");

                    } else {
                        oos.writeObject("order not updated");
                    }
          }    
                 if("9".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removeorder(id)) {
                        oos.writeObject("order removed successfully");

                    } else {
                        oos.writeObject("order not removed");
                    }
          }
                 if("10".equals(sel)){
              
                  int cat=Integer.parseInt((String) ois.readObject());
                  String catname = (String) ois.readObject();
                  float pk=Float.parseFloat((String) ois.readObject());
                  int pd=Integer.parseInt((String) ois.readObject());
                    if (modifycategory(cat,catname,pk,pd)) {
                        oos.writeObject("category updated successfully");

                    } else {
                        oos.writeObject("category not updated");
                    }
          }
                 if("11".equals(sel)){
                 int cat=Integer.parseInt((String) ois.readObject());
                 if (removecat(cat)) {
                        oos.writeObject("category deleted successfully");

                    } else {
                        oos.writeObject("category not deleted");
                    }
                 }
                 if("12".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 if (confirm(oid)) {
                        oos.writeObject("order payment confirmed successfully");

                    } else {
                        oos.writeObject("order payment not confirmed");
                    }
                 }
                 if("14".equals(sel)){
             String info = (String) ois.readObject();
            String r=searchusers(info);
            oos.writeObject(r);
        }
        if("15".equals(sel)){
             String info = (String) ois.readObject();
            String r=orderhistory(info);
            oos.writeObject(r);
        }
        if("16".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 if (checkout(oid)) {
                        oos.writeObject("checkout confirmed successfully");

                    } else {
                        oos.writeObject("checkout not confirmed");
                    }
                 }
       if("13".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 float pay=Float.parseFloat((String) ois.readObject());
                 if (acceptdeposit(oid,pay)) {
                        oos.writeObject("deposit confirmed successfully");

                    } else {
                        oos.writeObject("deposit not confirmed");
                    }
                 }
          if(sel.equalsIgnoreCase("17")) break;
      }
       while("1".equals(isadmin)){
          sel=(String) ois.readObject();  
          if("1".equals(sel)){  
                    String username = (String) ois.readObject();
                    String fname = (String) ois.readObject();
                    String lname = (String) ois.readObject();
                    String phone = (String) ois.readObject();
                    String address = (String) ois.readObject();
                    String email = (String) ois.readObject();

                    int active = Integer.parseInt((String)ois.readObject());
                
                

                    String license = (String) ois.readObject();
                    String pass = (String) ois.readObject();
                    if (adduser(username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        oos.writeObject("user created successfully");

                    } else {
                        oos.writeObject("user not created");
                    }}
                    if("2".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removecustomer(id)) {
                        oos.writeObject("user removed successfully");

                    } else {
                        oos.writeObject("user not removed");
                    }
          }
                   if("3".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 String username = (String) ois.readObject();
                  String fname = (String) ois.readObject();
                 String lname = (String) ois.readObject();
                 String phone = (String) ois.readObject();
                 String address = (String) ois.readObject();
                 String email = (String) ois.readObject();
                 int active = Integer.parseInt((String)ois.readObject());
  
                 String license = (String) ois.readObject();
                 String pass = (String) ois.readObject();
                    if (modifyuser(id,username, fname, lname, phone, address, email, active, 2, license, pass)) {
                        oos.writeObject("user updated successfully");

                    } else {
                        oos.writeObject("user not updated");
                    }
          }
           if("4".equals(sel)){
                 
                    int type = Integer.parseInt((String) ois.readObject());
      
                    int id = Integer.parseInt((String) ois.readObject());
                    String vin = (String) ois.readObject();
                    int platenum = Integer.parseInt((String) ois.readObject());
                   
                    int seats = Integer.parseInt((String) ois.readObject());
               
                    String maker = (String) ois.readObject();
                    String model = (String) ois.readObject();
                    int year = Integer.parseInt((String) ois.readObject());
                    int odometer = Integer.parseInt((String) ois.readObject());
                    int rented = Integer.parseInt((String) ois.readObject());
                  
                    int available = Integer.parseInt((String) ois.readObject());
                 
                     int branch = Integer.parseInt((String) ois.readObject());
                
                    if (addcar(type, id, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        oos.writeObject("car added successfully");

                    } else {
                        oos.writeObject("car not added");
                    }
          }
                     if("5".equals(sel)){
                     int id = Integer.parseInt((String) ois.readObject());
                    int type = Integer.parseInt((String) ois.readObject());
      
                
                    String vin = (String) ois.readObject();
                    int platenum = Integer.parseInt((String) ois.readObject());
                   
                    int seats = Integer.parseInt((String) ois.readObject());
               
                    String maker = (String) ois.readObject();
                    String model = (String) ois.readObject();
                    int year = Integer.parseInt((String) ois.readObject());
                    int odometer = Integer.parseInt((String) ois.readObject());
                    int rented = Integer.parseInt((String) ois.readObject());
                  
                    int available = Integer.parseInt((String) ois.readObject());
                 
                     int branch = Integer.parseInt((String) ois.readObject());
                
                    if (modifycar( id,type, vin, platenum, seats, model, maker, year, odometer, rented, available,branch)) {
                        oos.writeObject("car updated successfully");

                    } else {
                        oos.writeObject("car not updated");
                    }
          }
                                  if("6".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removecar(id)) {
                        oos.writeObject("car removed successfully");

                    } else {
                        oos.writeObject("car not removed");
                    }
          }
                        if("7".equals(sel)){
                 
                     int oid = Integer.parseInt((String) ois.readObject());
                    int uid = Integer.parseInt((String) ois.readObject());
      
                
                   
                    int cid = Integer.parseInt((String) ois.readObject());
                   
                    int length = Integer.parseInt((String) ois.readObject());
               
                  int odometer = Integer.parseInt((String) ois.readObject());
    
                    int distance = Integer.parseInt((String) ois.readObject());
                  

                
                    if (addorder(oid,uid,cid,length,odometer,distance)) {
                        oos.writeObject("order added successfully");

                    } else {
                        oos.writeObject("order not added");
                    }
          }
                             if("8".equals(sel)){
                   
                     int oid = Integer.parseInt((String) ois.readObject());
                    int uid = Integer.parseInt((String) ois.readObject());
      
                
                   
                    int cid = Integer.parseInt((String) ois.readObject());
                   
                    int length = Integer.parseInt((String) ois.readObject());
               
                  int odometer = Integer.parseInt((String) ois.readObject());
    
                    int distance = Integer.parseInt((String) ois.readObject());
                  

                
                    if (modifyorder(oid,uid,cid,length,odometer,distance)) {
                        oos.writeObject("order updated successfully");

                    } else {
                        oos.writeObject("order not updated");
                    }
          }    
                 if("9".equals(sel)){
                  int id=Integer.parseInt((String) ois.readObject());
                 
                    if (removeorder(id)) {
                        oos.writeObject("order removed successfully");

                    } else {
                        oos.writeObject("order not removed");
                    }
          }
                 if("10".equals(sel)){
              
                  int cat=Integer.parseInt((String) ois.readObject());
                  String catname = (String) ois.readObject();
                  float pk=Float.parseFloat((String) ois.readObject());
                  int pd=Integer.parseInt((String) ois.readObject());
                    if (modifycategory(cat,catname,pk,pd)) {
                        oos.writeObject("category updated successfully");

                    } else {
                        oos.writeObject("category not updated");
                    }
          }
                 if("11".equals(sel)){
                 int cat=Integer.parseInt((String) ois.readObject());
                 if (removecat(cat)) {
                        oos.writeObject("category deleted successfully");

                    } else {
                        oos.writeObject("category not deleted");
                    }
                 }
                 if("12".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 if (confirm(oid)) {
                        oos.writeObject("order payment confirmed successfully");

                    } else {
                        oos.writeObject("order payment not confirmed");
                    }
                 }
                 if("14".equals(sel)){
             String info = (String) ois.readObject();
            String r=searchusers(info);
            oos.writeObject(r);
        }
        if("15".equals(sel)){
             String info = (String) ois.readObject();
            String r=orderhistory(info);
            oos.writeObject(r);
        }
        if("16".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 if (checkout(oid)) {
                        oos.writeObject("checkout confirmed successfully");

                    } else {
                        oos.writeObject("checkout not confirmed");
                    }
                 }
       if("13".equals(sel)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 float pay=Float.parseFloat((String) ois.readObject());
                 if (acceptdeposit(oid,pay)) {
                        oos.writeObject("deposit confirmed successfully");

                    } else {
                        oos.writeObject("deposit not confirmed");
                    }
                 }
          if(sel.equalsIgnoreCase("17")) break;
      }
       while("2".equals(isadmin)){
           String sel1="";  
            while(!"4".equals(sel1)){
                  sel1=(String) ois.readObject();  
            if("1".equals(sel1)){
            String r=showcars();
            oos.writeObject(r);
        }
             if("2".equals(sel1)){
                 
                    
                    int uid = userid;
                    int cid = Integer.parseInt((String) ois.readObject());
                    int length = Integer.parseInt((String) ois.readObject());
                    int distance = Integer.parseInt((String) ois.readObject());
                    int oid = userid*100+length;
                    if (addorder(oid,uid,cid,length,0,distance)) {
                        oos.writeObject("order added successfully");

                    } else {
                        oos.writeObject("order not added");
                    }  }
                 if("3".equals(sel1)){
                 int oid=Integer.parseInt((String) ois.readObject());
                 if (checkout(oid)) {
                        oos.writeObject("order checkout successful");

                    } else {
                        oos.writeObject("checkout unsuccessful");
                    }
                 }}
            if(sel1.equalsIgnoreCase("4")) break;
       }
        if(sel.equalsIgnoreCase("17")) {
          ois.close();
            oos.close();
            con.close();
            };

            ois.close();
            oos.close();
            con.close();

         
            
        }

    }

}