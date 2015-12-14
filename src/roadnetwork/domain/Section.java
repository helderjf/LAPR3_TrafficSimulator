/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Section {
    
    private int id;
    private Node beginningNode;
    private Node endingNode;
    private String typology;
    private String direction;
    private double toll;
    private WindDirection windDirection;

    /**
     * 
     */
    public Section()
    {
        this.id = 0;
        this.beginningNode = null;
        this.endingNode = null;
        this.direction = "";
        this.toll = 0;
        this.typology = "";
        this.windDirection = null;
        
    }
    
    /**
     * 
     * @param id id of section
     * @param beginningNode beginningNode
     * @param endingNode endingNode
     * @param typology Typology (regular road, urban road/street, express road, controlled-access highway)
     * @param direction Direction (direct – beginning to	end; reverse – end to beginning,	bidirectional)
     * @param toll Toll	(may be	zero)
     * @param windDirection Wind direction (angle) and speed (m/s) relative to section direct direction (probability distributions)
     */
    public Section(int id, Node beginningNode, Node endingNode, String typology, String direction, double toll, WindDirection windDirection) {
        this.id = id;
        this.beginningNode = beginningNode;
        this.endingNode = endingNode;
        this.typology = typology;
        this.direction = direction;
        this.toll = toll;
        this.windDirection = windDirection;
    }
    
    /**
     * 
     * @param s Section copy
     */
    public Section(Section s)
    {
        s.beginningNode = this.beginningNode;
        s.direction = this.direction;
        s.endingNode = this.endingNode;
        s.id = this.id;
        s.toll = this.toll;
        s.typology = this.typology;
        s.windDirection = this.windDirection;
    }

    /**
     * 
     * @return section id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return BeginningNode
     */
    public Node getBeginningNode() {
        return beginningNode;
    }
    
    /**
     * 
     * @param beginningNode beginningNode
     */
    public void setBeginningNode(Node beginningNode) {
        this.beginningNode = beginningNode;
    }

    /**
     * 
     * @return EndingNode
     */
    public Node getEndingNode() {
        return endingNode;
    }

    /**
     * 
     * @param endingNode endingNode
     */
    public void setEndingNode(Node endingNode) {
        this.endingNode = endingNode;
    }

    /**
     * 
     * @return Typology
     */
    public String getTypology() {
        return typology;
    }

    /**
     * 
     * @param typology Typology
     */
    public void setTypology(String typology) {
        this.typology = typology;
    }

    /**
     * 
     * @return direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 
     * @param direction direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 
     * @return toll
     */
    public double getToll() {
        return toll;
    }

    /**
     * 
     * @param toll toll
     */
    public void setToll(double toll) {
        this.toll = toll;
    }

    /**
     * 
     * @return windDirection
     */
    public WindDirection getWindDirection() {
        return windDirection;
    }

    /**
     * 
     * @param windDirection windDirection
     */
    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.beginningNode);
        hash = 11 * hash + Objects.hashCode(this.endingNode);
        hash = 11 * hash + Objects.hashCode(this.typology);
        hash = 11 * hash + Objects.hashCode(this.direction);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.toll) ^ (Double.doubleToLongBits(this.toll) >>> 32));
        hash = 11 * hash + Objects.hashCode(this.windDirection);
        return hash;
    }

    /**
     * 
     * @param obj object
     * @return result
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.beginningNode, other.beginningNode)) {
            return false;
        }
        if (!Objects.equals(this.endingNode, other.endingNode)) {
            return false;
        }
        if (!Objects.equals(this.typology, other.typology)) {
            return false;
        }
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        if (Double.doubleToLongBits(this.toll) != Double.doubleToLongBits(other.toll)) {
            return false;
        }
        if (!Objects.equals(this.windDirection, other.windDirection)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return Section string
     */
    @Override
    public String toString() {
        return "Section{" + "id=" + id + ", beginningNode=" + beginningNode + ", endingNode=" + endingNode + ", typology=" + typology + ", direction=" + direction + ", toll=" + toll + ", windDirection=" + windDirection + '}';
    }
    
    
}
