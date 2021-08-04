package dev;

import java.sql.*;

public class PopulateCredoDB {

    //ID start index to be allocated according to current IDs in database
    public int CarModelIDStart;
    public int CarPartIDStart;
    public int FeatureIDStart;

    //Model entries to be added to db
    public String[][] Models = { {" \"BMW M3\" ", " \"2017\" ", "10"},
                                        {" \"AUDI R8\" ", " \"2013\" ", "9"},
                                        {" \"TESLA MODEL X\" ", " \"2021\" ", "7"},
                                        {" \"PORSCHE TAYCAN\" ", " \"2021\" ", "6"},
                                        {" \"FORD F-150\" ", " \"2018\" ", "2"},
                                        {" \"HONDA CIVIC\" ", " \"2008\" ", "5"},
                                        {" \"BMW X6\" ", " \"2014\" ", "1"},
                                        {" \"MERCEDES CL250\" ", " \"2016\" ", "3"},
                                        {" \"CHEVROLET CAMARO\" ", " \"2001\" ", "5"},
                                        {" \"CORVETTE C8\" ", " \"2020\" ", "4"} };

    //Part entries to be added to db
    public String[][] Parts = {  {" \"Goodyear Ultra Tread\" ", " \"Tyre\" ", "6", "6" },
                                        {" \"Brembo Brake Disk\" ", " \"Brake\" ", "7", "6" },
                                        {" \"Eicher Premium\" ", " \"Brake\" ", "6", "6" },
                                        {" \"Bosch Aerotwin Wipers\" ", " \"Wiper Blade\" ", "4", "4" },
                                        {" \"Modula Ciao Roof Box\" ", " \"Roof Box\" ", "5", "1" },
                                        {" \"Ecide EGM 019 Car Battery\" ", " \"Battery\" ", "8", "10" } };

    //Feature entries to be added to db
    public String[][] Features = {  {" \"Color\" ", " \"Crimson Red\" "},
                                            {" \"Special Edition\" ", " \"Type R Model\" "},
                                            {" \"Warranty\" ", " \"3 Year Warranty\" "},
                                            {" \"Color\" ", " \"Black Gloss\" "},
                                            {" \"Capacity\" ", " \"340ltr\" "},
                                            {" \"Finish\" ", " \"Carbon Fibre Lining\" "},
                                            {" \"Driving Assistance\" ", " \"Self Driving\" "},
                                            {" \"Driving Assistance\" ", " \"Lane Control\" "},
                                            {" \"Special Edition\" ", " \"AMG Model\" "},
                                            {" \"Capacity\" ", " \"850Ccs\" "} };

    //function to populate CarModel table
    public void populateModels() {
        try {

            //Get connection to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:4444/credosoftserver", "root", "password");
            //initialise statement
            Statement myStmt = myConn.createStatement();
            //create result set for count() currently existing in database
            ResultSet myRs = myStmt.executeQuery("SELECT COUNT(1) FROM CarModel");
            //set index CarModelID is to start from for data entries
            while (myRs.next()) {
                this.CarModelIDStart = myRs.getInt(1);
            }

            //Add Carmodels to db
            for (int i = 0; i < 10; i++) {
                int ID = CarModelIDStart + 1;
                this.CarModelIDStart++;
                String[] subArray = this.Models[i];
                String Name = subArray[0];
                String Year = subArray[1];
                String Feature = subArray[2];
                int Feat = Integer.parseInt(Feature);

                //SET INSERT QUERY WITH CURRENT VALUES TO ADD
                String insertquery = "INSERT INTO `CredosoftServer`.`CarModel`" +
                        "(`ModelID`" +
                        ",`ModelName`" +
                        ",`ModelYear`" +
                        ",`FeatureID`)" +
                        "VALUES" +
                        "( " + ID + ", " + Name + ", " + Year + ", " + Feat + " );";

                //execute generated query
                myStmt = myConn.createStatement();
                myStmt.executeUpdate(insertquery);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateParts(){
        try{
            //get connection to db
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:4444/credosoftserver" , "root", "password");
            //initialise statement
            Statement myStmt = myConn.createStatement();
            //create result set for count() currently existing in database
            ResultSet myRs = myStmt.executeQuery("SELECT COUNT(1) FROM CarPart");

            //set index CarPartID is to start from for data entries
            while (myRs.next()) {
                this.CarPartIDStart = myRs.getInt(1);
            }

            //ADD CARPARTS
            for (int x = 0; x < 20; x++) {
                for (int i = 0; i < 5; i++) {
                    int CID = this.CarPartIDStart + 1;
                    this.CarPartIDStart++;
                    String[] subArray = this.Parts[i];
                    String Name = subArray[0];
                    String Type = subArray[1];
                    String MUID = subArray[2];
                    String Feature = subArray[3];


                    int Feat = Integer.parseInt(Feature);
                    int foo = Integer.parseInt(MUID);

                    //SET INSERT QUERY WITH CURRENT VALUES TO ADD
                    String insertquery = "INSERT INTO `CredosoftServer`.`CarPart`" +
                            "(`ID`" +
                            ",`PartName`" +
                            ",`PartType`" +
                            ",`FeatureID`" +
                            ",`ModelID`)" +
                            "VALUES" +
                            "( " + CID + ", " + Name + ", " + Type + ", " + Feat + "," + foo +  " );";

                    //execute generated query
                    myStmt = myConn.createStatement();
                    myStmt.executeUpdate(insertquery);


                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void populateFeatures(){
        try{
            //get connection to db
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:4444/credosoftserver" , "root", "password");
            //initialise statement
            Statement myStmt = myConn.createStatement();
            //create result set for count() currently existing in database
            ResultSet myRs = myStmt.executeQuery("SELECT COUNT(1) FROM Features");

            //set index CarPartID is to start from for data entries
            while (myRs.next()) {
                this.FeatureIDStart = myRs.getInt(1);
            }

            //ADD FEATURES
            for (int x = 0; x < 5; x++) {
                for (int i = 0; i < 10; i++) {
                    int ID = this.FeatureIDStart + 1;
                    this.FeatureIDStart++;

                    String[] subArray = this.Features[i];
                    String Name = subArray[0];
                    String Desc = subArray[1];

                    //SET INSERT QUERY WITH CURRENT VALUES TO ADD
                    String insertquery = "INSERT INTO `CredosoftServer`.`Features`" +
                            "(`FeatureID`" +
                            ",`FeatureName`" +
                            ",`FeatureDescription`)" +
                            "VALUES" +
                            "( " + ID + ", " + Name + ", " + Desc + " );";

                    //execute generated query
                    myStmt = myConn.createStatement();
                    myStmt.executeUpdate(insertquery);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PopulateCredoDB db = new PopulateCredoDB();
        db.populateFeatures();
        db.populateModels();
        db.populateParts();
    }
}
