/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import roadnetwork.domain.PathParcel;
import roadnetwork.domain.Section;
import roadnetwork.domain.ResultStaticAnalysis;

/**
 *
 * @author josemiranda
 */
public class ExportCSV {

    String m_fileName;

    public ExportCSV(String name) {
        m_fileName = name+".csv";
    }

    public boolean export(ResultStaticAnalysis result) {
        boolean flag = true;
        try {
            FileWriter file = new FileWriter(new File(m_fileName));
            file.append("PATH;");
            file.append("\n");
            file.append("Section");
            file.append(';');
            file.append("Beginning Node");
            file.append(';');
            file.append("Ending Node");
            file.append(';');
            file.append("Section Weight");
            file.append(';');
            file.append("\n");
            int index = 0;
            for (PathParcel pp : result.getPath()) {
                file.append(pp.getSection().getRoadName());
                file.append(';');
                file.append(pp.getSection().getBeginningNode().getJunctionId());
                file.append(';');
                file.append(pp.getSection().getEndingNode().getJunctionId());
                file.append(';');
                Double time = result.getSectionWeight().get(index)/60;
                file.append(time.toString());
                index++;
                file.append("\n");
            }
            file.append("Total;");
            file.append(String.valueOf(result.getLength()));
            file.append(';');
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.getMessage();
            flag = false;
        }

        return flag;
    }

}
