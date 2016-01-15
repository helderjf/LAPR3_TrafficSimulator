/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import roadnetwork.domain.Result;

/**
 *
 * @author josemiranda
 */
public class ExportHTML {
    
    String m_fileName;
    FileWriter m_file;
    
    
    public ExportHTML(String fileName){
        m_fileName=fileName+".html";
    }
    
    private boolean createHTMLFile(){
        boolean flag = true;
        try{
            m_file = new FileWriter(new File(m_fileName));
        }catch(IOException ex) {
            ex.getMessage();
            flag = false;
        }
        return flag;
    }
    
    public boolean exportGlobalResults(ArrayList<Result> resultsList){
        
        boolean flag=createHTMLFile();
        
        if (flag==false) {
            return false;
        }
        
        try{
            m_file.append("<html>");
            m_file.append("<body>");
            
            for (Result result : resultsList) {
                m_file.append("<p>");
                m_file.append(result.getResultsHTMLCode());
                m_file.append("</p>");
            }
            m_file.append("</body>");
            m_file.append("</html>");
            
            m_file.close();
        }catch(IOException ex) {
            ex.getMessage();
            flag = false;
        }
        return flag;
    }
    
    
    
    public boolean exportFilterResults(){
        return true;
    }
    
}
