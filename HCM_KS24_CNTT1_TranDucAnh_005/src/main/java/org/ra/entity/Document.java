package org.ra.entity;

import java.util.List;
import java.util.Scanner;

public class Document {
    protected String documentId;
    protected String documentName;
    protected double fileSize;
    protected int downloads;

    public Document(String documentId, String documentName, int downloads, double fileSize) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.downloads = downloads;
        this.fileSize = fileSize;
    }

    public Document() {}

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public Document inputData(Scanner scanner, List<Document> documents) {
        while (true) {
            System.out.print("Enter ID: ");
            documentId = scanner.next();
            if (documentId.equals("")) throw new IllegalArgumentException("ID khong trung lap");
            break;
        }
        System.out.print("Enter Name: ");
        documentName = scanner.next();
        if (documentName.isEmpty()) throw new IllegalArgumentException("Name khong duoc rong");
        System.out.print("Enter Size: ");
        fileSize = scanner.nextDouble();
        if (fileSize <= 0) throw new IllegalArgumentException("Size phai lon hon 0");

        System.out.print("Enter Downloads: ");
        downloads = scanner.nextInt();
        if (downloads < 0) throw new IllegalArgumentException("Downloads lon hon hoac bang 0");
        return null;
    }

    public void displayData() {
        System.out.println(" --------------------------------");
        System.out.println("Document ID: " + this.documentId);
        System.out.println("Document Name: " + this.documentName);
        System.out.println("File Size: " + this.fileSize);
        System.out.println("Downloads: " + this.downloads);
        System.out.println(" --------------------------------");
    }
}

// - document (id không trùng, filezie > 0, download >=0)
