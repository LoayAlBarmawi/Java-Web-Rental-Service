import java.io.*;
import java.net.*;
import java.util.Scanner;


public class home {
public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
 Scanner scanner = new Scanner(System.in);
 

            socket = new Socket(host.getHostName(), 9876);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Sending request to Socket Server");
             String c="0";
        System.out.println("1)login\n2)register");
        c=scanner.nextLine();
        oos.writeObject(c);
        if(c.equals("1")){
        System.out.println("enter username and password");
             oos.writeObject(scanner.nextLine());
             oos.writeObject(scanner.nextLine());
        while ("f".equals((String)ois.readObject())) {
            System.out.println("wrong username/password");
            System.out.println("enter username and password");
             oos.writeObject(scanner.nextLine());
             oos.writeObject(scanner.nextLine());
        }}
        if(c.equals("2")){
             System.out.println("enter username,first name,last name,phone number,address,email,active user,license number,password");
                    
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());
          oos.writeObject(scanner.nextLine());

            System.out.println("input done");
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            System.out.println("enter username and password");
             oos.writeObject(scanner.nextLine());
             oos.writeObject(scanner.nextLine());
        while ("f".equals((String)ois.readObject())) {
            System.out.println("wrong username/password");
            System.out.println("enter username and password");
             oos.writeObject(scanner.nextLine());
             oos.writeObject(scanner.nextLine());
        }
        }
        String sel=" ";
         String isadmin=(String) ois.readObject();
         if("0".equals(isadmin)){
             System.out.println("welcome admin!");
             while(!"17".equals(sel)){
             System.out.println("enter selection:\n1)create account\n2)delete account\n3)modify account\n4)add car\n5)modify car\n6)delete car\n7)add order\n8)modify order\n9)delete order\n10)modify car category/n11)delete car category\n12)confirm order payment\n13)accept deposit\n14)search user\n15)get order history\n16)confirm checkout\n17)exit\n ");
                        sel=scanner.nextLine();
                       oos.writeObject(sel);
                       if("1".equals(sel)){
                           System.out.println("enter username,first name,last name,phone number,address,email,active user,type(admin=0.employee=1,customer=2),license number,password");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());

                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("2".equals(sel)){
                           System.out.println("enter id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                       if("3".equals(sel)){
                           System.out.println("enter id,username,first name,last name,phone number,address,email,active user,type(admin=0.employee=1,customer=2),license number,password");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());

                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("4".equals(sel)){
                           System.out.println("enter type,id,vin,plate number,number of seats,model name,maker name,year,odometer reading,rented,available,branch");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());


                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                         if("5".equals(sel)){
                           System.out.println("enter id,type,vin,plate number,number of seats,model name,maker name,year,odometer reading,rented,available,branch");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());


                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                       if("6".equals(sel)){
                           System.out.println("enter car id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("7".equals(sel)){
                            System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                      System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                        }
                           if("8".equals(sel)){
                            System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                     System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                           }
                           if("9".equals(sel)){
                           System.out.println("enter order id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                           if("10".equals(sel)){
                                   System.out.println("enter category number,name,price per km,price per day");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                           }
                             if("11".equals(sel)){
                           System.out.println("enter category id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                              if("12".equals(sel)){
                           System.out.println("enter order id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                              if("14".equals(sel)){
                                    System.out.println("enter user's first name or phone number");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("users: \n" + message);
                              }
                               if("15".equals(sel)){
                                    System.out.println("enter car id or user id");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("orders: \n" + message);
                              }
                               if("16".equals(sel)){
                                    System.out.println("enter order id");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("message: \n" + message);
                              }
                                     if("13".equals(sel)){
                                    System.out.println("enter order id and payed amount");
                                  oos.writeObject(scanner.nextLine());
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("message: \n" + message);
                              }


         }

         }
             if("1".equals(isadmin)){
             System.out.println("welcome employee!");
             while(!"17".equals(sel)){
             System.out.println("Enter selection:\n1)create customer account\n2)delete customer account\n3)modify customer account\n4)add car\n5)modify car\n6)delete car\n7)add order\n8)modify order\n9)delete order\n10)modify car category/n11)delete car category\n12)confirm order payment\n13)accept deposit\n14)search user\n15)get order history\n16)confirm checkout\n17)exit\n ");
                        sel=scanner.nextLine();
                       oos.writeObject(sel);
                       if("1".equals(sel)){
                           System.out.println("enter username,first name,last name,phone number,address,email,active user,license number,password");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());

                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("2".equals(sel)){
                           System.out.println("enter id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                       if("3".equals(sel)){
                           System.out.println("enter id,username,first name,last name,phone number,address,email,active user,license number,password");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
           
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());

                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("4".equals(sel)){
                           System.out.println("enter type,id,vin,plate number,number of seats,model name,maker name,year,odometer reading,rented,available,branch");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());


                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                         if("5".equals(sel)){
                           System.out.println("enter id,type,vin,plate number,number of seats,model name,maker name,year,odometer reading,rented,available,branch");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());


                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                       if("6".equals(sel)){
                           System.out.println("enter car id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                        if("7".equals(sel)){
                            System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                      System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                        }
                           if("8".equals(sel)){
                            System.out.println("enter order id,user id,car id,length in days,odometer reading after,distance of trip");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                     System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                           }
                           if("9".equals(sel)){
                           System.out.println("enter order id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                           if("10".equals(sel)){
                                   System.out.println("enter category number,name,price per km,price per day");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                           }
                             if("11".equals(sel)){
                           System.out.println("enter category id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                              if("12".equals(sel)){
                           System.out.println("enter order id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                              if("14".equals(sel)){
                                    System.out.println("enter user's first name or phone number");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("users: \n" + message);
                              }
                               if("15".equals(sel)){
                                    System.out.println("enter car id or user id");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("orders: \n" + message);
                              }
                               if("16".equals(sel)){
                                    System.out.println("enter order id");
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("message: \n" + message);
                              }
                                     if("13".equals(sel)){
                                    System.out.println("enter order id and payed amount");
                                  oos.writeObject(scanner.nextLine());
                                  oos.writeObject(scanner.nextLine());
                                   System.out.println("input done");
                                  String message = (String) ois.readObject();
                                  
                                  System.out.println("message: \n" + message);
                              }


         }

         }
               if("2".equals(isadmin)){
               
               System.out.println("welcome customer!");
             while(!"4".equals(sel)){
             System.out.println("enter selection \n1)show cars\n2)book car\n3)checkout\n4)exit\n");
                        sel=scanner.nextLine();
                       oos.writeObject(sel);
                       if("1".equals(sel)){
                        String message = (String) ois.readObject();
                                  
                                  System.out.println("cars: \n" + message);
                       }
               if("2".equals(sel)){
                       System.out.println("enter car id,length in days,distance of trip");
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());
                            oos.writeObject(scanner.nextLine());

                                     System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
                 if("3".equals(sel)){
                                                  System.out.println("enter order id");
                            oos.writeObject(scanner.nextLine());
                  
                          System.out.println("input done");
                          String message = (String) ois.readObject();
                          System.out.println("Message: " + message);
                       }
             
             
             }
               
               }

            ois.close();
            oos.close();

        }
    }

