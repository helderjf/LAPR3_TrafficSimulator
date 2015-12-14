/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Road {
    
    private int id;
    private String name;
    ArrayList<Section> sections;

    /**
     * 
     */
    public Road()
    {
        this.id = 0;
        this.name = "";
        this.sections = null;
    }
    
    /**
     * 
     * @param id id of Road
     * @param name name of Road
     * @param sections sections of Road
     */
    public Road(int id, String name, ArrayList<Section> sections) {
        this.id = id;
        this.name = name;
        this.sections = sections;
    }

    /**
     * 
     * @param id id of Road
     * @param name name of Road
     */
    public Road(int id, String name) {
        this.id = id;
        this.name = name;
        this.sections = null;
    }
    
    /**
     * 
     * @param r road object
     */
    public Road(Road r)
    {
        r.id = id;
        r.name = name;
        r.sections = sections;
    }

    /**
     * 
     * @return id of Road
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id id of Road
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return name of Road
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name name of Road
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return sections of Road
     */
    public ArrayList<Section> getSections() {
        return sections;
    }

    /**
     * 
     * @param sections sections of Road
     */
    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    /**
     * 
     * @param obj object
     * @return result of equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Road other = (Road) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.sections, other.sections)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return Road string 
     */
    @Override
    public String toString() {
        return "Road{" + "id=" + id + ", name=" + name + ", sections=" + sections + '}';
    }
 
    
}
