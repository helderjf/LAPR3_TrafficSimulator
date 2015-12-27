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
public class Section {
    
    
    private int m_pk;
    private String roadName;
    private Junction beginningNode;
    private Junction endingNode;
    private SectionTypology typology;
    private Direction direction;
    private double toll;
    private Wind wind;
    private ArrayList<Segment> segmentsList;

    /**
     * 
     */
    public Section()
    {
        
        this.roadName = ""; 
        this.beginningNode = null;
        this.endingNode = null;
        this.direction = null;
        this.toll = 0;
        this.typology = null;
        this.wind = null;
        this.segmentsList = null;
        
    }

    /**
     * 
     * @param roadId roadName of section
     * @param beginningNode beginningNode
     * @param endingNode endingNode
     * @param typology Typology (regular road, urban road/street, express road, controlled-access highway)
     * @param direction Direction (direct – beginning to	end; reverse – end to beginning,	bidirectional)
     * @param toll Toll	(may be	zero)
     * @param wind Wind direction (angle) and speed (m/s) relative to section direct direction (probability distributions)
     * @param segmentsList segmentsList
     */
    public Section(String roadId, Junction beginningNode, Junction endingNode, SectionTypology typology, Direction direction, double toll, Wind wind, ArrayList<Segment> segmentsList) {
        
        this.roadName = roadId;
        this.beginningNode = beginningNode;
        this.endingNode = endingNode;
        this.typology = typology;
        this.direction = direction;
        this.toll = toll;
        this.wind = wind;
        this.segmentsList = segmentsList;
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
        s.m_pk = this.m_pk;
        s.roadName = this.roadName;
        s.toll = this.toll;
        s.typology = this.typology;
        s.wind = this.wind;
        s.segmentsList = this.segmentsList;
    }

    /**
     * 
     * @return section m_pk
     */
    public int getId() {
        return m_pk;
    }

    /**
     * 
     * @return roadName
     */
    public String getRoadName() {
        return roadName;
    }
    
    

    /**
     * 
     * @return BeginningNode
     */
    public Junction getBeginningNode() {
        return beginningNode;
    }
    
    /**
     * 
     * @param beginningNode beginningNode
     */
    public void setBeginningNode(Junction beginningNode) {
        this.beginningNode = beginningNode;
    }

    /**
     * 
     * @return EndingNode
     */
    public Junction getEndingNode() {
        return endingNode;
    }

    /**
     * 
     * @param endingNode endingNode
     */
    public void setEndingNode(Junction endingNode) {
        this.endingNode = endingNode;
    }

    /**
     * 
     * @return Typology
     */
    public SectionTypology getTypology() {
        return typology;
    }

    /**
     * 
     * @param typology Typology
     */
    public void setTypology(SectionTypology typology) {
        this.typology = typology;
    }

    /**
     * 
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * 
     * @param direction direction
     */
    public void setDirection(Direction direction) {
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
     * @return wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public ArrayList<Segment> getSegmentsList() {
        return segmentsList;
    }

    public void setSegmentsList(ArrayList<Segment> segmentsList) {
        this.segmentsList = segmentsList;
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
        if (this.m_pk != other.m_pk) {
            return false;
        }
        if (!Objects.equals(this.roadName, other.roadName)) {
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
        if (this.direction != other.direction) {
            return false;
        }
        if (Double.doubleToLongBits(this.toll) != Double.doubleToLongBits(other.toll)) {
            return false;
        }
        if (!Objects.equals(this.wind, other.wind)) {
            return false;
        }
        if (!Objects.equals(this.segmentsList, other.segmentsList)) {
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
        return "Section{" + "id=" + m_pk + ", roadId=" + getRoadName() + ", beginningNode=" + beginningNode + ", endingNode=" + endingNode + ", typology=" + typology + ", direction=" + direction + ", toll=" + toll + ", windDirection=" + wind + ", segmentsList=" + segmentsList + '}';
    }

    /**
     * @param roadId the roadName to set
     */
    public void setRoadName(String roadId) {
        this.roadName = roadId;
    }

    SectionTypology getSectionType() {
        return typology;
    }

    public void setPK(int pk) {
        m_pk=pk;
    }

    public int getPK() {
        return m_pk;
    }
    
    

  
}
