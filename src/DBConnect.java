import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DBConnect {

   //variable from the database connection class
    DatabaseConnecton databaseConnecton = new DatabaseConnecton();


    public static void main(String[] args) {


        DBConnect dbConnect = new DBConnect();


        //variable for the menu
        int menu = 0;

        //Scanner to get the user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an option from the menu");

        do {
            //menu
            System.out.println("1.Add Record");
            System.out.println("2.View Record");
            System.out.println("3.Edit Records");
            System.out.println("4.Delete Records");
            System.out.println("5.Exit");
            menu = scanner.nextInt();

            switch(menu){
                case 1:
                    dbConnect.addRecords();
                    break;
                case 2:
                    dbConnect.viewRecords();
                    break;
                case 3:
                    dbConnect.editRecords();
                    break;
                case 4:
                    dbConnect.deleteRecords();
                    break;
                    case 5:
                        System.exit(5);
                        break;
                default:
                    System.out.println("Please enter a valid option");
            }

        }while (menu != 5);


    }
        public void addRecords () {

            Scanner sc = new Scanner(System.in);
            //id
            System.out.println("Please enter your id");
            int id = sc.nextInt();

            //name
            System.out.println("Please enter your first name ");
            String firstName = sc.next();

            //last name
            System.out.println("Please enter your last name");
            String lastName = sc.next();

            //job
            System.out.println("Please enter your job title");
            String jobTitle = sc.next();


            try {


                String query = "insert into workers (ID,First_Name,Last_Name,Job_Title) values(?,?,?,?)";


                //creating the prepared statement
                PreparedStatement preparedStatement = databaseConnecton.con.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, jobTitle);

                //executing the prepared statement
                System.out.println("User has been successfully added ");
            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }

        }

        public void viewRecords() {

            try {

                //retrieving data from the database
                System.out.println("--------------------------- Database Contains --------------------------");
                //query
                String sql = "select * from workers";
                Statement statement = databaseConnecton.con.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {

                    //getting all the details from the database from the columns
                    int id_col = rs.getInt("ID");
                    String first_name = rs.getString("First_Name");
                    String last_name = rs.getString("Last_Name");
                    String job = rs.getString("Job_Title");

                    //presenting them in one string
                    String fullData = id_col + " " + first_name + " " + last_name + " " + job;
                    System.out.println(fullData);
                }


            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }


        }

       public void editRecords(){

        int status = 0;

        //Scanner to get the user input
        Scanner scanner = new Scanner(System.in);
        //user input
           System.out.println("Please enter your First Name");
           String firstName = scanner.next();
           System.out.println("Please enter your Last Name");
           String lastName = scanner.next();
           System.out.println("Please enter your ID");
           int id = scanner.nextInt();

        //query
           String query = "update workers set First_Name = ?, Last_Name = ? where ID = ?";
        //prepared statement
           try {
               PreparedStatement preparedStatement = databaseConnecton.con.prepareStatement(query);
               preparedStatement.setString(1,firstName);
               preparedStatement.setString(2,lastName);
               preparedStatement.setInt(3,id);

               //executing the preparedstatement
              status =  preparedStatement.executeUpdate();

              //checking whether the statement is successful
              if(status == 1){
                  System.out.println("record has been updated successfully");
              }else{
                  System.out.println("record has not updated successfully");
              }


           }catch (SQLException e){
               System.out.println("Got an Exception");
               System.out.println(e.getMessage());
           }



       }

       public void deleteRecords(){

        //int variable for the id
        int id = -1 ;

        //int variable status
           int status = 0;
        //Scanner to get user input
           Scanner scanner = new Scanner(System.in);

           //user input
           System.out.println("Please enter your ID");
           try {
                id = scanner.nextInt();
           }catch (InputMismatchException e){
               System.out.println("Exception has found");
               System.out.println(e.getMessage());
           }
           String query = "delete from workers where ID = ?";



        try {
            //prepared statement
            PreparedStatement preparedStatement = databaseConnecton.con.prepareStatement(query);
            preparedStatement.setInt(1,id);

            //execute the statement
            status = preparedStatement.executeUpdate();

            if(status == 1){
                System.out.println("Record has been deleted successfully");
            }else{
                System.out.println("Record Deletion Failure");
            }
        }catch (SQLException e){
            System.out.println("Exception has found !!!");
            System.out.println(e.getMessage());
        }


       }
    }
