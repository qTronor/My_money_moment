package com.example.my_mone_moment.data;
import java.util.ArrayList;

public class Constants {
    // ArrayList and return the ArrayList
    public static ArrayList<Operation> getOperationData()
    {
        // create an ArrayList of type Employee class
        ArrayList<Operation> opList
                = new ArrayList<Operation>();
        Operation op1 = new Operation(1,"Gym",
                20000, "22/12/2002", true);
        opList.add(op1);

        Operation op2 = new Operation(2, "Grocery",
                3000, "22/12/2002", true);
        opList.add(op2);

        Operation op3 = new Operation(3,"Gym",
                20000, "22/12/2002", true);
        opList.add(op3);

        Operation op4 = new Operation(4,"Grocery",
                3000, "22/12/2002", true);
        opList.add(op4);

        Operation op5 = new Operation(5,"Gym",
                20000, "22/12/2002", true);
        opList.add(op5);

        Operation op6 = new Operation(6,"Grocery",
                3000, "22/12/2002", true);
        opList.add(op6);


        return opList;
    }
}
