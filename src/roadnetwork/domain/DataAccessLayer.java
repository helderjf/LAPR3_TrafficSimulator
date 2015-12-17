/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author josemiranda
 */
public class DataAccessLayer {

    public ArrayList<String> getProjectsIDList() {
        ArrayList<String> pidl = new ArrayList<>();
        //TODO call procedure

        //dummy
        String p1 = "project 1 - Porto Centro";
        String p2 = "project 2 - Porto Norte";
        String p3 = "project 2 - Porto Este";
        pidl.add(p1);
        pidl.add(p2);
        pidl.add(p3);
        return pidl;
    }



    public boolean makeProjectActive() {
        //TODO load project

        return true;
    }

}
