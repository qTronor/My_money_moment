package com.example.my_mone_moment.data;
import java.util.ArrayList;

public class Constants {
    // ArrayList and return the ArrayList
    public static ArrayList<Operation> getOperationData()
    {
        // create an ArrayList of type Employee class
        ArrayList<Operation> opList
                = new ArrayList<Operation>();
        Operation op1 = new Operation("Gym",
                "20000", "22/12/2002");
        opList.add(op1);

        Operation op2 = new Operation("Grocery",
                "3000", "22/12/2002");
        opList.add(op1);
        Operation op3 = new Operation("Gym",
                "20000", "22/12/2002");
        opList.add(op1);

        Operation op4 = new Operation("Grocery",
                "3000", "22/12/2002");
        opList.add(op1);

        Operation op5 = new Operation("Gym",
                "20000", "22/12/2002");
        opList.add(op1);

        Operation op6 = new Operation("Grocery",
                "3000", "22/12/2002");

        Operation op7 = new Operation("Gym",
                "20000", "22/12/2002");
        opList.add(op1);

        Operation op8 = new Operation("Grocery",
                "3000", "22/12/2002");

        return opList;
    }
}
