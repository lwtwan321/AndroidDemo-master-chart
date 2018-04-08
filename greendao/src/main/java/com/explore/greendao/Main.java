package com.explore.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.xishiwi.power.sml");
        addUserBean(schema);

        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }
    private static void addUserBean(Schema schema) {
        Entity note = schema.addEntity("User");
        note.addStringProperty("user_id").primaryKey();
        note.addStringProperty("user_name");
        note.addStringProperty("tk");
        note.addStringProperty("user_info");

    }
}
