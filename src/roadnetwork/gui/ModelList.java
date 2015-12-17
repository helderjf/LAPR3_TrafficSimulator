/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Helder Faria
 */
public class ModelList<T> extends DefaultListModel<T> {

    public boolean setItem(T item) {

        removeAllElements();
        this.addElement(item);
        return true;
    }

    public boolean setItems(List<T> lst) {

        removeAllElements();
        for (T it : lst) {
            this.addElement(it);
        }
        return true;
    }

    public boolean addItems(List<T> lst) {

        for (T it : lst) {
            this.addElement(it);
        }
        return true;
    }
    

    public List<T> getList() {
        List<T> lst = new ArrayList<>();
        for (int i = 0; i < this.getSize(); i++) {
            lst.add(this.get(i));
        }

        return lst;
    }

}
