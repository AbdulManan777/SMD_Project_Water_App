package com.ass2.water_reminderapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class user_required_water_info {
    int no_of_water_glasses;
    String Age;
    String gender;
    Calendar water_time;
    int index;


    public user_required_water_info() {
        this.no_of_water_glasses = 0;
        this.Age = "0"; //by default
        this.gender = "";
    }
    public void user_required_water_infoAdd(String age, String gender ) {
        this.Age = age;
        this.gender = gender;

        if(gender.equals("Male")) {
            if (Integer.parseInt(Age) > 18 && Integer.parseInt(Age) <= 30)
                this.no_of_water_glasses = 15;
            else if(Integer.parseInt(Age) > 10 && Integer.parseInt(Age) <= 18)
                this.no_of_water_glasses = 12;
        }
        else if(gender.equals("Female")){
            if (Integer.parseInt(Age) > 18 && Integer.parseInt(Age) <= 30)
                this.no_of_water_glasses = 12;
            else if(Integer.parseInt(Age) > 10 && Integer.parseInt(Age) <= 18)
                this.no_of_water_glasses = 8;
        }


    }

    public void setCalender(int i, Calendar c){
        this.water_time=c;
        this.index=i;

    }

    public int getIndex(){
        return this.index;
    }



    public int getNo_of_water_glasses() {
        return no_of_water_glasses;
    }

    public String getTime(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh-mm a");

        return simpleDateFormat.format(this.water_time.getTime());
    }

    public String getAge() {
        return Age;
    }

    public String getGender() {
        return gender;
    }
}
