/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.state;

import roadnetwork.domain.Project;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectStateImpl implements ProjectState {

    protected Project m_project;

    public ProjectStateImpl(Project p) {
        m_project = p;
    }

    public Project getProject() {
        return m_project;
    }

    @Override
    public boolean canImportRoadNetwork() {
        return false;
    }

    @Override
    public boolean canImportVehicles() {
        return false;
    }

    @Override
    public boolean canSaveProject() {
        return false;
    }

    @Override
    public boolean canCopyProject() {
        return false;
    }

    @Override
    public boolean canEditProperties() {
        return true;
    }

    @Override
    public boolean canAnalyse() {
        return false;
    }

    @Override
    public boolean vadidate() {
        return false;
    }

    @Override
    public boolean projectCreated() {
        if (m_project != null) {
            ProjectState ns = new ProjectStateEmpty(m_project);
            m_project.setState(ns);
            return true;
        }
        return false;
    }

    @Override
    public boolean propertiesChanged() {
        if (m_project.getName() != null && m_project.getDescription() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSaved() {
        return false;
    }

    @Override
    public boolean projectSaved() {
        return false;
    }

    @Override
    public boolean hasRoadNetwork() {
        return false;
    }

    @Override
    public boolean hasVehicles() {
        return false;
    }

    @Override
    public boolean canSimulate() {
        return false;
    }


}
