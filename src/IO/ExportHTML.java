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
import roadnetwork.domain.ResultSimulation;

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
        String resultString;
        
        if (flag == false) {
            return false;
        }

        resultString
                = "<html>"
                + "<body>";

        for (Result result : resultsList) {
            resultString
                    += "<p>"
                    + result.getGlobalResultsHTMLCode()
                    + "</p>";
        }
        resultString
                += "</body>"
                + "</html>";
        try {
            m_file.append(resultString);

            m_file.close();
        } catch (IOException ex) {
            ex.getMessage();
            flag = false;
        }
        return flag;
    }

    public boolean exportDetailedResults(ResultSimulation result) {

        boolean flag = createHTMLFile();
        String resultString;

        if (flag == false) {
            return false;
        }

        resultString
                = "<html>"
                + "<header>"
                + "<p>"
                + result.getGlobalResultsHTMLCode()
                + "</p>"
                + "</header>"
                +"<body>";
        
        resultString
                += result.getDetailedResults();

        resultString
                +=
                "</body>"+
                "</html>";

        try {
            m_file.append(resultString);

            m_file.close();
        } catch (IOException ex) {
            ex.getMessage();
            flag = false;
        }
        return flag;
    }
    
}
