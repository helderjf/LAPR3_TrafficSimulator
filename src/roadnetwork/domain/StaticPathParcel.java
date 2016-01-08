/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author josemiranda
 */
public class StaticPathParcel {
    
    private Section m_section;
    private SimDirection m_direction;
    
    public StaticPathParcel(Section section, SimDirection direction){
        m_section=section;
        m_direction=direction;
    }
    /**
     * @return the m_section
     */
    public Section getSection() {
        return m_section;
    }
    
    /**
     * @return the m_direction
     */
    public SimDirection getDirection() {
        return m_direction;
    }
    
    /**
     * @param m_section the m_section to set
     */
    public void setSection(Section m_section) {
        this.m_section = m_section;
    }
    
    /**
     * @param m_direction the m_direction to set
     */
    public void setDirection(SimDirection m_direction) {
        this.m_direction = m_direction;
    }
    
}
