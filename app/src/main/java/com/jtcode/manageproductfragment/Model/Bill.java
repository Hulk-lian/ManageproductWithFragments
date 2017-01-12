package com.jtcode.manageproductfragment.Model;


import java.util.ArrayList;
import java.util.Date;

public class Bill {

    int id;
    int idPharmacy;
    Date date;
    StatusBill status;
    ArrayList<BillLine> lines;
}
