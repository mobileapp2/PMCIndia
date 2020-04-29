package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class MyTeamEntity  implements Serializable {

    int start , length , level_id ;
   // String search[value]:

    public MyTeamEntity(int start, int length, int level_id) {
        this.start = start;
        this.length = length;
        this.level_id = level_id;
    }
}
