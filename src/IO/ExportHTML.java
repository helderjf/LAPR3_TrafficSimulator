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
            m_file.append("<html>\n"+
                    "<head>\n"+
                    "<title>\n"+
                    "Static Analysis - Global Results\n"+
                    "</title>\n");
            for (Result result : resultsList) {
                m_file.append("<p>\n");
                m_file.append(result.printResults());
                m_file.append("</p>\n");
            }
            
            m_file.append("</head>\n"+
                    "</html>\n");
            
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
