/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 * @author HP-PC
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadBean {

    UploadedFile file;
    
    String destination = "C:\\images\\";

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void fileUploadListener(FileUploadEvent e) {
        // Get uploaded file from the FileUploadEvent
        this.file = e.getFile();
        String fileName = file.getFileName();
        //Save uploaded file
        // Print out the information of the file
        System.out.println("Uploaded File Name Is :: " + fileName + " :: Uploaded File Size :: " + file.getSize());
        // Add message
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            try {
                save(fileName);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void save(String fileName) throws IOException {
            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(new File(destination + fileName));
            try{
            IOUtils.copy(input,output);
            /*int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = input.read()) != -1) {
                out.write(bytes, 0, read);
            }*/
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }
}
